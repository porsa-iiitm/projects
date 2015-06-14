package me.prankul.sms.activities;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import me.prankul.sms.R;
import me.prankul.sms.SmsHelper;


public class ContactsActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener{

    SimpleCursorAdapter mAdapter;
    MatrixCursor mMatrixCursor;
    LoaderManager loaderManager;
    CursorLoader cursorLoader;

    private static String ID= ContactsContract.Contacts._ID;
    private static String DISPLAY_NAME= ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    private static String PHOTO_THUMBNAIL_URI= ContactsContract.CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI;
    private static String NUMBER= ContactsContract.CommonDataKinds.Phone.NUMBER;

    private static final String[] CURSOR_PROJECTION = { ID, DISPLAY_NAME, NUMBER, PHOTO_THUMBNAIL_URI };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        loaderManager=getLoaderManager();

        // The contacts from the contacts content provider is stored in this cursor
        mMatrixCursor = new MatrixCursor(new String[] { "_id","name","photo","details"});

        String[] fromColumns = new String[] {"name","photo","details"};

        int[] toViews = {       R.id.txtViewTitle,
                                R.id.imgViewLogo,
                                R.id.txtViewDescription};


        // Adapter to set data in the listview
        mAdapter = new SimpleCursorAdapter(this,R.layout.contact_row,null,fromColumns,toViews, 0);

        // Getting reference to listview
        ListView lstContacts = (ListView) findViewById(R.id.lst_contacts);

        // Setting the adapter to listview
        lstContacts.setAdapter(mAdapter);
        lstContacts.setOnItemClickListener(this);

        loaderManager.initLoader(1, null, this);

    }



    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

        Uri contactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection1= ContactsContract.CommonDataKinds.Phone.TYPE+" = '"+ ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE+"'";

        cursorLoader = new CursorLoader(this, contactsUri, CURSOR_PROJECTION, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

        return cursorLoader;
    }

    public void onLoadFinished(Loader<Cursor> loader,Cursor cursor) {
        if(mAdapter!=null && cursor!=null) {

            int displayNameIndex=cursor.getColumnIndex(DISPLAY_NAME);
            int photoThumbnailUriIndex=cursor.getColumnIndex(PHOTO_THUMBNAIL_URI);
            int numberIndex=cursor.getColumnIndex(NUMBER);
            int idIndex=cursor.getColumnIndex(ID);

                if(cursor.moveToFirst()){
                    do{
                        long contactId = cursor.getLong(idIndex);

                            String displayName = "";
                            String mobilePhone = "";
                            String photoPath = "";

                            displayName = cursor.getString(displayNameIndex);
                            photoPath = cursor.getString(photoThumbnailUriIndex);
                            mobilePhone = cursor.getString(numberIndex);
                            if (photoPath == null) {
                                photoPath = "" + R.drawable.user_icon;
                            }

                            // Adding id, display name, path to photo and other details to cursor
                            mMatrixCursor.addRow(new Object[]{Long.toString(contactId), displayName, photoPath, mobilePhone});

                    }while(cursor.moveToNext());
                    Log.e("Count: ", "" + mMatrixCursor.getCount());
                }
            mAdapter.swapCursor(mMatrixCursor); //swap the new cursor in.
        }
        else
            Log.v("TAG", "OnLoadFinished: mAdapter is null");
    }

    public void onLoaderReset(Loader<Cursor> arg0) {
        if(mAdapter!=null)
            mAdapter.swapCursor(null);
        else
            Log.v("TAG", "OnLoadFinished: mAdapter is null");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mMatrixCursor.moveToPosition(i);

        String address=mMatrixCursor.getString(3);
        Intent intent=new Intent();
        intent.putExtra(SmsHelper.ADDRESS,address);
        setResult(1,intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
        intent.putExtra(SmsHelper.ADDRESS,"");
        setResult(1,intent);
        super.onBackPressed();
    }
}
