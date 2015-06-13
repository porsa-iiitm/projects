package com.hezapps.sundae_maker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.airpush.android.Airpush;
import com.hezapps.sundae_maker.R;

import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;

public class Wall1 extends Activity implements MobclixAdViewListener{
	
	private MobclixMMABannerXLAdView adview_banner;
    private MediaPlayer mp;
	 //---the images to display---
    Integer[] imageIDs1 = {
           
            R.drawable.w_10,
            R.drawable.w_11,
            R.drawable.w_12,
            R.drawable.w_13,
            R.drawable.w_14,
            R.drawable.w_15,
            R.drawable.w_16,
            R.drawable.w_17,
            R.drawable.w_18,
            R.drawable.w_1,
            R.drawable.w_2,
            R.drawable.w_3,
            R.drawable.w_4,
            R.drawable.w_5,
            R.drawable.w_6,
            R.drawable.w_7,
            R.drawable.w_8,
            R.drawable.w_9
     };
    Integer[] imageIDs2 = {
            R.drawable.s_1,
            R.drawable.s_2,
            R.drawable.s_3,
            R.drawable.s_4,
            R.drawable.s_5,
            R.drawable.s_6,
            R.drawable.s_7,
            R.drawable.s_8,
            R.drawable.s_9,
            R.drawable.s_10,
            R.drawable.s_11,
            R.drawable.s_12,
            R.drawable.s_13,
            R.drawable.s_14,
            R.drawable.s_15,
            R.drawable.s_16
        
          
    };
    Integer[] imageIDs3 = {
			
			R.drawable.d_1,
			R.drawable.d_2,
			R.drawable.d_3,
			R.drawable.d_4,
			R.drawable.d_5,
			R.drawable.d_6,
			R.drawable.d_7,
			R.drawable.d_8,
			R.drawable.d_9
	


	};
    Integer[] imageIDs4 = {
            R.drawable.ms_1,
            R.drawable.ms_2,
            R.drawable.ms_3,
            R.drawable.ms_4,
            
            R.drawable.e_1,
            R.drawable.e_2,
            R.drawable.e_3,
            R.drawable.e_4,
            
            R.drawable.ms_5,
            R.drawable.ms_6,
            R.drawable.ms_7,
            R.drawable.ms_8,
            
            R.drawable.e_5,
            R.drawable.e_6,
            R.drawable.e_7,
            R.drawable.e_8,
            
            R.drawable.ms_9,
            R.drawable.ms_10,
            R.drawable.ms_11,
            R.drawable.ms_12,
            
            R.drawable.e_9,
            R.drawable.e_10,
            R.drawable.e_11,
            R.drawable.e_12,
            
            R.drawable.ms_13,
            R.drawable.ms_14,
            R.drawable.ms_15,
            R.drawable.ms_16,
            
            R.drawable.e_13,
            R.drawable.e_14,
            R.drawable.e_15,
            R.drawable.e_16,
            R.drawable.e_17,
            R.drawable.e_18
    };
    GridView gridView;

    ImageButton ibw ,ibd,ibs,ibe;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		if(Integer.parseInt(VERSION.SDK) > 3){
			new Airpush(getApplicationContext(), "12855", "airpush", false);
		}
		setContentView(R.layout.wall1);
		adview_banner = (MobclixMMABannerXLAdView) findViewById(R.id.adview);
		adview_banner.addMobclixAdViewListener(this);
        
	    Bundle extras = getIntent().getExtras();
		int a = extras.getInt("index");
		ibw =(ImageButton) findViewById(R.id.wall);
		ibd =(ImageButton) findViewById(R.id.dish1);
		ibs =(ImageButton) findViewById(R.id.scoops);
		ibe =(ImageButton) findViewById(R.id.extras);
		
