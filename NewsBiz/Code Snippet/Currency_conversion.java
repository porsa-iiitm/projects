package com.techfest.appsurd.newsbiz;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.techfest.appsurd.newsbiz.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Currency_conversion extends Activity implements
		AdapterView.OnItemSelectedListener {
	private static final String SOAP_ACTION = "http://www.webserviceX.NET/ConversionRate";
	private static final String METHOD_NAME = "ConversionRate";
	private static final String NAMESPACE = "http://www.webserviceX.NET/";
	private static final String URL = "http://www.webservicex.net/CurrencyConvertor.asmx";
	private TextView ResultView;
	private EditText inputValue;
	String[] items = {

	"ALL-Albanian Lek", "DZD-Algerian Dinar", "ARS-Argentine Peso",
			"AWG-Aruba Florin", "AUD-Australian Dollar",
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
	private Spinner spin1;
	private Spinner spin2;
	private Double it;
	Object response;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currency_conversion);
		spin1 = (Spinner) findViewById(R.id.SpinnerFrom);
		spin1.setOnItemSelectedListener(this);
		spin2 = (Spinner) findViewById(R.id.SpinnerTo);
		spin2.setOnItemSelectedListener(this);
		ResultView = (TextView) findViewById(R.id.TextViewResult);
		ResultView.setClickable(false);
		inputValue = (EditText) findViewById(R.id.EditTextValue);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		aa
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(aa);
		spin2.setAdapter(aa);
		/*
		 * findViewById(R.id.startover).setOnClickListener( new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent i = new Intent(Currency_conversion.this,
		 * Spinner_Realtime.class); startActivity(i); } });
		 * 
		 * findViewById(R.id.done).setOnClickListener(new View.OnClickListener()
		 * {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent i = new Intent(Currency_conversion.this,
		 * ViewGraph.class); startActivity(i); } });
		 */
		findViewById(R.id.Button01).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (isDouble(inputValue.getText().toString())) {
							if (!inputValue.getText().toString().equals("")) {
								new Pdialog().execute();

							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Invalid Input", Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	class Pdialog extends AsyncTask<Void, String, Void> {
		ProgressDialog _prog_diag;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			_prog_diag = ProgressDialog.show(Currency_conversion.this, "",
					"Fetching Result...", true,true);
			_prog_diag.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
			Request.addProperty("FromCurrency", spin1.getSelectedItem()
					.toString().substring(0, 3));
			Request.addProperty("ToCurrency", spin2.getSelectedItem()
					.toString().substring(0, 3));
			SoapSerializationEnvelope soapEnvelope = null;
			soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);

			AndroidHttpTransport aht = new AndroidHttpTransport(URL);

			try {
				aht.call(SOAP_ACTION, soapEnvelope);
				// SoapPrimitive resultString =
				// (SoapPrimitive)soapEnvelope.getResponse();
				SoapObject oResponse = (SoapObject) soapEnvelope.bodyIn;

				response = oResponse.getProperty(0);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {
			_prog_diag.dismiss();
			it = Double.parseDouble(inputValue.getText().toString())
					* Double.parseDouble(response.toString());
			ResultView.setText(it.toString());
		}

	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		// selection.setText(items[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// selection.setText("");
	}

	public boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
}
