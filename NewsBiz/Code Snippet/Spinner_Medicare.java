package com.techfest.appsurd.newsbiz;

import com.techfest.appsurd.newsbiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Spinner_Medicare extends Activity implements
		AdapterView.OnItemSelectedListener {
	TextView selection;
	private int pos;
	String[] items = { "Health", "Cancer", "Cold and Flu",
			"Mental Health,Stress & Grief", "Diabetes", "Heart",
			"Healthy Pregnancy", "Pain Management", "Respiratory Diseases" };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.spinner);

		// selection=(TextView)findViewById(R.id.selection);

		Spinner spin = (Spinner) findViewById(R.id.spinn);
		spin.setOnItemSelectedListener(this);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		aa
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);

		// selection.setText(items[position]);

		findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (pos) {
				case 0:
					Intent i0 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i0.putExtra("index", 80);
					startActivity(i0);
					break;
				case 1:
					Intent i1 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i1.putExtra("index", 81);
					startActivity(i1);
					break;
				case 2:
					Intent i2 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i2.putExtra("index", 82);
					startActivity(i2);
					break;
				case 3:
					Intent i3 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i3.putExtra("index", 83);
					startActivity(i3);
					break;
				case 4:
					Intent i4 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i4.putExtra("index", 84);
					startActivity(i4);
					break;
				case 5:
					Intent i5 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i5.putExtra("index", 85);
					startActivity(i5);
					break;
				case 6:
					Intent i6 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i6.putExtra("index", 86);
					startActivity(i6);
					break;
				case 7:
					Intent i7 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i7.putExtra("index", 87);
					startActivity(i7);
					break;
				case 8:
					Intent i8 = new Intent(Spinner_Medicare.this,
							MessageList.class);
					i8.putExtra("index", 88);
					startActivity(i8);
					break;
				default:
					break;
				}
			}
		});
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		pos = position;
	}

	public void onNothingSelected(AdapterView<?> parent) {
		selection.setText("");
	}
}
