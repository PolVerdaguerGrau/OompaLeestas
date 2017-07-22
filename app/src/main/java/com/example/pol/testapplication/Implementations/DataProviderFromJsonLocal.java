package com.example.pol.testapplication.Implementations;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pol.testapplication.Interfaces.DataProviderService;
import com.example.pol.testapplication.JSONParser;
import com.example.pol.testapplication.Oompa;

import java.util.ArrayList;

/**
 * Created by pol on 7/22/17.
 */
public class DataProviderFromJsonLocal implements DataProviderService {

    //TODO this class shouldn't know about the existance of a class called OOmpa and should only work with strings
    @Override
    public ArrayList<Oompa> retrieveOompasInformation() {
        JSONParser parser = new JSONParser();

        Log.d("inside", "this is inside the data provider");
        JSONArray oompasJSONList = (JSONArray) parser.getJSONFromUrl("https://www.mockaroo.com/aaafc040/download?count=1000&key=aa8685c0");
        ArrayList<Oompa> oompaList= new ArrayList<>();
        for (int i = 0; i < oompasJSONList.length(); i++) {
            JSONObject oompaJSON = null;
            try {
                oompaJSON = oompasJSONList.getJSONObject(i);
                //System.out.println(oompaJSON.getString("first_name"));
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
