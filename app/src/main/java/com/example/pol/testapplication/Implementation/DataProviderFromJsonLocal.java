package com.example.pol.testapplication.Implementation;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pol.testapplication.Interface.DataProviderInterface;
import com.example.pol.testapplication.JSONParser;
import com.example.pol.testapplication.Oompa;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pol on 7/22/17.
 */
public class DataProviderFromJsonLocal implements DataProviderInterface {

    @Override
    public ArrayList<Oompa> retrieveOompas() {
        JSONParser parser = new JSONParser();

        Log.d("inside", "this is inside the data provider");
        JSONArray oompasJSONList = (JSONArray) parser.getJSONFromUrl("https://www.mockaroo.com/aaafc040/download?count=1000&key=aa8685c0");
        ArrayList<Oompa> oompaList= new ArrayList<>();
        for (int i = 0; i < oompasJSONList.length(); i++) {
            JSONObject oompaJSON = null;
            try {
                oompaJSON = oompasJSONList.getJSONObject(i);
                System.out.println(oompaJSON.getString("first_name"));
                oompaList.add(new Oompa(oompaJSON.getString("first_name"), oompaJSON.getString("last_name"),oompaJSON.getInt("id"),oompaJSON.getString("email"), oompaJSON.getString("gender").charAt(0),oompaJSON.getString("profession"),oompaJSON.getString("thumbnail"),oompaJSON.getString("image")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return oompaList;
    }

    public void copied () {
/*
        try {
            JSONObject reader = new JSONObject(oompasUnformated);

            JSONArray oompasJSONList = reader.getJSONArray("");

            for (int i = 0; i < oompasJSONList.length(); i++) {
                JSONObject oompaJSON = oompasJSONList.getJSONObject(i);
                System.out.println(oompaJSON.get("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/}
}
