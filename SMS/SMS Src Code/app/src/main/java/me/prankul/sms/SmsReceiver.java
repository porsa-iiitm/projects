package me.prankul.sms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    public String TAG = SmsReceiver.class.getSimpleName();
    public static final String SMS_BUNDLE = "pdus";

    private NotificationManager notificationManager;
    public static int notificationID = 101;
//    public static int numMessages = 0;



    public void onReceive(Context context, Intent intent) {

        if("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction().trim()) &&
                android.os.Build.VERSION.SDK_INT<19) {
            Log.e(TAG, intent.getAction());
            callOnReceive(context, intent);
        }else if("android.provider.Telephony.SMS_DELIVER".equals(intent.getAction().trim())){
            Log.e(TAG,intent.getAction());
            callOnReceive(context, intent);
        }
    }

    private void callOnReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);

            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String body = smsMessage.getMessageBody().trim();
                String address = smsMessage.getOriginatingAddress();
                String date=Long.toString(smsMessage.getTimestampMillis());

                ContentValues contentValues=new ContentValues();
                contentValues.put(SmsHelper.ADDRESS,address);
                contentValues.put(SmsHelper.DATE,date);
                contentValues.put(SmsHelper.READ, 0);
                contentValues.put(SmsHelper.STATUS,-1);
                contentValues.put(SmsHelper.TYPE,SmsHelper.MESSAGE_TYPE_INBOX);
                contentValues.put(SmsHelper.BODY,body);

                Uri u=context.getContentResolver().insert(SmsHelper.INBOX_URI,contentValues);
                Log.e(TAG,"Inserted URI: "+u);

                String contactName=null;
                if(address.length()>=10){
                    contactName= Util.getContactName(context, address);
                }
                if(contactName==null)
                    contactName=address;

                displayNotification(context,contactName,body);
            }
        }
    }

    public void displayNotification(Context context, String contactName, String body){

        /* Invoking the default notification service */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(contactName);
        mBuilder.setContentText(body);
        mBuilder.setTicker("SMS");
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_email);

        /* Increase notification number every time a new notification arrives */
//        mBuilder.setNumber(++numMessages);

        /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(context, InboxActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        mBuilder.setAutoCancel(true);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        /* notificationID allows you to update the notification later on. */
        notificationManager.notify(notificationID++, mBuilder.build());
    }
}