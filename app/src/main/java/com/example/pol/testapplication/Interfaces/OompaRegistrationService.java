package com.example.pol.testapplication.Interfaces;

import com.example.pol.testapplication.Oompa;

import java.util.Map;

/**
 * Created by pol on 7/22/17.
 */
public interface OompaRegistrationService {
     Map<String, String> getDetailedInformationById(int id);

     Map<Integer, Map<String, String>> getBriefInformation();

     Map<Integer, String> getImageUrls();
     void registerOompa(Oompa oompa);
    //this should be done with proper constructors
     void registerOompa(String name, String lastName, int id, String email, Character gender, String profession, String thumbnail, String imageUrl);


}
