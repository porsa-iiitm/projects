package com.prankul.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddTask extends Activity implements OnItemSelectedListener{
	
	EditText editTitle,editDescription;
	String priority,status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task);
		
		editTitle=(EditText) findViewById(R.id.edit_title);
		editDescription=(EditText) findViewById(R.id.edit_description);
		
		//Priority Spinner
		Spinner priority_spinner = (Spinner) findViewById(R.id.priority_spinner);
		priority_spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> priority_adapter = ArrayAdapter.createFromResource(this,
		        R.array.priority_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		priority_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		priority_spinner.setAdapter(priority_adapter);
		
		
		//Status Spinner
		Spinner status_spinner = (Spinner) findViewById(R.id.status_spinner);
		status_spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> status_adapter = ArrayAdapter.createFromResource(this,
		        R.array.status_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		status_spinner.setAdapter(status_adapter);

		
		
		
	}
	
	public void onSave(View v)
	{
		//Save Task
		String URL=Utillities.BASE_URL+"task/add?title="+editTitle.getText().toString().trim()+"&description="+
				editDescription.getText().toString().trim()+"&status="+status+"&priority="+priority+
				"&email="+Login.userId;
		URL=URL.replaceAll(" ", "%20");
		String title=editTitle.getText().toString().trim();
		String description=editDescription.getText().toString().trim();
		
		if(!title.isEmpty() && title!=null && !description.isEmpty() && description!=null)
		{
			Log.e("url ", URL);
			RestApi restApi = new RestApi(AddTask.this);
			restApi.addTask(URL);
			Utillities.showProgressDialog(this, "Adding Task...");
		}
		else
			Toast.makeText(AddTask.this,"Enter All Fields", Toast.LENGTH_SHORT).show();

	
	}
	
	public void onCancel(View v)
	{
		//Cancel Task
		AddTask.this.finish();
	}
	
	
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		  switch (parent.getId()) 
		    {         
		        case R.id.priority_spinner:
		        	//do this         
			    	 priority = parent.getItemAtPosition(position).toString();
			    //	 Toast.makeText(getApplicationContext(),item, Toast.LENGTH_SHORT).show();
		            break;              

		        case R.id.status_spinner:
		        	//do this
			    	 status = parent.getItemAtPosition(position).toString();
			    //	 Toast.makeText(getApplicationContext(),item1, Toast.LENGTH_SHORT).show();
		            break;              
		    }
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
