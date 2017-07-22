package com.example.pol.testapplication.Factories;

import com.example.pol.testapplication.Implementations.OompaRegistrationImpl;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;

/**
 * Created by pol on 7/22/17.
 */
public class ImageRetrieverFactory {
    private ImageRetrieverFactory() {}

    private OompaRegistrationService service;

    public static OompaRegistrationService getLocalService(){
        return OompaRegistrationImpl.getInstance();
    }
}
