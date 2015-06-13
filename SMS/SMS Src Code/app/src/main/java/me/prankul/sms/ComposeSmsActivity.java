package me.prankul.sms;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class ComposeSmsActivity extends ActionBarActivity {

    public String TAG = ComposeSmsActivity.class.getSimpleName();

    EditText phone, text;
    ImageButton pickContacts;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_sms);

        phone= (EditText) findViewById(R.id.phone);
        text= (EditText) findViewById(R.id.text);
        pickContacts= (ImageButton) findViewById(R.id.pick_contact);
        send= (Button) findViewById(R.id.send_sms);
    }

    public void getContact(View v){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivityForResult(intent,1);
    }

    public void sendSms(View v){
        String address=phone.getText().toString().trim();
        String body=text.getText().toString().trim();

        if(address!=null && !address.isEmpty() && address.length()>=10){

            if(body!=null && !body.isEmpty()){

                try {
                    //SEND SMS
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(address, null, body, null, null);

                    //Save in ContentProvider
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(SmsHelper.ADDRESS,address);
                    contentValues.put(SmsHelper.DATE,System.currentTimeMillis());
                    contentValues.put(SmsHelper.READ, 0);
                    contentValues.put(SmsHelper.STATUS,-1);
                    contentValues.put(SmsHelper.TYPE,SmsHelper.MESSAGE_TYPE_SENT);
                    contentValues.put(SmsHelper.BODY,body);

                    Uri u=this.getContentResolver().insert(SmsHelper.SENT_URI, contentValues);
                    Log.e(TAG, "Inserted URI: " + u);

                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this,"Enter Message",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Enter Valid Number",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1)
        {
            String address=data.getStringExtra(SmsHelper.ADDRESS);
            phone.setText(address);
        }
    }
}
