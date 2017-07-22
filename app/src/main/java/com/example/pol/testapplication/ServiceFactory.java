package com.example.pol.testapplication;

import com.example.pol.testapplication.Implementation.OompaRegistrationImpl;
import com.example.pol.testapplication.Interface.OompaRegistrationService;

/**
 * Created by pol on 7/22/17.
 */
public class ServiceFactory {
    private ServiceFactory() {}

    private OompaRegistrationService service;

    public static OompaRegistrationService getLocalService(){
        return OompaRegistrationImpl.getInstance();
    }
}
