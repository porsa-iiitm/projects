package com.prankul.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {
	
	public static String userId;
	EditText user_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		user_id=(EditText) findViewById(R.id.user_id);
		Button simpleLogin = (Button) findViewById(R.id.simple_login);
		simpleLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				userId=user_id.getText().toString().trim();
//				userId="prankulgarg@outlook.com";
				if(userId!=null)
				{
//					Toast.makeText(getApplicationContext(),userId, Toast.LENGTH_SHORT).show();
					Intent i = new Intent(Login.this, TaskActivity.class );
					i.putExtra("userId", userId);
					startActivity(i);
				}
				else
				{
					Toast.makeText(getApplicationContext(),"Email Id is required", Toast.LENGTH_SHORT).show();
				}		
			}
		});
		
		
		Button googleLogin = (Button) findViewById(R.id.google_login);
		googleLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Call Google Login API and extract emailId from response and pass emailId to Tasks.java
				
				Toast.makeText(getApplicationContext(),"Yet to Integrate, Enter Email ID", Toast.LENGTH_SHORT).show();
				
//				String userId=null;
//				Intent i = new Intent(Login.this, Tasks.class );
//				i.putExtra("userId", userId);
//				startActivity(i);
	
			}
		});
		
		
		
	}
}
