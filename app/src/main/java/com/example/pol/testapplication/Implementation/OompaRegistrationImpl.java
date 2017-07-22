package com.example.pol.testapplication.Implementation;

import android.content.Context;

import com.example.pol.testapplication.Interface.OompaRegistrationService;
import com.example.pol.testapplication.Oompa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        //populate();
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
    public Map<Integer, Map<String, String>> getBriefInformation() {
        Map<Integer, Map<String, String>> briefInformation = getDeepCopyInt(oompaPool);


        DetailedFields[] detailedFieldset = DetailedFields.values();
        // initialize with the exact length
        Set<String> stringDetailed = new HashSet<>();
        for (DetailedFields detailedField : detailedFieldset) {
            stringDetailed.add(detailedField.toString());
        }
        BriefFields[] briefFieldset = BriefFields.values();
        Set<String> stringBrief = new HashSet<>();
        for (BriefFields briefFields : briefFieldset) {
            stringBrief.add(briefFields.toString());
        }
        Set<String> excludedKeys = stringDetailed;
        Set<String> s2 = stringBrief;

        excludedKeys.removeAll(s2);

        for(int key : briefInformation.keySet()) {
            briefInformation.get(key).keySet().removeAll(excludedKeys);
        }

       /* for(BriefFields field  : BriefFields.values()) {
            briefInformation.put(field.toString(), oompaPool.get(id).get(field.toString()));
        }*/
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

    @Override
    public void registerOompa(Oompa oompa) {
        Map<String, String> values = new HashMap<>();
        values.put("first_name", oompa.getName());
        values.put("last_name", oompa.getLastName());
        values.put("email", oompa.getEmail());
        values.put("gender", oompa.getGender().toString());
        values.put("profession", oompa.getProfession());
        values.put("thumbnail", oompa.getThumbnail());
        values.put("image", oompa.getImageUrl());
        oompaPool.put(oompa.getId(), values);
    }


    private Map<Integer, Map<String,String>> getDeepCopyInt(Map<Integer, Map<String,String>> source) {
        Map<Integer, Map<String,String>> copy = new HashMap<>();
        for (Map.Entry<Integer, Map<String, String>> entry : source.entrySet())
            copy.put(entry.getKey(), getDeepCopy(entry.getValue()));
        return copy;
    }
    private Map<String, String> getDeepCopy(Map<String, String> source) {
        Map<String, String> copy = new HashMap<>();
        for (Map.Entry<String, String> entry : source.entrySet())
            copy.put(entry.getKey(), entry.getValue());
        return copy;
    }
}
