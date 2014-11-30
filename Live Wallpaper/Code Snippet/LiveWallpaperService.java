package com.hezapps.almudena_fernandez;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class LiveWallpaperService extends WallpaperService {

	private final Handler mHandler = new Handler();

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public Engine onCreateEngine() {
		return new CubeEngine();
	}

	class CubeEngine extends Engine implements
			SharedPreferences.OnSharedPreferenceChangeListener {

		private float mXOffset = 0, mYOffset = 0, mXStep = 0, mYStep = 0,
				mXPixels = 0, mYPixels = 0;

		private int fps = 3;

		private float mPosY;
		private boolean mAnime = true;

		private final Runnable mDrawAnim = new Runnable() {
			@Override
			public void run() {
				drawFrame();
			}
		};

		private boolean mVisible;

		private static final int NUM_RES = 21;
		private final Bitmap[] mPics = new Bitmap[NUM_RES];
		private SharedPreferences mPrefs;

		CubeEngine() {
			Resources res = getResources();
			int i;

			for (i = 0; i < NUM_RES; i++) {
				int id = res.getIdentifier("boot_00" + (100 + (i + 1)),
						"drawable", "com.hezapps.almudena_fernandez");
				mPics[i] = BitmapFactory.decodeResource(res, id);
			}

			if (i >= NUM_RES) {
				SharedPreferences localSharedPreferences1 = LiveWallpaperService.this
						.getSharedPreferences("LiveWallpaperSettings", 0);
				mPrefs = localSharedPreferences1;
				mPrefs.registerOnSharedPreferenceChangeListener(this);
				SharedPreferences localSharedPreferences2 = mPrefs;
				onSharedPreferenceChanged(localSharedPreferences2, null);
				return;
			}

		}

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);

			setTouchEventsEnabled(false);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			mHandler.removeCallbacks(mDrawAnim);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			mVisible = visible;
			if (visible) {
				drawFrame();
			} else {
				mHandler.removeCallbacks(mDrawAnim);
			}
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format,
				int width, int height) {
			super.onSurfaceChanged(holder, format, width, height);

			drawFrame();
		}

		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences paramSharedPreferences, String paramString) {
			int i = paramSharedPreferences.getInt("seekBar", 3);
			fps = i;
		}

		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			mVisible = false;
			mHandler.removeCallbacks(mDrawAnim);
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset, float xStep,
				float yStep, int xPixels, int yPixels) {

			mXOffset = xOffset;
			mYOffset = yOffset;
			mXStep = xStep;
			mYStep = yStep;
			mXPixels = xPixels;
			mYPixels = yPixels;

		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			// if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// mAnime = !mAnime;
			// }
			super.onTouchEvent(event);
		}

		void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();

			Canvas c = null;
			try {
				c = holder.lockCanvas();
				if (c != null) {
					// draw something
					drawAnim(c);

				}
			} finally {
				if (c != null)
					holder.unlockCanvasAndPost(c);
			}

			// Reschedule the next redraw
			mHandler.removeCallbacks(mDrawAnim);
			if (mVisible && mAnime) {
				mHandler.postDelayed(mDrawAnim, 1000 * fps);

			}
		}

		private int idx = 0;

		void drawAnim(Canvas c) {
			c.save();
			// c.translate(0, mPosY);

			c.drawBitmap(mPics[idx], mXOffset, mYOffset, null);
			if (mAnime)
				++idx;
			if (idx == NUM_RES)
				idx = 0;

			c.restore();

		}

	}
}
