package com.techfest.appsurd.newsbiz;

import com.techfest.appsurd.newsbiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Currency_Graph extends Activity implements
		AdapterView.OnItemSelectedListener {

	String[] items = { "ALL-Albanian Lek", "DZD-Algerian Dinar",
			"ARS-Argentine Peso", "AWG-Aruba Florin", "AUD-Australian Dollar",
			"AFA-Afghanistan Afghani", "BSD-Bahamian Dollar",
			"BHD-Bahraini Dinar", "BDT-Bangladesh Taka", "BBD-Barbados Dollar",
			"BZD-Belize Dollar", "BMD-Bermuda Dollar", "BTN-Bhutan Ngultrum",
			"BOB-Bolivian Boliviano", "BWP-Botswana Pula",
			"BRL-Brazilian Real", "GBP-British Pound", "BND-Brunei Dollar",
			"BIF-Burundi Franc", "XOF-CFA Franc (BCEAO)",
			"XAF-CFA Franc (BEAC)", "KHR-Cambodia Riel", "CAD-Canadian Dollar",
			"CVE-Cape Verde Escudo", "KYD-Cayman Islands Dollar",
			"CLP-Chilean Peso", "CNY-Chinese Yuan", "COP-Colombian Peso",
			"KMF-Comoros Franc", "CRC-Costa Rica Colon", "HRK-Croatian Kuna",
			"CUP-Cuban Peso", "CYP-Cyprus Pound", "CZK-Czech Koruna",
			"DKK-Danish Krone", "DJF-Dijibouti Franc", "DOP-Dominican Peso",
			"XCD-East Caribbean Dollar", "EGP-Egyptian Pound",
			"SVC-El Salvador Colon", "EEK-Estonian Kroon",
			"ETB-Ethiopian Birr", "EUR-Euro", "FKP-Falkland Islands Pound",
			"GMD-Gambian Dalasi", "GHC-Ghanian Cedi", "GIP-Gibraltar Pound",
			"XAU-Gold Ounces", "GTQ-Guatemala Quetzal", "GNF-Guinea Franc",
			"GYD-Guyana Dollar", "HTG-Haiti Gourde", "HNL-Honduras Lempira",
			"HKD-Hong Kong Dollar", "HUF-Hungarian Forint",
			"ISK-Iceland Krona", "INR-Indian Rupee", "IDR-Indonesian Rupiah",
			"IQD-Iraqi Dinar", "ILS-Israeli Shekel", "JMD-Jamaican Dollar",
			"JPY-Japanese Yen", "JOD-Jordanian Dinar", "KZT-Kazakhstan Tenge",
			"KES-Kenyan Shilling", "KRW-Korean Won", "KWD-Kuwaiti Dinar",
			"LAK-Lao Kip", "LVL-Latvian Lat", "LBP-Lebanese Pound",
			"LSL-Lesotho Loti", "LRD-Liberian Dollar", "LYD-Libyan Dinar",
			"LTL-Lithuanian Lita", "MOP-Macau Pataca", "MKD-Macedonian Denar",
			"MGF-Malagasy Franc", "MWK-Malawi Kwacha", "MYR-Malaysian Ringgit",
			"MVR-Maldives Rufiyaa", "MTL-Maltese Lira",
			"MRO-Mauritania Ougulya", "MUR-Mauritius Rupee",
			"MXN-Mexican Peso", "MDL-Moldovan Leu", "MNT-Mongolian Tugrik",
			"MAD-Moroccan Dirham", "MZM-Mozambique Metical",
			"MMK-Myanmar Kyat", "NAD-Namibian Dollar", "NPR-Nepalese Rupee",
			"ANG-Neth Antilles Guilder", "NZD-New Zealand Dollar",
			"NIO-Nicaragua Cordoba", "NGN-Nigerian Naira",
			"KPW-North Korean Won", "NOK-Norwegian Krone", "OMR-Omani Rial",
			"XPF-Pacific Franc", "PKR-Pakistani Rupee", "XPD-Palladium Ounces",
			"PAB-Panama Balboa", "PGK-Papua New Guinea Kina",
			"PYG-Paraguayan Guarani", "PEN-Peruvian Nuevo Sol",
			"PHP-Philippine Peso", "XPT-Platinum Ounces", "PLN-Polish Zloty",
			"QAR-Qatar Rial", "ROL-Romanian Leu", "RUB-Russian Rouble",
			"WST-Samoa Tala", "STD-Sao Tome Dobra", "SAR-Saudi Arabian Riyal",
			"SCR-Seychelles Rupee", "SLL-Sierra Leone Leone",
			"XAG-Silver Ounces", "SGD-Singapore Dollar", "SKK-Slovak Koruna",
			"SIT-Slovenian Tolar", "SBD-Solomon Islands Dollar",
			"SOS-Somali Shilling", "ZAR-South African Rand",
			"LKR-Sri Lanka Rupee", "SHP-St Helena Pound", "SDD-Sudanese Dinar",
			"SRG-Surinam Guilder", "SZL-Swaziland Lilageni",
			"SEK-Swedish Krona", "TRY-Turkey Lira", "CHF-Swiss Franc",
			"SYP-Syrian Pound", "TWD-Taiwan Dollar", "TZS-Tanzanian Shilling",
			"THB-Thai Baht", "TOP-Tonga Pa'anga",
			"TTD-Trinidad&amp;Tobago Dollar", "TND-Tunisian Dinar",
			"TRL-Turkish Lira", "USD-U.S. Dollar", "AED-UAE Dirham",
			"UGX-Ugandan Shilling", "UAH-Ukraine Hryvnia",
			"UYU-Uruguayan New Peso", "VUV-Vanuatu Vatu",
			"VEB-Venezuelan Bolivar", "VND-Vietnam Dong", "YER-Yemen Riyal",
			"YUM-Yugoslav Dinar", "ZMK-Zambian Kwacha", "ZWD-Zimbabwe Dollar" };
	String[] items1 = { "Weak", "Month", "3 Months", "6 Months", "1 Year" };
	String[] days = { "7", "31", "91", "183", "365" };
	String url;
	private Spinner spin1;
	private Spinner spin2;
	private Spinner spin3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currency_graph);
		spin1 = (Spinner) findViewById(R.id.SpinnerFrom);
		spin1.setOnItemSelectedListener(this);
		spin2 = (Spinner) findViewById(R.id.SpinnerTo);
		spin2.setOnItemSelectedListener(this);
		spin3 = (Spinner) findViewById(R.id.SpinnerLast);
		spin3.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		aa
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(aa);
		spin2.setAdapter(aa);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items1);
		a
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin3.setAdapter(a);
		url = "http://www.chartflow.com/chartGen/lineChartGen.asp?" + "ccy1="
				+ spin1.getSelectedItem().toString().substring(0, 3) + "&ccy2="
				+ spin2.getSelectedItem().toString().substring(0, 3) + "&days="
				+ days[spin3.getSelectedItemPosition()] + "&sz=468x210&sys=oz";
		/*
		 * from = spin1.getSelectedItem().toString().substring(0, 3); to =
		 * spin2.getSelectedItem().toString().substring(0, 3); pos =
		 * spin3.getSelectedItemPosition(); day = days[pos]; add = "ccy1=" +
		 * from + "&ccy2=" + to + "&days=" + day + "&sz=468x210&sys=oz"; url =
		 * default1 + add;
		 */
		/*
		 * findViewById(R.id.startover).setOnClickListener( new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent i = new Intent(Currency_Graph.this,
		 * Spinner_Realtime.class); startActivity(i); } });
		 */

		findViewById(R.id.done1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				url = "http://www.chartflow.com/chartGen/lineChartGen.asp?"
						+ "ccy1="
						+ spin1.getSelectedItem().toString().substring(0, 3)
						+ "&ccy2="
						+ spin2.getSelectedItem().toString().substring(0, 3)
						+ "&days=" + days[spin3.getSelectedItemPosition()]
						+ "&sz=468x210&sys=oz";
				// TODO Auto-generated method stub
				/*
				 * Toast.makeText(getApplicationContext(), url,
				 * Toast.LENGTH_LONG) .show();
				 */

				Intent i = new Intent(Currency_Graph.this, ViewGraph.class);
				i.putExtra("url", url);
				startActivity(i);

			}
		});
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		// selection.setText(items[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// selection.setText("");
	}
}
