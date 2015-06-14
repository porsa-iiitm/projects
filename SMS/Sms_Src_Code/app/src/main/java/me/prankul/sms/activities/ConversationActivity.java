package me.prankul.sms.activities;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.prankul.sms.adapters.ConversationAdapter;
import me.prankul.sms.R;
import me.prankul.sms.SmsHelper;


public class ConversationActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public String TAG = ConversationActivity.class.getSimpleName();

    RecyclerView recyclerView;
    ConversationAdapter conversationAdapter;
    public static MatrixCursor matrixCursor=null;
    LoaderManager loaderManager;
    CursorLoader cursorLoader;
    String threadId=null;
    String address=null;

    EditText text;
    Button send;

    private static final int LIST_ID=2;
    private static final String[] CURSOR_PROJECTION = {SmsHelper.ID,SmsHelper.BODY,SmsHelper.TYPE, SmsHelper.DATE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Bundle bundle=getIntent().getExtras();
        threadId=bundle.getString(SmsHelper.THREAD_ID);
        address=bundle.getString(SmsHelper.ADDRESS);
        String name=bundle.getString(SmsHelper.NAME);

        //Set Title
        if(name!=null)
            setTitle(name);
        else
            setTitle(address);

        text= (EditText) findViewById(R.id.text);
        send= (Button) findViewById(R.id.send_sms);

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        matrixCursor = new MatrixCursor(CURSOR_PROJECTION);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        loaderManager=getLoaderManager();
        conversationAdapter =new ConversationAdapter(this, matrixCursor);
        recyclerView.setAdapter(conversationAdapter);

        loaderManager.initLoader(LIST_ID, null, this);
        markThisThreadAsRead();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        cursorLoader = new CursorLoader(this, SmsHelper.SMS_URI, CURSOR_PROJECTION, SmsHelper.THREAD_ID+"="+threadId,
                null, SmsHelper.DATE + " ASC ");
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(conversationAdapter !=null && cursor!=null) {

            if(cursor.moveToFirst()){
                //TODO: Check for MatrixCursor
                matrixCursor = new MatrixCursor(CURSOR_PROJECTION);
                do{
                    String _id      = cursor.getString(conversationAdapter.indexId);
                    String body     = cursor.getString(conversationAdapter.indexBody);
                    String type     = cursor.getString(conversationAdapter.indexType);
                    String date     = cursor.getString(conversationAdapter.indexType);

                    matrixCursor.addRow(new Object[]{_id,body,type,date});

                }while(cursor.moveToNext());
            }
            //swap the new cursor in.
            conversationAdapter.swapCursor(matrixCursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(conversationAdapter !=null)
            conversationAdapter.swapCursor(null);
    }

    public void markThisThreadAsRead(){

        //Update "read" flag
        ContentValues contentValues=new ContentValues();
        contentValues.put(SmsHelper.READ, 1);
        this.getContentResolver().update(SmsHelper.SMS_URI, contentValues, SmsHelper.THREAD_ID +
                "=" + threadId, null);

    }

    public void sendSms(View v){
        String body=text.getText().toString().trim();

        if(body!=null && !body.isEmpty()){

            try {
                //SEND SMS
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(address, null, body, null, null);

                //Save in ContentProvider
                ContentValues contentValues=new ContentValues();
                contentValues.put(SmsHelper.ADDRESS,address);
                contentValues.put(SmsHelper.DATE, System.currentTimeMillis());
                contentValues.put(SmsHelper.READ, 1);
                contentValues.put(SmsHelper.STATUS, -1);
                contentValues.put(SmsHelper.TYPE, SmsHelper.MESSAGE_TYPE_SENT);
                contentValues.put(SmsHelper.BODY, body);

                Uri u=this.getContentResolver().insert(SmsHelper.SENT_URI, contentValues);
                Log.e(TAG, "Inserted URI: " + u);

                text.setText("");
                loaderManager.restartLoader(LIST_ID, null, this);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this,"Enter Message",Toast.LENGTH_LONG).show();
        }

    }
}
