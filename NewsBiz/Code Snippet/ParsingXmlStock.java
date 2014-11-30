package com.techfest.appsurd.newsbiz;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.techfest.appsurd.newsbiz.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ParsingXmlStock extends Activity {

	private final String MY_DEBUG_TAG = "StockMarket";
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	private TextView tv6;
	private TextView tv7;
	private TextView tv8;
	private TextView tv9;
	private TextView tv10;
	private TextView tv11;
	private TextView tv12;
	private TextView tv13;
	private TextView tv14;
	private TextView tv15;
	private TextView tv16;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.parsingxmlstock);

		/* Create a new TextView to display the parsingresult later. */
		tv1 = (TextView) findViewById(R.id.symbol);
		tv2 = (TextView) findViewById(R.id.last);
		tv3 = (TextView) findViewById(R.id.date);
		tv4 = (TextView) findViewById(R.id.time);
		tv5 = (TextView) findViewById(R.id.change);
		tv6 = (TextView) findViewById(R.id.open);
		tv7 = (TextView) findViewById(R.id.high);
		tv8 = (TextView) findViewById(R.id.low);
		tv9 = (TextView) findViewById(R.id.volume);
		tv10 = (TextView) findViewById(R.id.mktcap);

		tv11 = (TextView) findViewById(R.id.previousclose);
		tv12 = (TextView) findViewById(R.id.percentagechange);
		tv13 = (TextView) findViewById(R.id.annrange);
		tv14 = (TextView) findViewById(R.id.earns);
		tv15 = (TextView) findViewById(R.id.pe);
		tv16 = (TextView) findViewById(R.id.name);

		try {
			/* Create a URL we want to load some xml-data from. */
			// URL url = new
			// URL("http://www.anddev.org/images/tut/basic/parsingxml/example.xml");
			Bundle extras = getIntent().getExtras();
			String str = extras.getString("stockmarket");

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xrd = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			ExampleHandlerStock myExampleHandlerstk = new ExampleHandlerStock();
			xrd.setContentHandler(myExampleHandlerstk);

			InputStream is = new ByteArrayInputStream(str.getBytes());

			/* Parse the xml-data from our URL. */
			xrd.parse(new InputSource(is));
			/* Parsing has finished. */

			/* Our ExampleHandler now provides the parsed data to us. */
			ParsedExampleDataSetStock parsedExampleDataSetstk = myExampleHandlerstk
					.getParsedStkData();

			/* Set the result to be displayed in our GUI. */
			tv1.setText(parsedExampleDataSetstk.tolocation());
			tv2.setText(parsedExampleDataSetstk.totime());
			tv3.setText(parsedExampleDataSetstk.towind());
			tv4.setText(parsedExampleDataSetstk.tovisibility() + " EDT");
			tv5.setText(parsedExampleDataSetstk.toskyconditions());
			tv6.setText(parsedExampleDataSetstk.totemperature());
			tv7.setText(parsedExampleDataSetstk.todewpoint());
			tv8.setText(parsedExampleDataSetstk.torelativehumidity());
			tv9.setText(parsedExampleDataSetstk.topressure());
			tv10.setText(parsedExampleDataSetstk.tostatus());

			tv11.setText(parsedExampleDataSetstk.toprevclose());
			tv12.setText(parsedExampleDataSetstk.topercentagechg());
			tv13.setText(parsedExampleDataSetstk.toannrange());
			tv14.setText(parsedExampleDataSetstk.toearns());
			tv15.setText(parsedExampleDataSetstk.tope());
			tv16.setText(parsedExampleDataSetstk.toname());

		} catch (Exception e) {
			/* Display any Error to the GUI. */
			tv1.setText("Error: " + e.getMessage());
			Log.e(MY_DEBUG_TAG, "StockQueryError", e);
		}
		/* Display the TextView. */
		// this.setContentView(tv1);
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
			Intent i1 = new Intent(ParsingXmlStock.this, Share.class);
			startActivity(i1);
			return true;

		case R.id.help:
			LayoutInflater factory = LayoutInflater.from(ParsingXmlStock.this);
			View helpView = factory.inflate(R.layout.help, null);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					ParsingXmlStock.this).setIcon(R.drawable.help).setTitle(
					"Help").setView(helpView).setPositiveButton(
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
			LayoutInflater factoryapp = LayoutInflater
					.from(ParsingXmlStock.this);
			View appView = factoryapp.inflate(R.layout.aboutapp, null);
			AlertDialog.Builder builderapp = new AlertDialog.Builder(
					ParsingXmlStock.this).setIcon(R.drawable.about).setTitle(
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