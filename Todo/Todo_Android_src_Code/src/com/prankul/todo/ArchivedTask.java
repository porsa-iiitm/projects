package com.prankul.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ArchivedTask extends Activity 
{
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archived_task);
		listView = (ListView)findViewById(R.id.archive_list);
		String URL = Utillities.BASE_URL+"task/archive/"+Login.userId;
		System.out.println(URL);
		
		//Call API method
		RestApi restApi = new RestApi(ArchivedTask.this,listView);
		restApi.getArchiveTasks(URL);
		Utillities.showProgressDialog(this, "Loading...");
}
}
