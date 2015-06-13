package com.hezapps.almudena_fernandez;

/**
 * Copyright CMW Mobile.com, 2010. 
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;

/**
 * The SampleSeekBarDialogPreferenceSettings is responsible for the handling of
 * this sample settings.
 * 
 * @author Casper Wakkers
 */
public class LiveWallpaperSettings extends PreferenceActivity implements
		SharedPreferences.OnSharedPreferenceChangeListener,
		MobclixAdViewListener {
	/**
	 * {@inheritDoc}
	 */
	private MobclixMMABannerXLAdView adview_banner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		getPreferenceManager()
				.setSharedPreferencesName("LiveWallpaperSettings");

		setContentView(R.layout.pref);

		addPreferencesFromResource(R.xml.settings);
		getPreferenceManager().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);

		// TextView title=(TextView) findViewById(R.id.text);
		// title.setText(R.string.title);
		//		
		// TextView title2=(TextView) findViewById(R.id.text2);
		// title2.setText(R.string.title2);
		// title2.setTextColor(R.string.white);

		adview_banner = (MobclixMMABannerXLAdView) findViewById(R.id.adview);
		adview_banner.addMobclixAdViewListener(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);

		super.onDestroy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		finish();
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
