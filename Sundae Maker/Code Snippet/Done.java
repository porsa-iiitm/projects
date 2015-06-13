package com.hezapps.sundae_maker;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.airpush.android.Airpush;
import com.hezapps.sundae_maker.R;
import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;
public class Done extends Activity implements MobclixAdViewListener {
	
	private MobclixMMABannerXLAdView adview_banner;
    private Timer adRefreshTimer;
    private static final int adRefreshTime = 30000; //30 seconds

	Bitmap bm;
	String filename;
	private ProgressDialog m_ProgressDialog = null;
	private Runnable viewOrders;
	private static File APP_FILE_PATH = new File("/sdcard/My Sundae/");
	WebView Webview;
	private MediaPlayer mp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(Integer.parseInt(VERSION.SDK) > 3){
			new Airpush(getApplicationContext(), "12855", "airpush", false);
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.done);
		
		adview_banner = (MobclixMMABannerXLAdView) findViewById(R.id.adview);
		adview_banner.addMobclixAdViewListener(this);
		
		 viewOrders = new Runnable(){
				@Override
				public void run() {
					getOrders();
				}
			};
		
		

//
//		Bundle extras = getIntent().getExtras();
//		final Bitmap bm = (Bitmap)extras.get("done_id");

		APP_FILE_PATH.mkdirs();


		ImageButton share =(ImageButton)findViewById(R.id.share);
		share.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.promomailsubject));
				i.putExtra(Intent.EXTRA_TEXT, getString(R.string.promomailbody));
				startActivity(Intent.createChooser(i, "Share Via"));

			}
		});
	
		ImageButton btnmail = (ImageButton)findViewById(R.id.mail); 
		btnmail.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
				mp.start();

			// TODO Auto-generated method stub

			final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

			emailIntent.setType("plain/text");

			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ ""});

			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.promomailsubject));

			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.promomailbody));

			Done.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));}
		});

		findViewById(R.id.shareimage).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("image/jpg");

				//				    share.putExtra(Intent.EXTRA_STREAM,
				//				      Uri.parse("file://" + APP_FILE_PATH +filename+".jpg"));
				if(filename==null)
				{
					saveandforward();
				}

				Uri uri = Uri.fromFile(new File(APP_FILE_PATH , filename+".jpg"));
				share.putExtra(Intent.EXTRA_STREAM, uri);


				startActivity(Intent.createChooser(share, "Share Tag"));

			}
		});
		ImageButton btn = (ImageButton) findViewById(R.id.gallery);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				try {
					mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
		            mp.start();
					if(filename==null)
					{
						saveandforward();
					}
//					Toast.makeText (getApplicationContext(), 
//							"Image has been saved to Gallery(SD card) by name : " + APP_FILE_PATH + filename, Toast.LENGTH_LONG).show ();

					Thread thread = new Thread(null, viewOrders, "MagentoBackground");
					thread.start();
					m_ProgressDialog = ProgressDialog.show(Done.this,"Please wait...", "Saving...", true);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	void saveandforward(){
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddHHmmss");
			filename = sdf.format(cal.getTime());
			//File fname = new File (APP_FILE_PATH,filename);
			final FileOutputStream out = new FileOutputStream(new File(APP_FILE_PATH ,filename +".jpg"));

			Bundle extras = getIntent().getExtras();
			final Bitmap bm = (Bitmap)extras.get("done_id");
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();

		}catch (Exception e) {
			e.printStackTrace();
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

			Thread.sleep(3000);
			//    Log.i("ARRAY", ""+ m_orders.size());
		} catch (Exception e) {
			Log.e("BACKGROUND_PROC", e.getMessage());
		}
		runOnUiThread(returnRes);
	}
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
			Done.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//Intent i = new Intent(Done.this, Cake1.class);
			int color = 0x00000000;
			Intent intent= new Intent();
			intent.putExtra("color",color);
			//intent.putExtra("index1",1);
			setResult(RESULT_OK,intent);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
	public void onSuccessfulLoad(MobclixAdView v) {
		// TODO Auto-generated method stub
	adview_banner.setVisibility(v.VISIBLE);	
	}

	@Override
	public String query() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
