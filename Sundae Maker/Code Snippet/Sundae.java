package com.hezapps.sundae_maker;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airpush.android.Airpush;
import com.hezapps.sundae_maker.R;

import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;

public class Sundae extends Activity implements  View.OnClickListener,View.OnTouchListener , MobclixAdViewListener{
	
	private MobclixMMABannerXLAdView adview_banner;

	private MediaPlayer mp;
	private DragController mDragController;   // Object that sends out drag-drop events while a view is being moved.
	private DragLayer mDragLayer;             // The ViewGroup that supports drag-drop.
	public static DragLayer draglayer[]=new DragLayer[25];

	int a,b,c,d,e,index1;
	int id; 
	int del_id;
	private Bitmap b1,b2;
	private int h,w;
	private LinearLayout ll ;
	ImageButton zi;
	ImageButton zo;
	ImageView img ;
	int i=9785;
	int j= 7589;

	int ids[];
	int position;
	
	
	public static final boolean Debugging = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 if(Integer.parseInt(VERSION.SDK) > 3){
				new Airpush(getApplicationContext(), "12855", "airpush", false);
			}
		setContentView(R.layout.sundae);
		adview_banner = (MobclixMMABannerXLAdView) findViewById(R.id.adview);
		adview_banner.addMobclixAdViewListener(this);
		
		mDragController = new DragController(this);
		DragController dragController = mDragController;
		ll = (LinearLayout) findViewById(R.id.draglayout);
		mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
		mDragLayer.setDragController(dragController);
		dragController.addDropTarget (mDragLayer);
		zi=(ImageButton) findViewById(R.id.zoomIn);
		zo=(ImageButton) findViewById(R.id.zoomOut);

