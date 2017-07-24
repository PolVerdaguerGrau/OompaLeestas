package com.example.pol.testapplication.Factories;

import com.example.pol.testapplication.Implementations.OompaRegistrationImpl;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;

/**
 * Created by pol on 7/22/17.
 */
public class ServiceRegistrationFactory {
    private ServiceRegistrationFactory() {}

    public static OompaRegistrationService getLocalService(){
        return OompaRegistrationImpl.getInstance();
    }
}
