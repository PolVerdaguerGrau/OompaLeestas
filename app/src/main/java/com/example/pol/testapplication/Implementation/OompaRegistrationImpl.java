package com.example.pol.testapplication.Implementation;

import com.example.pol.testapplication.Interface.OompaRegistrationService;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by pol on 7/22/17.
 */
public class OompaRegistrationImpl implements OompaRegistrationService {

    @Inject
    public OompaRegistrationImpl() {}

    private enum BriefFields {
        image, first_name, last_name, gender,
        profession
    }

    private enum DetailedFields {
        image, first_name, last_name, gender,
        profession,email, thumbnail
    }

    Map<Integer, Map<String, String>> oompaPool;

    @Override
    public Map<String, String> getDetailedInformationById(int id) {
        Map<String, String> detailedInformation = new HashMap<>();
        for(DetailedFields field  : DetailedFields.values()) {
            detailedInformation.put(field.toString(), oompaPool.get(id).get(field));
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
