package me.prankul.sms.activities;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.HashSet;

import me.prankul.sms.R;
import me.prankul.sms.adapters.SMSAdapter;
import me.prankul.sms.SmsHelper;
import me.prankul.sms.Util;

public class SMSActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    public String TAG = SMSActivity.class.getSimpleName();

    RecyclerView recyclerView;
    SMSAdapter smsAdapter;
    public static MatrixCursor matrixCursor=null;
    LoaderManager loaderManager;
    CursorLoader cursorLoader;

    private static final int LIST_ID=1;
    private static final String[] CURSOR_PROJECTION = {SmsHelper.ID,SmsHelper.THREAD_ID,SmsHelper.ADDRESS,SmsHelper.BODY,SmsHelper.READ};
    private static final String[] MATRIX_CURSOR = {SmsHelper.ID,SmsHelper.THREAD_ID,SmsHelper.ADDRESS,SmsHelper.BODY,SmsHelper.READ,SmsHelper.NAME};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        matrixCursor = new MatrixCursor(MATRIX_CURSOR);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        loaderManager=getLoaderManager();
        smsAdapter =new SMSAdapter(this, matrixCursor);
        recyclerView.setAdapter(smsAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    updateOnSwipe(position);
                                }
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    updateOnSwipe(position);
                                }
                            }
                        });
        recyclerView.addOnItemTouchListener(swipeTouchListener);

        loaderManager.initLoader(LIST_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        cursorLoader = new CursorLoader(this, SmsHelper.SMS_URI, CURSOR_PROJECTION, null, null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(smsAdapter !=null && cursor!=null) {

            if(cursor.moveToFirst()){
                //TODO: Check for MatrixCursor
                matrixCursor = new MatrixCursor(MATRIX_CURSOR);
                HashSet<String> isAdded=new HashSet<>();
                do{
                    String threadId= cursor.getString(smsAdapter.indexThreadId);

                    if(!isAdded.contains(threadId)){
                        String _id      = cursor.getString(smsAdapter.indexId);
                        String address  = cursor.getString(smsAdapter.indexAddress);
                        String body     = cursor.getString(smsAdapter.indexBody);
                        String read     = cursor.getString(smsAdapter.indexRead);

                        //Adding required columns to the cursor
                        String contactName=null;
                        if(address.length()>=10){
                            contactName= Util.getContactName(this, address);
                        }
                        matrixCursor.addRow(new Object[]{_id, threadId, address, body, read, contactName});
                        isAdded.add(threadId);
                    }

                }while(cursor.moveToNext());
            }
            //swap the new cursor in.
            smsAdapter.swapCursor(matrixCursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(smsAdapter !=null)
            smsAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.compose_message) {

            Intent intent = new Intent(this, ComposeSmsActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if(SDK_INT>=19){
            final String myPackageName = getPackageName();
            if(!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
                // App is not default, Make it default
                Intent intent =new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, myPackageName);
                startActivity(intent);
            }
        }
    }


    public void updateOnSwipe(int position){
        matrixCursor.moveToPosition(position);

        if(matrixCursor.getString(smsAdapter.indexName)==null){
            //Delete SMS
            this.getContentResolver().delete(SmsHelper.SMS_URI,SmsHelper.ID+"=?",
                    new String[]{matrixCursor.getString(smsAdapter.indexId)});
        }
        else
        {
            //Update "read" flag
            ContentValues contentValues=new ContentValues();
            contentValues.put(SmsHelper.READ, 1);
            this.getContentResolver().update(SmsHelper.SMS_URI, contentValues,SmsHelper.ID+"=?",
                    new String[]{matrixCursor.getString(smsAdapter.indexId)});
        }
        loaderManager.restartLoader(LIST_ID, null, this);
    }

}