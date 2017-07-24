package com.example.pol.testapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pol.testapplication.Factories.ImageRetrieverFactory;
import com.example.pol.testapplication.Factories.ServiceRegistrationFactory;
import com.example.pol.testapplication.Interfaces.ImageRetrieverService;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

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

        

        int id = Integer.valueOf(getIntent().getStringExtra("id"));
        Map<String, String> information = oompaRegistrationService.getDetailedInformationById(id);

        LinearLayout layout = (LinearLayout) findViewById(R.id.detail_layout);


        TextView textview2 = new TextView(this);
        textview2.setText(information.get("first_name"));
        textview2.setPadding(0, 200, 5, 60);
        layout.addView(textview2);


        ImageRetrieverService imageService = ImageRetrieverFactory.getLocalService();
        Bitmap image = imageService.getImage(id);

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(image, 120, 120, false));
        layout.addView(imageView);

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