		zi.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)

			{
				if(del_id!=0)
				{
					
					DragLayer dl =(DragLayer) findViewById(R.id.drag_layer);

					img=(ImageView)findViewById(del_id);
					int left = img.getLeft();
					int top = img.getTop();
					int w = img.getWidth();
					int h = img.getHeight();
					if (w<300 || h<200)
					{
					w = (int) (w * 1.2);
					h = (int) (h * 1.2);
					}
					LayoutParams lp = new DragLayer.LayoutParams (w, h, left, top);

					dl.updateViewLayout(img, lp);

					}
				else
				{
					  Toast.makeText (getApplicationContext(), 
			                    "select a item for zoom in", Toast.LENGTH_LONG).show ();

				}
			}

		});
		
		zo.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)

			{
				if(del_id!=0)
				{
					
					DragLayer dl =(DragLayer) findViewById(R.id.drag_layer);
					img=(ImageView)findViewById(del_id);
					int left = img.getLeft();
					int top = img.getTop();
					int w = img.getWidth();
					int h = img.getHeight();
					 
					if (w>15 || h>10)
					{
					w = (int) (w / 1.2);
					h = (int) (h / 1.2);
					}
					LayoutParams lp = new DragLayer.LayoutParams (w, h, left, top);

					dl.updateViewLayout(img, lp);

}
				else
				{
					  Toast.makeText (getApplicationContext(), 
			                    "select a item for zoom out", Toast.LENGTH_LONG).show ();

				}
			}

		});
		
				
		ImageButton wall = (ImageButton)findViewById(R.id.wall);
		wall.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				Intent i = new Intent(Sundae.this, Wall1.class);
				i.putExtra("index", 1);
				startActivityForResult(i,1);
			}
		});

		ImageButton dish = (ImageButton)findViewById(R.id.dish);
		dish.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				Intent i = new Intent(Sundae.this, Wall1.class);
				i.putExtra("index", 2);
				startActivityForResult(i,1);
			}
		});

		ImageButton toppings = (ImageButton)findViewById(R.id.scoops);
		toppings.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				Intent i = new Intent(Sundae.this, Wall1.class);
				i.putExtra("index", 3);
				startActivityForResult(i,1);
			}
		});


		ImageButton candles = (ImageButton)findViewById(R.id.extras);
		candles.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				Intent i = new Intent(Sundae.this, Wall1.class);
				i.putExtra("index", 4);
				startActivityForResult(i,1);
			}
		});

		ImageButton del = (ImageButton)findViewById(R.id.delete);
		del.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if(del_id!=0)
				{
					  MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
		            mp.start();
					DeleteFromStack(del_id);
					if(del_id!=R.id.dish1)
					{
//						Splash.id2[Splash.position]=del_id;
//						Splash.position++;
						mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
						View vew =findViewById(del_id);
						mDragLayer.removeView(vew);
						//vew.setVisibility(View.INVISIBLE);
					}
					else
					{
						View vew =findViewById(del_id); 
						vew.setVisibility(View.GONE);
					}

				}
				del_id=0;
			}

			private void DeleteFromStack(int del_id) {
				for(int i=0;i<Splash.position;i++)
				{
					if(Splash.id1[i]==del_id)
					{
						for(int j=i;j<Splash.position-1;j++)
						{
							Splash.id1[j]=Splash.id1[j+1];
						}
					}
				}
				Splash.position--;
			}
		});
		
		ImageButton done = (ImageButton)findViewById(R.id.done); 
		done.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View view)
			{  
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				LinearLayout ll = (LinearLayout) findViewById(R.id.draglayout);
				if (a==0)
				{
					ll.setBackgroundResource(R.drawable.background);
				}
				else
				{
					ll.setBackgroundResource(a);
				}
				
				View v = findViewById(R.id.draglayout);	
				Bitmap bmp = loadBitmapFromView(v);  
				Intent i = new Intent("com.hezapps.sundae_maker.Done");
				i.putExtra("done_id", bmp);
				startActivityForResult(i,2);
				
			}
		});




		ImageButton undo = (ImageButton)findViewById(R.id.undo); 
		undo.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View view)
			{ 
				Splash.position--;

				if(Splash.position>=0)
				{
					  MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
		            mp.start();
					if(Splash.id1[Splash.position]==R.id.dish1)
					{
						View vew = findViewById(Splash.id1[Splash.position]);
						vew.setVisibility(View.GONE);
					}
					else
					{
						DragLayer v = (DragLayer) findViewById(R.id.drag_layer);
						View vew = findViewById(Splash.id1[Splash.position]);
						v.removeView(vew);
					}
					del_id=0;
				}
				//vew.setVisibility(View.INVISIBLE);
				if(Splash.position<0)
				{
					Splash.position=0;
				}

			}
		}); 


		ImageButton startover = (ImageButton)findViewById(R.id.startover); 
		startover.setOnClickListener(this);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1&&resultCode==RESULT_OK){
			index1= data.getIntExtra("index1",0);
			
			if(index1==1)
			{
				a= data.getIntExtra("wall_id",0);
				RelativeLayout dl =(RelativeLayout) findViewById(R.id.sundae_frags);
				dl.setBackgroundResource(a);	
				
			}
			if(index1==2)
			{
				b = data.getIntExtra("dish_id",0);
				
				ImageView dish1 =(ImageView) findViewById(R.id.dish1);
				dish1.setImageResource(b);
				dish1.setVisibility(View.VISIBLE);
				Splash.id1[Splash.position]=R.id.dish1;
			
				Splash.position++;
				dish1.setOnClickListener(this);
				dish1.setOnTouchListener(this);

			}
			if(index1==3)
			{
				d= data.getIntExtra("scoops_id",0);
				ImageView image_scoops = new ImageView(this);
				image_scoops.setId(i);
				Splash.id1[Splash.position]=image_scoops.getId();
				Splash.position++;
				image_scoops.setScaleType(ImageView.ScaleType.CENTER_CROP);
				image_scoops.setImageResource(d);
				//View ve= findViewById(d);
			
				mDragLayer.addView(image_scoops);
				
//				Splash.id1[Splash.position]=image_topping.getId();
//				Splash.position++;

				//ll.addView(image_topping);
				//image_topping.setOnLongClickListener(this);
				image_scoops.setOnTouchListener(this);
				image_scoops.setOnClickListener(this);
				i++;
			}
			if(index1==4)
			{
				e = data.getIntExtra("extra_id",0);
				ImageView image_extras = new ImageView(this);
				image_extras.setId(j);
				Splash.id1[Splash.position]=image_extras.getId();
				Splash.position++;
				image_extras.setScaleType(ImageView.ScaleType.CENTER_CROP);
				image_extras.setImageResource(e);
				mDragLayer.addView(image_extras);
				//ll.addView(image_candles);
				//image_candles.setOnLongClickListener(this);
				image_extras.setOnTouchListener(this);
				image_extras.setOnClickListener(this);
				j++;
			}
		}
		if(requestCode==2&&resultCode==RESULT_OK)
		{
			int color = data.getIntExtra("color",0);
			LinearLayout ll = (LinearLayout) findViewById(R.id.draglayout);
			//ll.setBackgroundResource(a);
			ll.setBackgroundColor(color);

		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		del_id = v.getId();
		
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			
			return startDrag (v);

		default :
			break;
		}
		return false;
	}
	@Override
	public void onClick(View v) {
		//Cake1.del_id = v.getId();
		if (v == findViewById(R.id.startover)) {
			  MediaPlayer	mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
            mp.start();
            

        	Intent i = new Intent("com.hezapps.sundae_maker.LandingScreen");
        	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	Splash.position=0;
        	for(int i1=0;i1<Splash.id1.length;i1++)
        	{
        		Splash.id1[i1]=0;
        	}
        	startActivity(i);
        	
		}
	}

	public static Bitmap loadBitmapFromView(View v) {
		Bitmap b = Bitmap.createBitmap(320,240, Bitmap.Config.RGB_565);
		Canvas c = new Canvas(b);
		Paint paint = new Paint();
		//c.drawBitmap(viewBgrnd, 0, 0, paint);
		v.draw(c);
		return b;

	}

	public boolean startDrag (View v)
	{
		Object dragInfo = v;
		mDragController.startDrag (v, mDragLayer, dragInfo, DragController.DRAG_ACTION_MOVE);
		return true;
	}
	public void toast (String msg)
	{
		Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
	} // end toast

	/**
	 * Send a message to the debug log and display it using Toast.
	 */


	public void trace (String msg) 
	{
		if (!Debugging) return;
		Log.d ("Cake", msg);
		toast (msg);
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
    	Sundae.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
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
    		// prepare the alert box
    		final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

    		alertbox.setCancelable(true);
    		alertbox.setIcon(R.drawable.icon);
    		alertbox.setTitle("Home Screen");
    		alertbox.setInverseBackgroundForced(true);
    		// set the message to display
    		alertbox.setMessage("Do You Really Want To Quit ?");

    		// set a positive/yes button and create a listener
    		alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

    		// do something when the button is clicked
    		@Override
    		public void onClick(DialogInterface arg0, int arg1) {
    		Intent i = new Intent("com.hezapps.sundae_maker.LandingScreen");
    		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		Splash.position=0;
    		for(int i1=0;i1<Splash.id1.length;i1++)
    		{
    			Splash.id1[i1]=0;
    		}
    		startActivity(i);
    		}
    		});
    		// set a negative/no button and create a listener
    		alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

    		// do something when the button is clicked
    		@Override
    		public void onClick(DialogInterface arg0, int arg1) {

    		}
    		});

    		// display box
    		alertbox.show();
    		//finish();
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
	public void onSuccessfulLoad(MobclixAdView arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String query() {
		// TODO Auto-generated method stub
		return null;
	}
}
