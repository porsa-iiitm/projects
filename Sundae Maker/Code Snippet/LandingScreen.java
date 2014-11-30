package com.hezapps.sundae_maker;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.airpush.android.Airpush;
import com.hezapps.sundae_maker.R;

import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;

public class LandingScreen extends Activity implements MobclixAdViewListener {
	
	private MobclixMMABannerXLAdView adview_banner;

	private LinearLayout linear_ads;
	private MediaPlayer mp;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		if(Integer.parseInt(VERSION.SDK) > 3){
			new Airpush(getApplicationContext(), "12855", "airpush", false);
		}
		
		setContentView(R.layout.landingscreen);	
		adview_banner = (MobclixMMABannerXLAdView) findViewById(R.id.adview);
		adview_banner.addMobclixAdViewListener(this);
	
    	
	
    
		ImageButton btnplay = (ImageButton)findViewById(R.id.play); 
		btnplay.setOnClickListener(new View.OnClickListener()
	    {
	        @Override
			public void onClick(View view)
	        
	        {
	            mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
	        	startActivity( new Intent( LandingScreen.this, Sundae.class ) );
	    	}
	    });
		
		
		ImageButton btnmysun = (ImageButton)findViewById(R.id.mysundae);
		btnmysun.setOnClickListener(new View.OnClickListener()
	    {
	        @Override
			public void onClick(View view)
	        {
//	        	Intent intent = new Intent(); 
//	        	intent.setType("image/*"); 
//	        	intent.setAction(Intent.ACTION_GET_CONTENT); 
//	        	startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
	        	mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
	            startActivity( new Intent( LandingScreen.this, MySundae.class ) );

	    	}
	    });
		
		
		ImageButton btnmoregames = (ImageButton)findViewById(R.id.moregames); 
		btnmoregames.setOnClickListener(new View.OnClickListener()
	    {
	        @Override
			public void onClick(View view)
	        {
	        	mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
	        	Intent goToMarket = null;
	        	goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/search?q=pub:\"Hezapps\""));
	        	startActivity(goToMarket);
	        	//return true;
	    	}
	    });
	}
//	 @Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    try {
//	        if (resultCode == RESULT_OK) {
//	            if (requestCode == 1) {
//	                Uri selectedImageUri = data.getData();
//	                String selectedImagePath = getPath(selectedImageUri);
//	                getBitmap(selectedImagePath, 0);
//	                // Log.d("Debug","Saved...." + selectedImagePath);
//	            }
//	        }
//	    } catch (Exception e) {
//	        Log.e("Error", "Unable to set thumbnail", e);
//	    }
//	}
//	 public Bitmap getBitmap(String path, int size) {
//		    BitmapFactory.Options options = new BitmapFactory.Options();
//		    options.inSampleSize = size;
//		    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
//
//		    return bitmap;
//		}
//	 public String getPath(Uri uri) {
//		 Cursor cursor = null;
//		    int column_index = 0;
//		    try {
//		        String[] projection = { MediaColumns.DATA };
//		        cursor = managedQuery(uri, projection, null, null, null);
//		        column_index = cursor
//		        .getColumnIndexOrThrow(MediaColumns.DATA);
//		        cursor.moveToFirst();
//		    } catch (Exception e) {
//		        Log.d("Error", "Exception Occured", e);
//
//		    }
//
//		    return cursor.getString(column_index);
//
//	 }

	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
	    	MenuInflater inflater = getMenuInflater();
	    	inflater.inflate(R.menu.menu, menu);
	    	return true;
	    	}
	    
	    @Override
		public boolean onOptionsItemSelected(MenuItem item) {
	    	// Handle item selection
	    	switch (item.getItemId()) {
	    	case R.id.feedback:
	    	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	    	emailIntent.setType("plain/text");
	    	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"feedback.mylabs@gmail.com"});
	    	// emailIntent.putExtra(android.content.Intent.ACTION_SENDTO, "feedback.mylabs@gmail.com ");
	    	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback for Sundae Maker ");
	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Insert your feedback");
	    	LandingScreen.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	    	return true;
	    	
	    	case R.id.rate:
	    		Intent rate = null;
	    		rate = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=com.hezapps.sundae_maker"));
	    		startActivity(rate);
	    		return true;
	    	
	    	case R.id.more:
	    		Intent goToMarket = null;
	    		goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/search?q=pub:\"Hezapps\""));
	    		startActivity(goToMarket);
	    		return true;

	    	default:
	    	return super.onOptionsItemSelected(item);
	    	}
	    	}
		@Override
		public String keywords() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void onAdClick(MobclixAdView arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onCustomAdTouchThrough(MobclixAdView arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onFailedLoad(MobclixAdView arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean onOpenAllocationLoad(MobclixAdView arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void onSuccessfulLoad(MobclixAdView arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public String query() {
			// TODO Auto-generated method stub
			return null;
		}
}


