package com.example.pol.testapplication.Activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.pol.testapplication.Factories.ServiceDataProviderFactory;
import com.example.pol.testapplication.Factories.ServiceRegistrationFactory;
import com.example.pol.testapplication.Interfaces.DataProviderService;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;
import com.example.pol.testapplication.Oompa;
import com.example.pol.testapplication.R;

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
            finish();
        }
    }
}