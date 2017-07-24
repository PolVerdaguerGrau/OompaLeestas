package com.example.pol.testapplication;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.pol.testapplication.Factories.ImageRetrieverFactory;
import com.example.pol.testapplication.Factories.ServiceDataProviderFactory;
import com.example.pol.testapplication.Factories.ServiceRegistrationFactory;
import com.example.pol.testapplication.Interfaces.DataProviderService;
import com.example.pol.testapplication.Interfaces.ImageRetrieverService;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;

public class MainActivity extends Activity implements OnClickListener {

    private ArrayList<Oompa> oompas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        findViewById(R.id.my_button).setOnClickListener(this);
        new LongRunningGetIO().execute();
        //TODO treure aquesta xapussa dáqui
        try {
            Log.d("time", "AAAA");
            Thread.sleep(10000);
            Log.d("time", "BBBBB");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this, OompasListActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.my_button);
        b.setClickable(false);

    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpContext localContext = new BasicHttpContext();
                HttpGet httpGet = new HttpGet("http://www.cheesejedi.com/rest_services/get_big_cheese.php?puzzle=1");
            String text = "Please wait";
            DataProviderService dataProvider = ServiceDataProviderFactory.getLocalService();
            Log.d("main activity", "This is before entering the data provider");
            oompas = dataProvider.retrieveOompasInformation();
            OompaRegistrationService oompaRegistrator = ServiceRegistrationFactory.getLocalService();
            for(Oompa oompa : oompas) {
                oompaRegistrator.registerOompa(oompa);
            }
            text = oompas.isEmpty() ? "No Oompas found" : "Done";
            return text;
        }

        protected void onPostExecute(String results) {
            if (results != null) {
                EditText et = (EditText) findViewById(R.id.my_edit);
                et.setText(results);
            }
            Button b = (Button) findViewById(R.id.my_button);
            b.setClickable(true);
        }
    }
}