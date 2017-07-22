package com.example.pol.testapplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.pol.testapplication.Implementation.DataProviderFromJsonLocal;
import com.example.pol.testapplication.Interface.DataProviderInterface;

public class MainActivity extends Activity implements OnClickListener {

    private ArrayList<Oompa> oompas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        findViewById(R.id.my_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.my_button);
        b.setClickable(false);
        new LongRunningGetIO().execute();

    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://www.cheesejedi.com/rest_services/get_big_cheese.php?puzzle=1");
            String text = "Please wait";
            DataProviderFromJsonLocal dataProvider = new DataProviderFromJsonLocal();
            Log.d("main activity", "This is before entering the data provider");
            oompas = dataProvider.retrieveOompas();
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