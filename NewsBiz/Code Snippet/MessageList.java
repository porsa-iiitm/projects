package com.techfest.appsurd.newsbiz;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.techfest.appsurd.newsbiz.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MessageList extends ListActivity {
	TextView tv;
	private List<Message> messages;
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	private Vector<RowData3> data3;
	ArrayAdapter<String> adaptersimple;
	RowData rd;
	RowData3 rd3;
	int a;
	CustomAdapter3 adapter3;
	CustomAdapter2 adapter2;
	CustomAdapter1 adapter1;
	CustomAdapter adapter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.list);
		tv = (TextView) findViewById(R.id.title);
		new Pdialog().execute();
	}

	private void loadFeed() {
		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		data = new Vector<RowData>();
		data3 = new Vector<RowData3>();
		try {
			Bundle extras = getIntent().getExtras();
			a = extras.getInt("index");
			switch (a) {
			// Top News
			case 01:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/top.xml";
				tv.setText("Top News");
				break;
			// Sports
			case 10:
				BaseFeedParser.feedUrlString = "http://feeds.reuters.com/reuters/INcricketNews?format=xml";
				tv.setText("Cricket News");
				break;
			case 11:
				BaseFeedParser.feedUrlString = "http://static.espncricinfo.com/rss/livescores.xml";
				tv.setText("Cricket Live Scores");
				break;
			case 12:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/badminton.xml";
				tv.setText("Badminton News");
				break;
			case 13:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/tennis.xml";
				tv.setText("Tennis News");
				break;
			case 14:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/football.xml";
				tv.setText("Football News");
				break;
			case 15:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/hockey.xml";
				tv.setText("Hockey News");
				break;
			case 16:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/golf.xml";
				tv.setText("Golf News");
				break;
			case 17:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/shooting.xml";
				tv.setText("Shooting News");
				break;
			case 18:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/sports/othersports.xml";
				tv.setText("Other Sports");
				break;
			// Bollywood
			case 20:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/news_hindi.xml";
				tv.setText("Bollywood News");
				break;
			case 21:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/reviews_hindi.xml";
				tv.setText("Bollywood Reviews");
				break;
			// case 22:
			// BaseFeedParser.feedUrlString =
			// "http://www.nowrunning.com/cgi-bin/rss/previews_hindi.xml";
			// tv.setText("Bollywood Previews");
			// break;
			case 23:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/bollywoodevents.xml";
				tv.setText("Bollywood Events");
				break;
			case 24:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/moviestills_hindi.xml";
				tv.setText("Movie Stills");
				break;

			// Hollywood
			case 30:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/news_english.xml";
				tv.setText("Hollywood News");
				break;
			case 31:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/reviews_english.xml";
				tv.setText("Hollywood Reviews");
				break;
			case 32:
				BaseFeedParser.feedUrlString = "http://www.nowrunning.com/cgi-bin/rss/previews_english.xml";
				tv.setText("Hollywood Previews");
				break;

			// Weather Zone News
			case 04:
				BaseFeedParser.feedUrlString = "http://webservice.weatherzone.com.au/rss/wx.php?news=1";
				tv.setText("Weather Reports");
				break;
			// Technology
			case 05:
				BaseFeedParser.feedUrlString = "http://feeds.reuters.com/reuters/INtechnologyNews?format=xml";
				tv.setText("Technology");
				break;
			// Business
			case 06:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/rss/business/business.xml";
				tv.setText("Business");
				break;
			// Medicare
			case 80:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/general?format=xml";
				tv.setText("Health");
				break;
			case 81:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/cancer?format=xml";
				tv.setText("Cancer");
				break;
			case 82:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/swineflu?format=xml";
				tv.setText("Cold and Flu");
				break;
			case 83:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/mentalhealthstressandgrief?format=xml";
				tv.setText("Mental Health,Stress & Grief");
				break;
			case 84:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/diabetes?format=xml";
				tv.setText("Diabetes");
				break;
			case 85:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/heart?format=xml";
				tv.setText("Heart");
				break;
			case 86:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/healthypregnancy?format=xml";
				tv.setText("Healthy Pregnancy");
				break;
			case 87:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/painmanagement?format=xml";
				tv.setText("Pain Management");
				break;
			case 88:
				BaseFeedParser.feedUrlString = "http://feeds.myoptumhealth.com/portal/feeds/respiratorydiseases?format=xml";
				tv.setText("Respiratory Diseases");
				break;
			// Blogging
			case 54:
				BaseFeedParser.feedUrlString = "http://ibnlive.in.com/xml/allblogsauthor.xml";
				tv.setText("Blogging");
				break;
			// Magazines
			case 55:
				BaseFeedParser.feedUrlString = "http://feeds.bbci.co.uk/news/magazine/rss.xml";
				tv.setText("Magazines");
				break;

			// Job search

			case 100:
				BaseFeedParser.feedUrlString = "http://www.jobs.technowebworld.com/feed/";
				tv.setText("Goverment Jobs in India");
				break;
			case 101:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=7";
				tv.setText("Finance and Accounts");
				break;
			case 102:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=2";
				tv.setText("Banking Insurance");
				break;
			case 103:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=1000";
				tv.setText("Education/Teaching");
				break;
			case 104:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=10";
				tv.setText("Hotels/Restaurants");
				break;
			case 105:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=23";
				tv.setText("Telecom/ISP");
				break;
			case 106:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=20";
				tv.setText("Sales/Business");
				break;
			case 107:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=19";
				tv.setText("Retails Chain");
				break;
			case 108:
				BaseFeedParser.feedUrlString = "http://jobsearch.monsterindia.com/category/rss_jobs.html?cat=15";
				tv.setText("Others");
				break;
			case 113:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/qotd_pics?format=xml";
				tv.setText("Quotes of the day");
				break;
			case 114:
				BaseFeedParser.feedUrlString = "http://my.horoscope.com/astrology/daily-horoscopes-rss.html";
				tv.setText("Horoscopes");
				break;
			case 115:
				BaseFeedParser.feedUrlString = "http://www.1888articles.com/rss/latest-articles.xml";
				tv.setText("Latest Articles");
				break;
			case 116:
				BaseFeedParser.feedUrlString = "http://www.allydirectory.com/Biographies/feed/";
				tv.setText("Biographies");
				break;

			case 120:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/brainyquote/QUOTEBR";
				tv.setText("Quote of the day");
				break;
			case 121:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/brainyquote/QUOTELO";
				tv.setText("Love Quote of the day");
				break;
			case 122:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/brainyquote/QUOTEFU";
				tv.setText("Funny Quote of the day");
				break;
			case 123:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/brainyquote/QUOTEAR";
				tv.setText("Art Quote of the day");
				break;
			case 124:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/brainyquote/QUOTENA";
				tv.setText("Nature Quote of the day");
				break;
			case 125:
				BaseFeedParser.feedUrlString = "http://wordsmith.org/awad/rss1.xml";
				tv.setText("Today's Word");
				break;
			case 131:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/India365?format=xml";
				tv.setText("IT Gadgets");
				break;
			case 132:
				BaseFeedParser.feedUrlString = "http://feeds.feedburner.com/Makeuseof?format=xml";
				tv.setText("IT Tips");
				break;

			default:
				break;
			}
			BaseFeedParser parser = new BaseFeedParser();
			messages = parser.parse();
			switch (a) {
			case 20:
			case 21:
			case 23:
			case 24:
			case 30:
			case 31:
			case 54:
			case 55:
			case 32:
			case 100:
			case 116:
			case 131:
			case 132:
				List<String> titles = new ArrayList<String>(messages.size());
				int i = 0;
				for (Message msg : messages) {
					titles.add(msg.getTitle());
					rd = new RowData(i, msg.getTitle(), msg.getDate());
					data.add(rd);
					i++;
				}
				adapter2 = new CustomAdapter2(this, R.layout.row2, R.id.title,
						data);
				break;
			case 04:
			case 11:
			case 101:
			case 102:
			case 103:
			case 104:
			case 105:
			case 106:
			case 107:
			case 108:
			case 114:
			case 115:
			case 120:
			case 121:
			case 122:
			case 123:
			case 124:
			case 125:
				// List<String> titles2 = new
				// ArrayList<String>(messages.size());
				// for (Message msg : messages){
				// titles2.add(msg.getTitle());
				// }
				// adaptersimple = new ArrayAdapter<String>(this,
				// android.R.layout.simple_list_item_1,titles2);
				List<String> titles2 = new ArrayList<String>(messages.size());
				int i1 = 0;
				for (Message msg : messages) {
					titles2.add(msg.getTitle());
					rd3 = new RowData3(i1, msg.getTitle());
					data3.add(rd3);
					i1++;
				}
				adapter3 = new CustomAdapter3(this, R.layout.row2, R.id.title,
						data3);

				break;

			default:
				List<String> titles1 = new ArrayList<String>(messages.size());
				int i11 = 0;
				for (Message msg : messages) {
					titles1.add(msg.getTitle());
					rd = new RowData(i11, msg.getTitle(), msg.getDate());
					data.add(rd);
					i11++;
				}
				adapter = new CustomAdapter(this, R.layout.row2, R.id.title,
						data);
				break;
			}

		} catch (Throwable t) {
			Log.e("Android News", t.getMessage(), t);
		}
	}

	class Pdialog extends AsyncTask<Void, String, Void> {
		ProgressDialog _prog_diag;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			_prog_diag = ProgressDialog.show(MessageList.this, "",
					"Please wait ....", true, true);
			_prog_diag.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			loadFeed();
			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {

			switch (a) {
			case 20:
			case 21:
			case 100:
			case 23:
			case 24:
			case 30:
			case 31:
			case 32:
			case 54:
			case 55:
			case 131:
			case 132:
			case 116:
				setListAdapter(adapter2);
				getListView().setTextFilterEnabled(true);
				break;

			case 04:
			case 11:
			case 101:
			case 102:
			case 103:
			case 104:
			case 105:
			case 106:
			case 107:
			case 108:
			case 114:
			case 115:
			case 120:
			case 121:
			case 122:
			case 123:
			case 124:
			case 125:
				setListAdapter(adapter3);
				// getListView().setTextFilterEnabled(true);
				break;

			default:
				setListAdapter(adapter);
				getListView().setTextFilterEnabled(true);
				break;
			}
			_prog_diag.dismiss();
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		final URL link = messages.get(position).getLink();
		Intent viewMessage = new Intent(Intent.ACTION_VIEW, Uri.parse(messages
				.get(position).getLink().toExternalForm()));
		this.startActivity(viewMessage);

		l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v, int pos,
					long id) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_SUBJECT,
						getString(R.string.promomailsubjectinfo));
				i.putExtra(Intent.EXTRA_TEXT, link.toString());
				startActivity(Intent.createChooser(i, "Share this News via"));
				return true;
			}
		});

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	private class CustomAdapter extends ArrayAdapter<RowData> {
		public CustomAdapter(Context context, int resource,
				int textViewResourceId, List<RowData> objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			TextView title = null;
			TextView detail = null;
			ImageView i11 = null;
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.row2, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.mTitle);
			detail = holder.getdetail();
			detail.setText(rowData.mDetail);
			i11 = holder.getImage();
			String des = messages.get(rowData.mId).getDescription();
			String Url = GetUrl(des);
			Drawable drawable = LoadImageFromWebOperations(Url);
			if (drawable == null) {
				i11.setImageResource(R.drawable.extra);
			} else {
				i11.setImageDrawable(drawable);
			}
			return convertView;
		}

		private class ViewHolder {
			private View mRow;
			private TextView title = null;
			private TextView detail = null;
			private ImageView i11 = null;

			public ViewHolder(View row) {
				mRow = row;
			}

			public TextView gettitle() {
				if (null == title) {
					title = (TextView) mRow.findViewById(R.id.title);
				}
				return title;
			}

			public TextView getdetail() {
				if (null == detail) {
					detail = (TextView) mRow.findViewById(R.id.detail);
				}
				return detail;
			}

			public ImageView getImage() {
				if (null == i11) {
					i11 = (ImageView) mRow.findViewById(R.id.img);
				}
				return i11;
			}
		}
	}

	private class CustomAdapter1 extends ArrayAdapter<RowData> {
		public CustomAdapter1(Context context, int resource,
				int textViewResourceId, List<RowData> objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			TextView title = null;
			TextView detail = null;
			ImageView i11 = null;
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.row2, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.mTitle);
			detail = holder.getdetail();
			detail.setText(rowData.mDetail);
			i11 = holder.getImage();
			// String des =messages.get(rowData.mId).getDescription();
			String des = messages.get(rowData.mId).getMediaThumbnail();
			String Url = GetUrl(des);
			Drawable drawable = LoadImageFromWebOperations(Url);
			if (drawable == null) {
				i11.setImageResource(R.drawable.extra);
			} else {
				i11.setImageDrawable(drawable);
			}
			return convertView;
		}

		private class ViewHolder {
			private View mRow;
			private TextView title = null;
			private TextView detail = null;
			private ImageView i11 = null;

			public ViewHolder(View row) {
				mRow = row;
			}

			public TextView gettitle() {
				if (null == title) {
					title = (TextView) mRow.findViewById(R.id.title);
				}
				return title;
			}

			public TextView getdetail() {
				if (null == detail) {
					detail = (TextView) mRow.findViewById(R.id.detail);
				}
				return detail;
			}

			public ImageView getImage() {
				if (null == i11) {
					i11 = (ImageView) mRow.findViewById(R.id.img);
				}
				return i11;
			}
		}
	}

	private class CustomAdapter2 extends ArrayAdapter<RowData> {
		public CustomAdapter2(Context context, int resource,
				int textViewResourceId, List<RowData> objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			TextView title = null;
			TextView detail = null;
			ImageView i11 = null;
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.row2, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.mTitle);
			detail = holder.getdetail();
			detail.setText(rowData.mDetail);
			i11 = holder.getImage();
			// String des =messages.get(rowData.mId).getDescription();
			// String Url = GetUrl(des);
			// Drawable drawable = LoadImageFromWebOperations(Url);
			i11.setImageResource(R.drawable.extra);
			return convertView;
		}

		private class ViewHolder {
			private View mRow;
			private TextView title = null;
			private TextView detail = null;
			private ImageView i11 = null;

			public ViewHolder(View row) {
				mRow = row;
			}

			public TextView gettitle() {
				if (null == title) {
					title = (TextView) mRow.findViewById(R.id.title);
				}
				return title;
			}

			public TextView getdetail() {
				if (null == detail) {
					detail = (TextView) mRow.findViewById(R.id.detail);
				}
				return detail;
			}

			public ImageView getImage() {
				if (null == i11) {
					i11 = (ImageView) mRow.findViewById(R.id.img);
				}
				return i11;
			}
		}
	}

	private class CustomAdapter3 extends ArrayAdapter<RowData3> {
		public CustomAdapter3(Context context, int resource,
				int textViewResourceId, List<RowData3> objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			TextView title = null;

			ImageView i11 = null;
			RowData3 rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.row2, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.mTitle);
			// detail = holder.getdetail();
			// detail.setText(rowData.mDetail);
			i11 = holder.getImage();
			// String des =messages.get(rowData.mId).getDescription();
			// String Url = GetUrl(des);
			// Drawable drawable = LoadImageFromWebOperations(Url);
			i11.setImageResource(R.drawable.extra);
			return convertView;
		}

		private class ViewHolder {
			private View mRow;
			private TextView title = null;
			private ImageView i11 = null;

			public ViewHolder(View row) {
				mRow = row;
			}

			public TextView gettitle() {
				if (null == title) {
					title = (TextView) mRow.findViewById(R.id.title);
				}
				return title;
			}

			public ImageView getImage() {
				if (null == i11) {
					i11 = (ImageView) mRow.findViewById(R.id.img);
				}
				return i11;
			}
		}
	}

	private class RowData {
		protected int mId;
		protected String mTitle;
		protected String mDetail;

		RowData(int id, String title, String detail) {
			mId = id;
			mTitle = title;
			mDetail = detail;
		}

		@Override
		public String toString() {
			return mId + " " + mTitle + " " + mDetail;
		}
	}

	private class RowData3 {
		protected int mId;
		protected String mTitle;

		RowData3(int id, String title) {
			mId = id;
			mTitle = title;

		}

		@Override
		public String toString() {
			return mId + " " + mTitle;
		}
	}

	private Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		} catch (Exception e) {
			System.out.println("Exc=" + e);
			return null;
		}
	}

	private String GetUrl(String str) {
		// TODO Auto-generated method stub
		String sub = null;
		int a = str.indexOf("http://");
		String substring = str.substring(a, str.length());
		int b, c = 0;
		if (substring.contains("jpg")) {
			b = substring.indexOf("jpg");
			c = b + 3;
		} else if (substring.contains("png")) {
			b = substring.indexOf("png");
			c = b + 3;
		} else if (substring.contains("gif")) {
			b = substring.indexOf("gif");
			c = b + 3;
		} else if (substring.contains("jpeg")) {
			b = substring.indexOf("jpeg");
			c = b + 4;
		}
		sub = substring.substring(0, c);
		return sub;
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

		case R.id.share:
			Intent i1 = new Intent(MessageList.this, Share.class);
			startActivity(i1);
			return true;

		case R.id.help:
			LayoutInflater factory = LayoutInflater.from(MessageList.this);
			View helpView = factory.inflate(R.layout.help, null);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MessageList.this).setIcon(R.drawable.help).setTitle("Help")
					.setView(helpView).setPositiveButton(
							R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked OK so do some stuff */
								}
							})

			;
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		case R.id.extra:
			LayoutInflater factoryapp = LayoutInflater.from(MessageList.this);
			View appView = factoryapp.inflate(R.layout.aboutapp, null);
			AlertDialog.Builder builderapp = new AlertDialog.Builder(
					MessageList.this).setIcon(R.drawable.about).setTitle(
					"About App").setView(appView).setPositiveButton(
					R.string.alert_dialog_ok,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int whichButton) {

							/* User clicked OK so do some stuff */
						}
					})

			;
			AlertDialog alertapp = builderapp.create();
			alertapp.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
