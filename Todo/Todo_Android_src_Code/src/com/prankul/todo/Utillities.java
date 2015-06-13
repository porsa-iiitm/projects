package com.prankul.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import com.prankul.model.Task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import java.io.*;

public class Utillities {

	private static ProgressDialog progressDialog;
	public static String BASE_URL="http://192.168.1.5/laravel/";
    public static int LOW=1;
    public static int MEDIUM=2;
    public static int HIGH=3;

	public static void showProgressDialog(Context context, String message) {
		try {
			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage(message);
			progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
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
	

   public static int PriorityToValue(String Priority)
   {
	if(Priority.equals("High"))
		return HIGH;
	else if(Priority.equals("Medium"))
		return MEDIUM;
	else if(Priority.equals("Low"))
		return LOW;
	return 0;
		
   }
   public static long DateToTimestamp(String Strdate)
   {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   Date date = null;
		try {
			date = (Date)formatter.parse(Strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		   System.out.println(date.getTime());
		   return date.getTime()+19800000;
   }
   
   public static String getDate(long time) {
	    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
	    cal.setTimeInMillis(time);
	    String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
	    return date;
	}
   
   
   public static String TasksToString(Vector<Task> taskVector)
   {
	   StringBuilder sb = new StringBuilder();
	   String ret = null;
       for(int i=0;i<taskVector.size();i++)
       {
    	   Task task =taskVector.get(i);
    	   if(i==0)
    	   {
    		   ret="<b><h3>Task # "+(i+1)+"</h3></b>\n"+
    		    	"<b><i>Title: </i></b>" + task.getTitle() + "\r\n\n" +
    		        "<b><i>Description: </i></b>" + task.getDescription() + "\r\n\n" + 
    		        "<b><i>Priority: </i></b>" + task.getPriority() + "\r\n\n" + 
    		        "<b><i>Status: </i></b> " + task.getStatus() + "\r\n\n" + 
    		        "<b><i>Created At: </i></b> " + task.getCreated_at() + "\r\n\n" ; 
    	   }
    	   else
    	   {
		    	   ret=ret+"<b><h3>Task # "+(i+1)+"</h3></b>\n"+
		    	   "<b><i>Title: </i></b>" + task.getTitle() + "\r\n\n" +
		           "<b><i>Description: </i></b>" + task.getDescription() + "\r\n\n" + 
		           "<b><i>Priority: </i></b>" + task.getPriority() + "\r\n\n" + 
		           "<b><i>Status: </i></b> " + task.getStatus() + "\r\n\n" + 
		           "<b><i>Created At: </i></b> " + task.getCreated_at() + "\r\n\n" ;
    	   }
          
       }
       return ret;
	}
	public static void makeAlertdiallog(Context context, String titlte,
			String msg) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titlte);
		builder.setMessage(msg);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
/*    public static void ObjectToXML(Task task , String FILE_NAME) {
 
        try {
            JAXBContext context = JAXBContext.newInstance(Task.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
            // m.marshal(task, System.out);
 
            // Write to File
            m.marshal(task, new File("FolderName/sdcard/"+FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }*/
	/*public static void write(Task task, File filename) throws Exception
	{
        XMLEncoder encoder =new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
        encoder.writeObject(task);
        encoder.close();
    }*/
}
