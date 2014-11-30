package com.ndroid.calcplusplus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class Calc extends Activity implements
		RadioGroup.OnCheckedChangeListener {
	public static TextView InputText;
	public static TextView OutputText;
	GridView mKeypadGrid, mKeypadGrid2;
	KeypadAdapter mKeypadAdapter;
	KeypadAdapter2 mKeypadAdapter2;
	public String ans = new String();
	public static int deg = 1;
	public static int hlp = 1;
	public static int cond = 1;

	static double m1 = 0.0;
	int k = 1;
	RadioButton c1, c2;
	RadioGroup rg;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Get reference to the user input TextView
		InputText = (TextView) findViewById(R.id.txtInput);
		InputText.setText("");

		OutputText = (TextView) findViewById(R.id.txtStack);
		OutputText.setText("");

		c1 = (RadioButton) findViewById(R.id.radiobutton1);
		c2 = (RadioButton) findViewById(R.id.radiobutton2);

		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		rg.setOnCheckedChangeListener(this);

		function1();

		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				function1();

			}
		});

		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				function2();

			}
		});
	}

	void function1() {

		// Get reference to the keypad button GridView
		mKeypadGrid = (GridView) findViewById(R.id.grdButtons);

		// Create Keypad Adapter
		mKeypadAdapter = new KeypadAdapter(this);

		// Set adapter of the keypad grid
		mKeypadGrid.setAdapter(mKeypadAdapter);

		// Set button click listener of the keypad adapter
		mKeypadAdapter.setOnButtonClickListener(new OnClickListener() {
			public void onClick(View v) {
				Button btn = (Button) v;
				// Get the KeypadButton value which is used to identify the
				// keypad button from the Button's tag
				KeypadButton keypadButton = (KeypadButton) btn.getTag();

				// Process keypad button
				try {
					ProcessKeypadInput(keypadButton);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		mKeypadGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		});

	}

	void function2() {
		// Get reference to the keypad button GridView
		mKeypadGrid2 = (GridView) findViewById(R.id.grdButtons);

		// Create Keypad Adapter
		mKeypadAdapter2 = new KeypadAdapter2(this);

		// Set adapter of the keypad grid
		mKeypadGrid2.setAdapter(mKeypadAdapter2);

		// Set button click listener of the keypad adapter
		mKeypadAdapter2.setOnButtonClickListener(new OnClickListener() {
			public void onClick(View v) {
				Button btn = (Button) v;
				// Get the KeypadButton value which is used to identify the
				// keypad button from the Button's tag
				KeypadButton2 keypadButton2 = (KeypadButton2) btn.getTag();

				// Process keypad button
				try {
					ProcessKeypadInput2(keypadButton2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		mKeypadGrid2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		});

	}

	private void ProcessKeypadInput(KeypadButton keypadButton) {
		switch (keypadButton) {
		case ZERO:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("0");
			} else
				InputText.append("0");
			break;
		case ONE:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("1");
			} else
				InputText.append("1");
			break;
		case TWO:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("2");
			} else
				InputText.append("2");
			break;
		case THREE:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("3");
			} else
				InputText.append("3");
			break;
		case FOUR:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("4");
			} else
				InputText.append("4");
			break;
		case FIVE:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("5");
			} else
				InputText.append("5");
			break;
		case SIX:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("6");
			} else
				InputText.append("6");
			break;
		case SEVEN:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("7");
			} else
				InputText.append("7");
			break;
		case EIGHT:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("8");
			} else
				InputText.append("8");
			break;
		case NINE:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("9");
			} else
				InputText.append("9");
			break;
		case PLUS:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("+");
			} else
				InputText.append("+");
			break;
		case MINUS:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("-");
			} else
				InputText.append("-");
			break;
		case MULTIPLY:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("*");
			} else
				InputText.append("*");
			break;
		case DIV:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("/");
			} else
				InputText.append("/");
			break;
		case DECIMAL_SEP:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				if (InputText.getText().toString().contains(".")) {
				} else
					InputText.append(".");
			} else {
				if (InputText.getText().toString().contains(".")) {
				} else
					InputText.append(".");
			}
			break;
		case OPEN_BRACKET:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("(");
			} else
				InputText.append("(");
			break;
		case SIGN:
			/**/
			Stack infix1 = new Stack(200);
			Stack post1 = new Stack(200);
			String str1 = new String();
			try {
				str1 = InputText.getText().toString();
				System.out.println(str1 + "  ");
				str1 = str1.replaceAll("arcsin", "a");
				str1 = str1.replaceAll("arccos", ";");
				str1 = str1.replaceAll("arctan", ":");
				str1 = str1.replaceAll("sinh", "q");
				str1 = str1.replaceAll("cosh", "w");
				str1 = str1.replaceAll("tanh", "z");
				str1 = str1.replaceAll("sin", "#");
				str1 = str1.replaceAll("cos", "~");
				str1 = str1.replaceAll("tan", "@");
				str1 = str1.replaceAll("log", "&");
				str1 = str1.replaceAll("inverse", "i");
				str1 = str1.replaceAll("MOD", "m");
				str1 = str1.replaceAll("th root", "r");
				str1 = str1.replaceAll("ln", "`");
				str1 = str1.replaceAll("sqrt", "n");
				str1 = str1.replaceAll("cbrt", "o");
				str1 = str1.replaceAll("sqre", "s");
				str1 = str1.replaceAll("cube", "c");
				str1 = str1.replaceAll("e", "2.718281828459045");
				str1 = str1.replaceAll("pi", "3.141592653589793");

				for (int i = 0; i < str1.length(); i++)
					infix1.push(str1.charAt(i));
				// infix.show();
				post1 = infix1.infipost(post1);
				// post.show();
				if (cond == 2) {
					OutputText.setText("Syntax Error");
					System.out.println(cond);
				} else {
					if (isDouble(str1)) {
						double x = Double.parseDouble(str1);
						x = x * -1;
						InputText.setText(x + "");
					} else {
						double d = post1.posteval();
						d = d * -1;
						InputText.setText(d + "");
						System.out.println(cond);
					}
				}
			} catch (Exception e) {
				OutputText.setText("Syntax Error");
			}
			break;
		case CLOSE_BRACKET:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append(")");
			} else
				InputText.append(")");
			break;

		case MR:
			InputText.setText("");
			InputText.setText(m1 + "");
			break;
		case M_ADD:
			try {
				Stack infix = new Stack(200);
				Stack post = new Stack(200);
				String str = new String();

				str = InputText.getText().toString();
				System.out.println(str + "  ");
				str = str.replaceAll("arcsin", "a");
				str = str.replaceAll("arccos", ";");
				str = str.replaceAll("arctan", ":");
				str = str.replaceAll("sinh", "q");
				str = str.replaceAll("cosh", "w");
				str = str.replaceAll("tanh", "z");
				str = str.replaceAll("sin", "#");
				str = str.replaceAll("cos", "~");
				str = str.replaceAll("tan", "@");
				str = str.replaceAll("inverse", "i");
				str = str.replaceAll("MOD", "m");
				str = str.replaceAll("th root", "r");
				str = str.replaceAll("log", "&");
				str = str.replaceAll("ln", "`");
				str = str.replaceAll("sqrt", "n");
				str = str.replaceAll("cbrt", "o");
				str = str.replaceAll("sqre", "s");
				str = str.replaceAll("cube", "c");
				str = str.replaceAll("e", "2.718281828459045");
				str = str.replaceAll("pi", "3.141592653589793");

				for (int i = 0; i < str.length(); i++)
					infix.push(str.charAt(i));

				if (isDouble(str)) {
					m1 += Double.parseDouble(str);
					InputText.setText("");
					OutputText.setText(str + "");
				} else {
					// infix.show();
					post = infix.infipost(post);
					// post.show();
					double d = post.posteval();
					m1 += d;
					InputText.setText("");
					OutputText.setText(d + "");
				}
			} catch (Exception e) {
				OutputText.setText("Syntax Error");
			}

			break;
		case M_REMOVE:
			try {
				Stack infix = new Stack(200);
				Stack post = new Stack(200);
				String str = new String();

				str = InputText.getText().toString();
				System.out.println(str + "  ");
				str = str.replaceAll("arcsin", "a");
				str = str.replaceAll("arccos", ";");
				str = str.replaceAll("arctan", ":");
				str = str.replaceAll("sinh", "q");
				str = str.replaceAll("cosh", "w");
				str = str.replaceAll("tanh", "z");
				str = str.replaceAll("sin", "#");
				str = str.replaceAll("cos", "~");
				str = str.replaceAll("tan", "@");
				str = str.replaceAll("inverse", "i");
				str = str.replaceAll("MOD", "m");
				str = str.replaceAll("th root", "r");
				str = str.replaceAll("log", "&");
				str = str.replaceAll("ln", "`");
				str = str.replaceAll("sqrt", "n");
				str = str.replaceAll("cbrt", "o");
				str = str.replaceAll("sqre", "s");
				str = str.replaceAll("cube", "c");
				str = str.replaceAll("e", "2.718281828459045");
				str = str.replaceAll("pi", "3.141592653589793");

				for (int i = 0; i < str.length(); i++)
					infix.push(str.charAt(i));

				if (isDouble(str)) {
					m1 -= Double.parseDouble(str);
					InputText.setText("");
				} else {
					// infix.show();
					post = infix.infipost(post);
					// post.show();
					double d = post.posteval();
					m1 -= d;
					InputText.setText("");
				}
			} catch (Exception e) {
				OutputText.setText("Syntax Error");
			}

			break;

		case C:
			InputText.setText("");
			OutputText.setText("");
			m1 = 0;

			break;
		case ANS:
			try {
				Stack infix = new Stack(200);
				Stack post = new Stack(200);
				String str = new String();

				str = InputText.getText().toString();
				System.out.println(str + "  ");
				str = str.replaceAll("arcsin", "a");
				str = str.replaceAll("arccos", ";");
				str = str.replaceAll("arctan", ":");
				str = str.replaceAll("sinh", "q");
				str = str.replaceAll("cosh", "w");
				str = str.replaceAll("tanh", "z");
				str = str.replaceAll("sin", "#");
				str = str.replaceAll("cos", "~");
				str = str.replaceAll("tan", "@");
				str = str.replaceAll("inverse", "i");
				str = str.replaceAll("MOD", "m");
				str = str.replaceAll("th root", "r");
				str = str.replaceAll("log", "&");
				str = str.replaceAll("ln", "`");
				str = str.replaceAll("sqrt", "n");
				str = str.replaceAll("cbrt", "o");
				str = str.replaceAll("sqre", "s");
				str = str.replaceAll("cube", "c");
				str = str.replaceAll("e", "2.718281828459045");
				str = str.replaceAll("pi", "3.141592653589793");

				for (int i = 0; i < str.length(); i++)
					infix.push(str.charAt(i));

				if (isDouble(str))
					InputText.setText(str);
				else {
					// infix.show();
					post = infix.infipost(post);
					// post.show();
					double d = post.posteval();
					InputText.setText(d + "");
				}
			} catch (Exception e) {
				OutputText.setText("Syntax Error");
			}
			break;
		case CALCULATE:

			Stack infix = new Stack(200);
			Stack post = new Stack(200);
			String str = new String();

			str = InputText.getText().toString();
			System.out.println(str + "  ");
			str = str.replaceAll("arcsin", "a");
			str = str.replaceAll("arccos", ";");
			str = str.replaceAll("arctan", ":");
			str = str.replaceAll("sinh", "q");
			str = str.replaceAll("cosh", "w");
			str = str.replaceAll("tanh", "z");
			str = str.replaceAll("sin", "#");
			str = str.replaceAll("cos", "~");
			str = str.replaceAll("tan", "@");
			str = str.replaceAll("log", "&");
			str = str.replaceAll("inverse", "i");
			str = str.replaceAll("MOD", "m");
			str = str.replaceAll("th root", "r");
			str = str.replaceAll("ln", "`");
			str = str.replaceAll("sqrt", "n");
			str = str.replaceAll("cbrt", "o");
			str = str.replaceAll("sqre", "s");
			str = str.replaceAll("cube", "c");
			str = str.replaceAll("e", "2.718281828459045");
			str = str.replaceAll("pi", "3.141592653589793");
			if (MathError(str)) {
				try {
					for (int i = 0; i < str.length(); i++)
						infix.push(str.charAt(i));
					// infix.show();
					post = infix.infipost(post);
					// post.show();
					if (cond == 2) {
						OutputText.setText("Syntax Error");
						System.out.println(cond);
					} else if (isDouble(str)) {
						OutputText.setText(str);
					} else {
						double d = post.posteval();
						OutputText.setText(d + "");
						System.out.println(cond);

					}
				} catch (Exception e) {
					OutputText.setText("Syntax Error");
				}

			} else
				OutputText.setText("Math Error");
			break;
		case BACKSPACE:
			String s = new String(InputText.getText().toString());
			char o[] = new char[(s.length()) - 1];
			for (int i = 0; i < s.length() - 1; i++)
				o[i] = s.charAt(i);
			String s1 = new String(o);
			InputText.setText(s1);
			break;
		default:
			break;

		}
	}

	private void ProcessKeypadInput2(KeypadButton2 keypadButton2) {
		switch (keypadButton2) {
		case BACKSPACE:
			String s = new String(InputText.getText().toString());
			char o[] = new char[(s.length()) - 1];
			for (int i = 0; i < s.length() - 1; i++)
				o[i] = s.charAt(i);
			String s1 = new String(o);
			InputText.setText(s1);
			break;
		case pow:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("^");
			} else
				InputText.append("^");
			break;
		case square:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("sqre");
			} else
				InputText.append("sqre");
			break;
		case cube:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("cube");
			} else
				InputText.append("cube");
			break;
		case sinx:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("sin");
			} else
				InputText.append("sin");
			break;
		case cosx:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("cos");
			} else
				InputText.append("cos");
			break;
		case tanx:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("tan");
			} else
				InputText.append("tan");
			break;
		case acosx:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("arccos");
			} else
				InputText.append("arccos");
			break;
		case asinx:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("arcsin");
			} else
				InputText.append("arcsin");
			break;
		case atanx:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("arctan");
			} else
				InputText.append("arctan");
			break;
		case ln:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("ln");
			} else
				InputText.append("ln");
			break;
		case log:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("log");
			} else
				InputText.append("log");
			break;
		case factorial:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("!");
			} else
				InputText.append("!");
			break;
		case sqrt:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("sqrt");
			} else
				InputText.append("sqrt");
			break;
		case cbrt:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("cbrt");
			} else
				InputText.append("cbrt");
			break;
		case sinh:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("sinh");
			} else
				InputText.append("sinh");
			break;
		case cosh:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("cosh");
			} else
				InputText.append("cosh");
			break;
		case tanh:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("tanh");
			} else
				InputText.append("tanh");
			break;
		case pow_of_e:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("e^");
			} else
				InputText.append("e^");
			break;
		case e:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("e");
			} else
				InputText.append("e");
			break;
		case pi:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("pi");
			} else
				InputText.append("pi");
			break;
		case modulus:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("MOD");
			} else
				InputText.append("MOD");
			break;
		case inverse:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("inverse");
			} else
				InputText.append("inverse");
			break;
		case ROOT:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("th root");
			} else
				InputText.append("th root");
			break;
		case percent:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("%");
			} else
				InputText.append("%");
			break;
		case ncr:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("C");
			} else
				InputText.append("C");
			break;
		case npr:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("P");
			} else
				InputText.append("P");
			break;
		case pow_of_ten:
			if (OutputText.getText().toString() != "") {
				OutputText.setText("");
				InputText.setText("");
				InputText.append("10^");
			} else
				InputText.append("10^");
			break;
		default:
			break;
		}

	}

	public boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean MathError(String str) {
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) == '&' || str.charAt(i) == 'r'
					|| str.charAt(i) == '`' || str.charAt(i) == 'C'
					|| str.charAt(i) == 'P' || str.charAt(i) == 'n' || str
					.charAt(i) == 'o') && (str.charAt(i + 1) == '-')) {
				return false;
			}
			if ((str.charAt(i) == 'C' || str.charAt(i) == 'P')
					&& (str.charAt(i - 1) < str.charAt(i + 1))) {
				return false;
			}
			if ((str.charAt(i) == 'C' || str.charAt(i) == 'P')
					&& (str.charAt(i - 1) <= 0 || str.charAt(i + 1) < 0)) {
				return false;
			}
			/*
			 * if((str.charAt(i)=='n'||str.charAt(i)=='o')) { char ss [] = null
			 * ; ss[0]=str.charAt(i+1); ss[1]='\0'; String jj = ss.toString();
			 * if(!isDouble(jj)) { return false; } }
			 */

		}
		return true;

	}

	public boolean SyntaxError(String str) {
		/*
		 * for(int i =0;i<str.length();i++) {
		 * if((str.charAt(i)=='&'||str.charAt(
		 * i)=='r'||str.charAt(i)=='`'||str.charAt(i)=='C' ||str.charAt(i)=='P'
		 * ||str.charAt(i)=='n'||str.charAt(i)=='o')&&(str.charAt(i+1)=='-')) {
		 * return false; }
		 * 
		 * }
		 */
		return true;

	}

	public void onCheckedChanged(RadioGroup rg, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.radiobutton1:
			deg = 2;
			break;
		case R.id.radiobutton2:
			deg = 1;
			break;
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
			alertbox.setMessage("Do You Want To Quit From Calculator ?");

			// set a positive/yes button and create a listener
			alertbox.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						// do something when the button is clicked
						public void onClick(DialogInterface arg0, int arg1) {
							Intent i = new Intent(
									Calc.this,
									com.ndroid.calcplusplus.Landing.class);
							i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							m1 = 0;
							startActivity(i);
						}
					});
			// set a negative/no button and create a listener
			alertbox.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						// do something when the button is clicked
						public void onClick(DialogInterface arg0, int arg1) {

						}
					});

			// display box
			alertbox.show();
			// finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
			Intent i1 = new Intent(Calc.this,
					com.ndroid.calcplusplus.Share.class);
			startActivity(i1);
			return true;

		case R.id.help:
			LayoutInflater factory = LayoutInflater.from(Calc.this);
			View helpView = factory.inflate(R.layout.help, null);
			AlertDialog.Builder builder = new AlertDialog.Builder(Calc.this)
					.setIcon(R.drawable.help)
					.setTitle("Help")
					.setView(helpView)
					.setPositiveButton(R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
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
			Intent i = new Intent(Calc.this, Extras.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}