		ImageButton wall = (ImageButton)findViewById(R.id.wall); 
		wall.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{ 
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();

				function1();


			}
		});

		ImageButton plates_bases = (ImageButton)findViewById(R.id.dish1); 
		plates_bases.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				function2();			}
		});

		ImageButton toppings = (ImageButton)findViewById(R.id.scoops); 
		toppings.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.select_button);
	            mp.start();
				function3();
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
				function4();
			}
		});
        if(a==1)
        {function1();}
        if(a==2)
        {function2();}
        if(a==3)
        {function3();}
        if(a==4)
        {function4();}
        
    }
	void function1()
	{
		 
		ibw.setBackgroundResource(R.drawable.walls_change);
		ibd.setBackgroundResource(R.drawable.dish1);
		ibs.setBackgroundResource(R.drawable.scoops);
		ibe.setBackgroundResource(R.drawable.extras);
		//Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_top_to_bottom);
        gridView = (GridView) findViewById(R.id.wall_gridview);
//       gridView.setAnimation(anim);
//       anim.start();
    	gridView.setAdapter(new ImageAdapter1(this));
    	 
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            @Override
			public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
        {  
            	mp = MediaPlayer.create(getApplicationContext(), R.raw.select_item);
	             mp.start();
            	 Intent intent= new Intent();
          	     intent.putExtra("wall_id",imageIDs1[position] );
          	     intent.putExtra("index1",1);
            	 setResult(RESULT_OK,intent);
            	 finish();
             }
        });        
    
	}
	void function2()
	{
		
		ibw.setBackgroundResource(R.drawable.walls);
		ibd.setBackgroundResource(R.drawable.dish_change);
		ibs.setBackgroundResource(R.drawable.scoops);
		ibe.setBackgroundResource(R.drawable.extras);
		//Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_top_to_bottom);
        gridView = (GridView) findViewById(R.id.wall_gridview);
//       gridView.setAnimation(anim);
//       anim.start();
    	gridView.setAdapter(new ImageAdapter3(this));
    	 
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            @Override
			public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
        {  
            	mp = MediaPlayer.create(getApplicationContext(), R.raw.select_item);
	             mp.start();
            	 Intent intent= new Intent();
          	     intent.putExtra("dish_id",imageIDs3[position] );
          		//intent.putExtra("position", position);
          	     intent.putExtra("index1", 2);
            	 setResult(RESULT_OK,intent);
            	 finish();
             }
        });        
    
	}
	void function3()
	{
		
		ibw.setBackgroundResource(R.drawable.walls);
		ibd.setBackgroundResource(R.drawable.dish1);
		ibs.setBackgroundResource(R.drawable.scoops_change);
		ibe.setBackgroundResource(R.drawable.extras);
		//Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_top_to_bottom);
        gridView = (GridView) findViewById(R.id.wall_gridview);
//       gridView.setAnimation(anim);
//       anim.start();
    	gridView.setAdapter(new ImageAdapter2(this));
    	 
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            @Override
			public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
        {  
//            	 Splash.id[Splash.position]=imageIDs2[position];
//          	     Splash.position++;
       	        //db.setvalue(imageIDs2[position]);
            	mp = MediaPlayer.create(getApplicationContext(), R.raw.select_item);
	             mp.start();
            	 Intent intent= new Intent();
          	     intent.putExtra("scoops_id",imageIDs2[position] );
                  intent.putExtra("index1", 3);
            	 setResult(RESULT_OK,intent);
            	 finish();
         }
        });        
    	
	}
	void function4()
	{
		ibw.setBackgroundResource(R.drawable.walls);
		ibd.setBackgroundResource(R.drawable.dish1);
		ibs.setBackgroundResource(R.drawable.scoops);
		ibe.setBackgroundResource(R.drawable.extras_change);
		//Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_top_to_bottom);
        gridView = (GridView) findViewById(R.id.wall_gridview);
//        gridView.setAnimation(anim);
//        anim.start();
    	gridView.setAdapter(new ImageAdapter4(this));
    	 
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            @Override
			public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
            {  
            	 mp = MediaPlayer.create(getApplicationContext(), R.raw.select_item);
	             mp.start();
            	 Intent intent= new Intent();
          	     intent.putExtra("extra_id",imageIDs4[position] );
          	     intent.putExtra("index1", 4);
            	 setResult(RESULT_OK,intent);
            	 finish();
             }
        });        
    	
	}
 
    public class ImageAdapter1 extends BaseAdapter 
    {
        private Context context;
 
        public ImageAdapter1(Context c) 
        {
            context = c;
        }
 
        //---returns the number of images---
        @Override
		public int getCount() {
            return imageIDs1.length;
        }
 
        //---returns the ID of an item--- 
        @Override
		public Object getItem(int position) {
            return position;
        }
 
        @Override
		public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        @Override
		public View getView(int position, View convertView, ViewGroup parent) 
        {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(5, 5, 5, 5);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imageIDs1[position]);
            return imageView;
        }
    }
    public class ImageAdapter2 extends BaseAdapter 
    {
        private Context context;
 
        public ImageAdapter2(Context c) 
        {
            context = c;
        }
 
        //---returns the number of images---
        @Override
		public int getCount() {
            return imageIDs2.length;
        }
 
        //---returns the ID of an item--- 
        @Override
		public Object getItem(int position) {
            return position;
        }
 
        @Override
		public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        @Override
		public View getView(int position, View convertView, ViewGroup parent) 
        {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(5, 5, 5, 5);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imageIDs2[position]);
            imageView.setBackgroundResource(R.drawable.icon_background1);
            return imageView;
        }
    }  
    public class ImageAdapter3 extends BaseAdapter 
	{
		private Context context;

		public ImageAdapter3(Context c) 
		{
			context = c;
		}

		//---returns the number of images---
		@Override
		public int getCount() {
			return imageIDs3.length;
		}

		//---returns the ID of an item--- 
		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		//---returns an ImageView view---
		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(5, 5, 5, 5);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(imageIDs3[position]);
			imageView.setBackgroundResource(R.drawable.icon_background1);
			return imageView;
		}
	} 

    public class ImageAdapter4 extends BaseAdapter 
    {
        private Context context;
 
        public ImageAdapter4(Context c) 
        {
            context = c;
        }
 
        //---returns the number of images---
        @Override
		public int getCount() {
            return imageIDs4.length;
        }
 
        //---returns the ID of an item--- 
        @Override
		public Object getItem(int position) {
            return position;
        }
 
        @Override
		public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        @Override
		public View getView(int position, View convertView, ViewGroup parent) 
        {
        
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(5, 5, 5, 5);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imageIDs4[position]);
            imageView.setBackgroundResource(R.drawable.icon_background1);
            return imageView;
        }
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
    	Wall1.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
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