package com.prankul.toi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by prankul on 3/4/15.
 */
public class Utils {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String message) {
        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        catch (Exception e) {
        }
    }

    public static void hideProgressDialog() {
        try {
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
        } catch (Exception e) {
        }
    }

    public static boolean isShowingProgressDialog() {
        return progressDialog.isShowing();
    }

    public static void showAlertDialog(Activity activity,String data){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setMessage(data);
        alertDialogBuilder.setPositiveButton("Ok",null);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showToast(final Context context, final String message){
        try {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }.start();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static String getTimestampString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTimestampString(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM hh:mm");
        return dateFormat.format(timestamp);
    }

    public static long getTimeDifference(String dateLine)
    {
        try {
            //"Jun 8, 2015, 01.08AM IST"
            dateLine=dateLine.substring(0,dateLine.length()-4);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy, hh.mmaa", Locale.getDefault());
            Date d = sdf.parse(dateLine);

            Calendar c = Calendar.getInstance();
            c.setTime(d);
            long time = c.getTimeInMillis();
            long curr = System.currentTimeMillis();
            long diff = curr - time;    //Time difference in milliseconds
            return diff/(1000*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Checks if device is connected to Internet or not.
     *
     * @param context the activity context
     * @return true, if device is connected to Internet.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }
}
