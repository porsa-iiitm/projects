package com.hezapps.sundae_maker;

import java.io.File;
import java.io.FileFilter;

import com.hezapps.sundae_maker.R;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Displays images from an SD card.
 */
public class MySundae extends Activity {
	
	private ProgressDialog m_ProgressDialog = null;
	private Runnable viewOrders;

    /**
     * Cursor used to access the results from querying for images on the SD card.
     */
    private Cursor cursor;
    /*
     * Column index for the Thumbnails Image IDs.
     */
    private int columnIndex;
    
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.mysundae);
        

		viewOrders = new Runnable(){
			@Override
			public void run() {
				getOrders();
			}
		};
		Thread thread = new Thread(null, viewOrders, "MagentoBackground");
		thread.start();
		m_ProgressDialog = ProgressDialog.show(MySundae.this,"Please wait...", "Loading...", true);
 
        File extStore = Environment.getExternalStorageDirectory();
        File[] imageDirs = extStore.listFiles(filterForImageFolders);
        String path = imageDirs.toString();

        // Set up an array of the Thumbnail Image ID column we want
        String[] projection = {MediaStore.Images.Thumbnails.IMAGE_ID};
        // Create the cursor pointing to the SDCard
        cursor = managedQuery( MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection, // Which columns to return
                null,       // Return all rows
                null,
                MediaStore.Images.Thumbnails.IMAGE_ID);
        // Get the column index of the Thumbnails Image ID
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID);

        GridView sdcardImages = (GridView) findViewById(R.id.sdcard);
        sdcardImages.setAdapter(new ImageAdapter(this));

        // Set up a click listener
        sdcardImages.setOnItemClickListener(new OnItemClickListener() {
            @Override
			public void onItemClick(AdapterView parent, View v, int position, long id) {
//            	
////            //	long imageId = (ImageAdapter)parent.getAdapter.getItemAt(position);
////                Intent fullScreenIntent = new Intent(v.getContext(),this.class);
////                fullScreenIntent.putExtra(MyCakes.class.getName(),id) ;
////                MyCakes.this.startActivity(fullScreenIntent); 
////            	
//               //  Get the data location of the image
//                String[] projection = {MediaColumns.DATA};
//                cursor = managedQuery( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        projection, // Which columns to return
//                        null,       // Return all rows
//                        null,
//                        MediaStore.Images.Thumbnails.IMAGE_ID);
//                
//                columnIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
//                cursor.moveToPosition(position);
//               
//                // Get image filename
//                String imagePath = cursor.getString(columnIndex);
//                // Use this path to do further processing, i.e. full screen display
            }
        });
    }

    //File Filter
    FileFilter filterForImageFolders = new FileFilter() 
    {            
    private	String folder1 = "My Sundae";
   
        @Override
		public boolean accept(File folder) 
        { 
            try 
            { 
                //Checking only directories, since we are checking for files within 
                //a directory 
                if(folder.isDirectory()) 
                { 
                    File[] listOfFiles = folder.listFiles(); 

                    if (listOfFiles == null)  
                    return true;
//                    //For each file in the directory... 
//                    for (File file : listOfFiles) 
//                    {                            
//                        //Check if the extension is one of the supported filetypes                           
//                        //imageExtensions is a String[] containing image filetypes (e.g. "png")
////                        for (String ext : ex) 
////                        { 
////                            if (file.getName().endsWith("." + ext)) 
////                            	return true; 
////                        } 
//                    }                        
                } 
                return false; 
            } 
            catch (SecurityException e) 
            { 
                Log.v("debug", "Access Denied"); 
                return false; 
            } 
        } 
    };

    
    
    
    /**
     * Adapter for our image files.
     */
    private class ImageAdapter extends BaseAdapter {

        private Context context;

        public ImageAdapter(Context localContext) {
            context = localContext;
        }

        @Override
		public int getCount() {
            return cursor.getCount();
        }
        @Override
		public Object getItem(int position) {
            return position;
        }
        @Override
		public long getItemId(int position) {
            return position;
        }
        

        

        @Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ImageView picturesView;
            if (convertView == null) {
                picturesView = new ImageView(context);
                // Move cursor to current position
                cursor.moveToPosition(position);
                // Get the current value for the requested column
                int imageID = cursor.getInt(columnIndex);
                // Set the content of the image based on the provided URI
                picturesView.setImageURI(Uri.withAppendedPath(
                        MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));
                picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                picturesView.setPadding(8, 8, 8, 8);
                picturesView.setLayoutParams(new GridView.LayoutParams(100, 100));
        
            }
            else {
                picturesView = (ImageView)convertView;
            }
            return picturesView;
        }
    }
    
    private Runnable returnRes = new Runnable() {

		@Override
		public void run() {
			m_ProgressDialog.dismiss();
		}
	};

	private void getOrders(){
		try{

			Thread.sleep(2000);
			// Log.i("ARRAY", ""+ m_orders.size());
		} catch (Exception e) {
			Log.e("BACKGROUND_PROC", e.getMessage());
		}
		runOnUiThread(returnRes);
	}
    
   
}