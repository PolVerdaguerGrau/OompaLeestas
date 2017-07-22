package com.example.pol.testapplication.Implementation;

import android.content.Context;

import com.example.pol.testapplication.Interface.OompaRegistrationService;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pol on 7/22/17.
 */
public class OompaRegistrationImpl implements OompaRegistrationService {


    private static OompaRegistrationImpl instance = null;
    Map<Integer, Map<String, String>> oompaPool;

    protected OompaRegistrationImpl() {
        oompaPool = new HashMap<>();
        populate();
    }

    private void populate() {
        HashMap<String, String> map = new HashMap<>();
        map.put("first_name", "POL");
        map.put("last_name", "VERDAGUER");
        map.put("email", "this@ismyemail.com");
        map.put("profession", "professor");
        oompaPool.put(1,map);

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("first_name", "NURIA");
        map2.put("last_name", "AUMI");
        map2.put("email", "this@gmail.com");
        map2.put("profession", "trapesist");
        oompaPool.put(2,map2);
    }
    public static OompaRegistrationImpl getInstance() {
        if(instance == null) {
            instance = new OompaRegistrationImpl();
        }
        return instance;
    }

    private enum BriefFields {
        image, first_name, last_name, gender,
        profession
    }

    private enum DetailedFields {
        image, first_name, last_name, gender,
        profession,email, thumbnail
    }


    @Override
    public Map<String, String> getDetailedInformationById(int id) {
        Map<String, String> detailedInformation = new HashMap<>();
        for(DetailedFields field  : DetailedFields.values()) {
            detailedInformation.put(field.toString(), oompaPool.get(id).get(field.toString()) == null ? "" : oompaPool.get(id).get(field.toString()));
        }
        return detailedInformation;
    }

    @Override
    public Map<String, String> getBriefInformationById(int id) {
        Map<String, String> briefInformation = new HashMap<>();
        for(BriefFields field  : BriefFields.values()) {
            briefInformation.put(field.toString(), oompaPool.get(id).get(field));
        }
        return briefInformation;    }

    @Override
    public void registerOompa(String name, String lastName, int id, String email, Character gender, String profession, String thumbnail, String imageUrl) {
        Map<String, String> values = new HashMap<>();
        values.put("first_name", name);
        values.put("last_name", lastName);
        values.put("email", email);
        values.put("gender", gender.toString());
        values.put("profession", profession);
        values.put("thumbnail", thumbnail);
        values.put("image", imageUrl);
        oompaPool.put(id, values);
    }
}
