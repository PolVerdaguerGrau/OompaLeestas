package com.example.pol.testapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OompasListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oompas_list);
        //setContentView(R.layout.content_oompas_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 10; ++i)
            items.add(String.valueOf(i));

        TableLayout tl=(TableLayout)findViewById(R.id.main_table);
        for(String num : items) {
            TableRow tr1 = new TableRow(this);
            tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView textview = new TextView(this);
            textview.setText("JOAN");
            textview.setPadding(40, 60, 5, 20);
            tr1.addView(textview);

            TextView textview2 = new TextView(this);
            textview2.setText(num);
            textview2.setPadding(5, 60, 5, 20);
            tr1.addView(textview2);

            TextView textview3 = new TextView(this);
            textview3.setText("JOAN@asdfasd.com");
            textview3.setPadding(5, 60, 5, 20);
            tr1.addView(textview3);
            tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

//textview.getTextColors(R.color.)




    }
}
