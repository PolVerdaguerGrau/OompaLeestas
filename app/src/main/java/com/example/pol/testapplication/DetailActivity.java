package com.example.pol.testapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pol.testapplication.Interface.OompaRegistrationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {

     OompaRegistrationService oompaRegistrationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TextView textView = (TextView) findViewById(R.id.oompaID);
        //textView.setText(getIntent().getStringExtra("id"));

        //// TODO: 7/22/17 Now this uses a singleton but it should be using injection

        oompaRegistrationService = ServiceRegistrationFactory.getLocalService();

        List<String> items = new ArrayList<>();

        Set<String> keySet = oompaRegistrationService.getDetailedInformationById(Integer.valueOf(getIntent().getStringExtra("id"))).keySet();
        for (String id : keySet) {
            items.add(oompaRegistrationService.getDetailedInformationById(Integer.valueOf(getIntent().getStringExtra("id"))).get(id));
        }
        //TODO: Populate the textfields with the information retrieved from the oompaRegistration but it must be a service already instantiated.
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(itemsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
