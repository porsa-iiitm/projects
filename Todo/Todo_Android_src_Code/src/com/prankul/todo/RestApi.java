package com.prankul.todo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.prankul.adapters.CommentListAdapter;
import com.prankul.adapters.TaskListAdapter;
import com.prankul.model.Comment;
import com.prankul.model.Task;

public class RestApi {
	
	protected AsyncHttpClient client;
	protected Context mContext;
	Gson gson;
	Activity mActivity;
	private ListView mListView;
    public RestApi(Context context) 
    {
		client = new AsyncHttpClient();
		client.setTimeout(60000);
		mContext = context;
		gson = new Gson();
	}
    public RestApi(Activity activity) 
    {
		client = new AsyncHttpClient();
		client.setTimeout(60000);
		mActivity = activity;
		gson = new Gson();
	}
    public RestApi(Activity activity , ListView listView) 
    {
		client = new AsyncHttpClient();
		client.setTimeout(60000);
		mActivity = activity;
		gson = new Gson();
		mListView=listView;
	}
	
	public Vector<Task> getAllTasks(String url)
	{
			final Vector<Task> taskVector = new Vector<Task>();
			client.get(url,null, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        	System.out.println("calling success 1");
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	for(int i=0;i<response.length();i++)
	        	{
	        		JSONObject js = null;
					try {
						js = response.getJSONObject(i);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Task task =gson.fromJson(js.toString(), Task.class);
	        		taskVector.add(task);
	        		
	        	}
	        	Object[] a = taskVector.toArray();  
	        	Arrays.sort(a);
	        	for(int i=0;i < a.length; i++) 
	        	{  
	        		taskVector.setElementAt((Task) a[i], i) ;
	        	}  
	        	//Call setAdapter
	        	TaskListAdapter adapter;
	        	adapter = new TaskListAdapter(mActivity, taskVector);
	        	mListView.setAdapter(adapter);
	        	System.out.println("TaskVecotr Size: "+taskVector.size());
	        	Utillities.hideProgressDialog();
	        	
	        }
		});
	return taskVector;
	}
	
	public void addTask(String url)
	{
			client.post(url, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) 
	        {
	        	//Do Something when Response is just an Object
	        	System.out.println("Add Task Response: "+response.toString());
	        	Utillities.hideProgressDialog();
	        	mActivity.finish();
	        	
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	
	        }
		});
	return ;
	}
	
	public void taskDetail(int id)
	{
			//Get Task Details from taskVector for taskId=id and Call Comments API
		String url=Utillities.BASE_URL+"task/comment/"+id;
		getComments(url);
			
	return ;
	}
	
	public void getComments(String url)
	{
			final Vector<Comment> commentVector = new Vector<Comment>();
		
			//Get Comments for taskId=id
			
			client.get(url,null, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array	
	        	for(int i=0;i<response.length();i++)
	        	{
	        		JSONObject js = null;
					try {
						js = response.getJSONObject(i);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Comment cmt =gson.fromJson(js.toString(), Comment.class);
	        		commentVector.add(cmt);
	        		
	        	}
	        	Log.e("getComments;", "b4 adapter");
	        	//Call setAdapter
	        	CommentListAdapter adapter;
	        	adapter = new CommentListAdapter(mActivity, commentVector);
	        	mListView.setAdapter(adapter);
	        	System.out.println("Comment Vector Size: "+commentVector.size());
	        	Utillities.hideProgressDialog();
	        	
	        }
		});
	return ;
	}
	
	
	public void addComment(String url,final int task_id)
	{
			client.post(url, new JsonHttpResponseHandler() 
			{
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        	System.out.println("Add Comment Response: "+response.toString());
	        	String url=Utillities.BASE_URL+"task/comment/"+task_id;
	        	getComments(url);
	        	//Utillities.hideProgressDialog();
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	
	        }
		});
	return ;
	}
	
	
	public void editTask(String url)
	{
			client.put(url,null, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        	System.out.println("Edit Task Response: "+response.toString());
	        	Utillities.hideProgressDialog();
	        	mActivity.finish();
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	
	        }
		});
	return ;
	}
	
	public void deleteTask(String url)
	{
			client.delete(url, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) 
	        {
	        	//String URL = Utillities.BASE_URL+"task/"+Login.userId;
	        	//Do Something when Response is just an Object
	        	System.out.println("Delete Task Response: "+response.toString());
	        	//getAllTasks(URL);
	        	Utillities.hideProgressDialog();
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	
	        }
		});
	return ;
	}
	
	public void archiveTask(String url)
	{
			client.delete(url, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        	System.out.println("Archive Task Response: "+response.toString());
	        	Utillities.hideProgressDialog();
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	
	        }
		});
	return ;
	}
	
	public void getArchiveTasks(String url)
	{
			final Vector<Task> taskVector = new Vector<Task>();
			client.get(url,null, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	for(int i=0;i<response.length();i++)
	        	{
	        		JSONObject js = null;
					try {
						js = response.getJSONObject(i);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Task task =gson.fromJson(js.toString(), Task.class);
	        		taskVector.add(task);
	        		
	        	}
	        	//Call setAdapter
	        	TaskListAdapter adapter;
	        	adapter = new TaskListAdapter(mActivity, taskVector);
	        	mListView.setAdapter(adapter);
	        	System.out.println("ArchiveTaskVecotr Size: "+taskVector.size());
	        	Utillities.hideProgressDialog();
	        	
	        }
		});
	return ;
	}
	
	
	public void googleLogin(String url)
	{
			client.get(url,null, new JsonHttpResponseHandler() {
			
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONObject response) {
	        	//Do Something when Response is just an Object
	        	System.out.println("Google Authentication: "+response.toString());
	        	Utillities.hideProgressDialog();
	        	
	        }
	        
	        @Override
	        public void onSuccess(int statusCode, Header[] headers,org.json.JSONArray response) {
	        	//Do Something when Response is an Array					
	        	
	        }
		});
	return ;
	}
	

}
