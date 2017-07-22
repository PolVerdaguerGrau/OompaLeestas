package com.example.pol.testapplication.Interface;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by pol on 7/22/17.
 */
public interface OompaRegistrationService {
    public Map<String, String> getDetailedInformationById(int id);

    public Map<String, String> getBriefInformationById(int id);

    //this should be done with proper constructors
    public void registerOompa(String name, String lastName, int id, String email, Character gender, String profession, String thumbnail, String imageUrl);
}
