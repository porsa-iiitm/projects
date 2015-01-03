package com.prankul.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.prankul.model.Task;

public class TaskDetails extends ActionBarActivity 
{
	TextView title,description,priority,status,createdAt;
	ListView commentList;
	EditText comment;
	ImageButton addButton;
	Task task;
	Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_details);
		Intent intent = getIntent();
		task= (Task)intent.getParcelableExtra("task");
		activity=this;
		getviews();
		String URL = Utillities.BASE_URL+"task/comment/"+task.getId();
		System.out.println(URL);
		
		//Call API method
		RestApi restApi = new RestApi(TaskDetails.this,commentList);
		restApi.getComments(URL);
		Utillities.showProgressDialog(this, "Loading...");
		
		
		title.setText(task.getTitle());
		description.setText(task.getDescription());
		priority.setText(task.getPriority());
		status.setText(task.getStatus());

		String date=Utillities.getDate(Utillities.DateToTimestamp(task.getCreated_at()));
		createdAt.setText(date);
		
	}

	private void getviews() 
	{
		// TODO Auto-generated method stub
		title=(TextView) findViewById(R.id.titletxt);
		description=(TextView) findViewById(R.id.descriptiontxt);
		priority=(TextView) findViewById(R.id.prioritytxt);
		status=(TextView) findViewById(R.id.statustxt);
		createdAt=(TextView) findViewById(R.id.createdattxt);
		commentList=(ListView) findViewById(R.id.comments_list);
		comment=(EditText) findViewById(R.id.edit_add_more);
		addButton=(ImageButton) findViewById(R.id.plus);
	}
	public void addField(View v)
	{

		if(comment.getText().toString().isEmpty())
			Toast.makeText(getApplicationContext(),"Enter Comment", Toast.LENGTH_SHORT).show();
		else
		{
			RestApi restApi = new RestApi(TaskDetails.this,commentList);
			String URL = Utillities.BASE_URL+"task/comment/add?comment="+
					comment.getText().toString().trim()+"&task_id="+task.getId();
			URL = URL.replaceAll(" ", "%20");
			System.out.println(URL);
			restApi.addComment(URL,task.getId());
			comment.setText("");
			Utillities.showProgressDialog(this, "Adding Comment...");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.task_details_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.edit_task) {
			//Call Activity to edit the task and then update Task API
			  Intent i=new Intent(TaskDetails.this,EditTask.class);
			  i.putExtra("task", task);
	  	      startActivityForResult(i,2);
	  	      return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  
    {  
              super.onActivityResult(requestCode, resultCode, data);  
               // check if the request code is same as what is passed  here it is 2  
                	if(requestCode==2)  
                      {  
                	  if(resultCode==RESULT_OK)
                	  {
                		  
                	  }
                	  else
                		  activity.finish();
                      } 
               
  }  
}
