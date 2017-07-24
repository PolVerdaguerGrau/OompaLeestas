package com.example.pol.testapplication.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pol.testapplication.Factories.ImageRetrieverFactory;
import com.example.pol.testapplication.Factories.ServiceRegistrationFactory;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;
import com.example.pol.testapplication.R;

import java.util.Map;
import java.util.Set;

public class OompasListActivity extends AppCompatActivity {

    OompaRegistrationService oompaRegistrationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oompas_list);

        oompaRegistrationService = ServiceRegistrationFactory.getLocalService();
        ImageRetrieverFactory.getLocalService().loadBulkImages(oompaRegistrationService.getImageUrls());

        Map<Integer, Map<String, String>> oompaFeed = oompaRegistrationService.getBriefInformation();
        Set<Integer> keySet = oompaFeed.keySet();
        TableLayout tl = (TableLayout) findViewById(R.id.main_table);

        TableRow firstRow = setupFirstRow();
        tl.addView(firstRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        for (final int id : keySet) {
            TableRow tr1 = new TableRow(this);
            setThreeColumnRow(oompaFeed, id, tr1);
            tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OompasListActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @NonNull
    private TableRow setupFirstRow() {
        TableRow firstRow = new TableRow(this);
        firstRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView textview = new TextView(this);
        textview.setText("Name");
        textview.setPadding(50, 30, 5, 60);
        firstRow.addView(textview);

        TextView textview2 = new TextView(this);
        textview2.setText("Gender");
        textview2.setPadding(0, 30, 5, 60);
        firstRow.addView(textview2);

        TextView textview3 = new TextView(this);
        textview3.setText("Profession");
        textview3.setPadding(0, 30, 5, 60);
        firstRow.addView(textview3);
        return firstRow;
    }

    private void setThreeColumnRow(Map<Integer, Map<String, String>> oompaFeed, final int id, TableRow tr1) {
        tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView textview = new TextView(this);
        textview.setText(oompaFeed.get(id).get("first_name") + " " + oompaFeed.get(id).get("last_name"));
        textview.setPadding(50, 30, 5, 20);
        tr1.addView(textview);

        TextView textview2 = new TextView(this);
        textview2.setText(oompaFeed.get(id).get("gender"));
        textview2.setPadding(0, 30, 5, 20);
        tr1.addView(textview2);

        TextView textview3 = new TextView(this);
        textview3.setText(oompaFeed.get(id).get("profession"));
        textview3.setPadding(0, 30, 5, 20);
        tr1.addView(textview3);
        tr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), DetailActivity.class);
                i.putExtra("id", String.valueOf(id));
                startActivity(i);
            }
        });
    }

//textview.getTextColors(R.color.)


}

