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

public class MainActivity extends Activity {

    private ArrayList<Oompa> oompas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        new LongRunningGetIO().execute();
    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            String text;
            DataProviderService dataProvider = ServiceDataProviderFactory.getLocalService();
            oompas = dataProvider.retrieveOompasInformation();
            OompaRegistrationService oompaRegistrator = ServiceRegistrationFactory.getLocalService();
            for(Oompa oompa : oompas) {
                oompaRegistrator.registerOompa(oompa);
            }
            text = oompas.isEmpty() ? "No Oompas found" : "Done";
            return text;
        }

        protected void onPostExecute(String results) {
            Intent i = new Intent(getBaseContext(), OompasListActivity.class);
            startActivity(i);
        }
    }
}