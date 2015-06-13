package com.prankul.todo;

import com.prankul.model.Task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class EditTask extends Activity implements OnItemSelectedListener{
	
	EditText editTitle,editDescription;
	String priority,status;
	Task task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task);
		
		Intent intent = getIntent();
		task= (Task)intent.getParcelableExtra("task");
		
		editTitle=(EditText) findViewById(R.id.edit_title);
		editDescription=(EditText) findViewById(R.id.edit_description);
		editTitle.setText(task.getTitle());
		editDescription.setText(task.getDescription());
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
		 if (!task.getPriority().equals(null)) 
		 {
		        int spinnerPostion = priority_adapter.getPosition(task.getPriority());
		        priority_spinner.setSelection(spinnerPostion);
		 }
		
		
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
		if (!task.getStatus().equals(null)) 
		 {
		        int spinnerPostion = status_adapter.getPosition(task.getStatus());
		        status_spinner.setSelection(spinnerPostion);
		 }

	}
	
	public void onSave(View v)
	{
		//Save Task
		String URL=Utillities.BASE_URL+"task/update?id="+task.getId()+"&title="+editTitle.getText().toString().trim()+"&description="+
				editDescription.getText().toString().trim()+"&status="+status+"&priority="+priority+
				"&email="+Login.userId;
		URL=URL.replaceAll(" ", "%20");
		String title=editTitle.getText().toString().trim();
		String description=editDescription.getText().toString().trim();
		
		if(!title.isEmpty() && title!=null && !description.isEmpty() && description!=null)
		{
			Log.e("url ", URL);
			RestApi restApi = new RestApi(EditTask.this);
			restApi.editTask(URL);
			Utillities.showProgressDialog(this, "Updating Task...");
		}
		else
			Toast.makeText(EditTask.this,"Enter All Fields", Toast.LENGTH_SHORT).show();

	
	}
	
	public void onCancel(View v)
	{
		//Cancel Task
		Intent intent = new Intent();
	    setResult(RESULT_OK, intent);
		EditTask.this.finish();
	}
	
	
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		  switch (parent.getId()) 
		    {         
		        case R.id.priority_spinner:
		        	 priority = parent.getItemAtPosition(position).toString();
			    	 break;              

		        case R.id.status_spinner:
		        	status = parent.getItemAtPosition(position).toString();
			    	break;              
		    }
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
