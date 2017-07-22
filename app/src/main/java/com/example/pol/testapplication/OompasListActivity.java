package com.example.pol.testapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pol.testapplication.Interface.OompaRegistrationService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OompasListActivity extends AppCompatActivity {

    OompaRegistrationService oompaRegistrationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oompas_list);
        //setContentView(R.layout.content_oompas_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        oompaRegistrationService = ServiceRegistrationFactory.getLocalService();

        Map<Integer, Map<String, String>> oompaFeed = oompaRegistrationService.getBriefInformation();
        Set<Integer> keySet = oompaFeed.keySet();
        for (final int id : keySet) {
            TableLayout tl = (TableLayout) findViewById(R.id.main_table);
            TableRow tr1 = new TableRow(this);
            tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView textview = new TextView(this);
            textview.setText("Name: " + oompaFeed.get(id).get("first_name"));
            textview.setPadding(40, 60, 5, 20);
            tr1.addView(textview);

            TextView textview2 = new TextView(this);
            textview2.setText("hola");
            textview2.setPadding(5, 60, 5, 20);
            tr1.addView(textview2);

            TextView textview3 = new TextView(this);
            textview3.setText("adeu.get(0)");
            textview3.setPadding(5, 60, 5, 20);
            tr1.addView(textview3);
            tr1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getBaseContext(), DetailActivity.class);
                    i.putExtra("id", String.valueOf(id));
                    startActivity(i);
                }
            });
            tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

//textview.getTextColors(R.color.)


}

