package me.prankul.sms;

import android.net.Uri;

/**
 * Created by prankul on 13/6/15.
 */
public class SmsHelper {
    public static final String ID = "_id";
    public static final String ADDRESS = "address";
    public static final String PERSON = "person";
    public static final String DATE = "date";
    public static final String READ = "read";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String BODY = "body";
    public static final String NAME = "name";
    public static final int MESSAGE_TYPE_INBOX = 1;
    public static final int MESSAGE_TYPE_SENT = 2;
    public static final Uri SMS_URI = Uri.parse("content://sms/");
    public static final Uri INBOX_URI = Uri.parse("content://sms/inbox");
    public static final Uri SENT_URI = Uri.parse("content://sms/sent");
}
