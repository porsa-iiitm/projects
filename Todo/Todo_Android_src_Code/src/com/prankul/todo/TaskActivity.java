package com.prankul.todo;

import java.util.Vector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.prankul.model.Task;

public class TaskActivity extends ActionBarActivity
{

	Vector<Task> taskVector = new Vector<Task>();
	ListView listView;
	String userId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_activity);
		listView = (ListView)findViewById(R.id.task_list);
		
		listView.setTextFilterEnabled(true);
		registerForContextMenu(listView);
		Intent intent = getIntent();
		userId = intent.getStringExtra("userId");
		
		String URL = Utillities.BASE_URL+"task/"+userId;
		System.out.println(URL);
		
		//Call API method
		RestApi restApi = new RestApi(TaskActivity.this,listView);
		taskVector=restApi.getAllTasks(URL);
		System.out.println("Tasks.size="+taskVector.size());
		
		Utillities.showProgressDialog(this, "Loading...");
		
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{

		      @Override
		      public void onItemClick(AdapterView<?> parent, final View view,int position, long id) 
		      {
		    	  Intent i=new Intent(TaskActivity.this,TaskDetails.class);
		    	  i.putExtra("task", taskVector.get(position));
		    	  startActivityForResult(i,2);
		      }

		    });


	}
	
		
	//Action Bar Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_search) 
		{
			
			return true;
		}else if(id == R.id.add_task)
		{
			Intent i=new Intent(TaskActivity.this,AddTask.class);
			startActivityForResult(i, 2);
		return true;
		}else if(id == R.id.archive_task)
		{   
			
			Intent i=new Intent(TaskActivity.this,ArchivedTask.class);
    	    startActivity(i);
			return true;
		}else if(id == R.id.export_task)
		{
			
			
			return true;
		}else if(id == R.id.share_task)
		{
			   String value=Utillities.TasksToString(taskVector);
			   Intent i=new Intent(Intent.ACTION_SEND);
			   i.setType("text/plain");
			   i.putExtra(Intent.EXTRA_SUBJECT, "ToDo Tasks");
			   i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(value));
			   startActivity(Intent.createChooser(i, "Share via"));
			
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
                	String URL = Utillities.BASE_URL+"task/"+userId;
            		System.out.println(URL);
            		
            		//Call API method
            		RestApi restApi = new RestApi(TaskActivity.this,listView);
            		taskVector=restApi.getAllTasks(URL);
            		System.out.println("Tasks.size="+taskVector.size());
            		
            		Utillities.showProgressDialog(this, "Updating...");
                      } 
               
  }  
	//Context Menu
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v,
	   ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  menu.setHeaderTitle("Select Action");
	  menu.add(0, v.getId(), 0, "Edit");
	  menu.add(0, v.getId(), 0, "Archive");
	  menu.add(0, v.getId(), 0, "Delete");
	  menu.add(0, v.getId(), 0, "Share");
	 }
	 
	 @Override
	 public boolean onContextItemSelected(MenuItem item) 
	 {
		 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		 int index = info.position;
	  if (item.getTitle() == "Edit") 
	  {
		  Intent i=new Intent(TaskActivity.this,EditTask.class);
		  i.putExtra("task", taskVector.get(index));
  	      startActivityForResult(i,2);
	   
	  }else if (item.getTitle() == "Archive") 
	  {
		  	
		  	String URL = Utillities.BASE_URL+"task/archive/"+taskVector.get(index).getId();
		  	String URL1 = Utillities.BASE_URL+"task/"+Login.userId;
			System.out.println(URL);
			
			//Call API method
			RestApi restApi = new RestApi(TaskActivity.this,listView);
			restApi.archiveTask(URL);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			taskVector=restApi.getAllTasks(URL1);
			Utillities.showProgressDialog(this, "Archiving Task...");
		   
	  }	else if (item.getTitle() == "Delete") 
	  {
		  	String URL = Utillities.BASE_URL+"task/"+taskVector.get(index).getId();
		  	String URL1 = Utillities.BASE_URL+"task/"+Login.userId;
			System.out.println(URL);
			
			//Call API method
			RestApi restApi = new RestApi(TaskActivity.this,listView);
			restApi.deleteTask(URL);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			taskVector=restApi.getAllTasks(URL1);
			Utillities.showProgressDialog(this, "Deleting Task...");
	  }else if (item.getTitle() == "Share")
	  {
		   Task task =taskVector.get(index);
		   String value="<b><i>Title: </i></b>" + task.getTitle() + "\r\n\n" +
				   	  "<b><i>Description: </i></b>" + task.getDescription() + "\r\n\n" + 
   		              "<b><i>Priority: </i></b>" + task.getPriority() + "\r\n\n" + 
   		              "<b><i>Status: </i></b> " + task.getStatus() + "\r\n\n" + 
   		              "<b><i>Created At: </i></b> " + task.getCreated_at() + "\r\n\n" ; 
		   Intent i=new Intent(Intent.ACTION_SEND);
		   i.setType("text/plain");
		   i.putExtra(Intent.EXTRA_SUBJECT, "ToDo Task Detail");
		   i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(value));
		   startActivity(Intent.createChooser(i, "Share via"));
	  }else {
	   return false;
	  }
	  return true;
	 }
	 
	
	

}
