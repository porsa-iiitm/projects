package com.prankul.toi;

import android.net.ParseException;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    public String TAG = MainActivity.class.getSimpleName();
    protected AsyncHttpClient client;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    File dir;
    File file;
    String NEWS_FILE="/news.txt";
    String FOLDER_NAME="/TOI";

    public static String url="https://api.myjson.com/bins/46jjg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();
        client.setTimeout(60000);

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        //Check existence of directory, if not present then make directory
        dir = new File(Environment.getExternalStorageDirectory() + FOLDER_NAME);
        if(!dir.exists() || !dir.isDirectory()) {
            // do something here
            dir.mkdir();
        }

        //Check for base file, if not present then make base file else create sub-sequential file
        file = new File(dir+NEWS_FILE);
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Utils.showProgressDialog(this,"Loading..");
        if(Utils.isNetworkAvailable(this)){
            getNewsFeedOnline();
        }else{
            getNewsFeedOffline();
        }

    }

    public void getNewsFeedOnline(){
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject response) {
                //Do Something when Response is just an Object
                try {
                    ArrayList newsItemList = prepareNewsItemList(response);
                    writeToFile(response);
                    newsAdapter = new NewsAdapter(MainActivity.this, newsItemList, false);
                    recyclerView.setAdapter(newsAdapter);
                    Utils.hideProgressDialog();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ArrayList prepareNewsItemList(JSONObject response) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("NewsItem");
        ArrayList newsItemList = new ArrayList<NewsItem>();

        for (int i = 0; i < jsonArray.length(); i++) {
//                        android.util.Log.e(TAG,"Response: "+jsonArray.getJSONObject(i).toString());
            NewsItem newsItem = new NewsItem();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            newsItem.setNewsItemId(jsonObject.optString("NewsItemId"));
            newsItem.setHeadLine(jsonObject.optString("HeadLine"));
            newsItem.setByLine(jsonObject.optString("ByLine"));
            newsItem.setDateLine(jsonObject.optString("DateLine"));
            newsItem.setAgency(jsonObject.optString("Agency"));

            Image image = new Image();
            JSONObject imageObject = jsonObject.optJSONObject("Image");

            if (imageObject != null)
                image.setThumb(imageObject.optString("Thumb"));

            newsItem.setImage(image);

            newsItemList.add(newsItem);
        }
        return newsItemList;
    }


    public void getNewsFeedOffline(){
        //Do Something when Response is just an Object
        try {
            JSONObject response=readFromFile();
            if(response!=null){
                ArrayList newsItemList = prepareNewsItemList(response);
                newsAdapter = new NewsAdapter(MainActivity.this, newsItemList, true);
                recyclerView.setAdapter(newsAdapter);
                Utils.hideProgressDialog();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject readFromFile() {
        // TODO Auto-generated method stub

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject response =null;
        JSONObject jsonObject =null;
        try {
            Object obj = null;
            try {
                obj = parser.parse(new FileReader(file));
            } catch (org.json.simple.parser.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response = (org.json.simple.JSONObject) obj;
            try {
                if(response!=null)
                jsonObject=new JSONObject(response.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void writeToFile(JSONObject list) {
        try {
            FileWriter filew = new FileWriter(file.toString());
            filew.write(list.toString());
            filew.flush();
            filew.close();
        }
        catch (Exception e) {
            System.out.println("e: " + e);
        }
    }
}
