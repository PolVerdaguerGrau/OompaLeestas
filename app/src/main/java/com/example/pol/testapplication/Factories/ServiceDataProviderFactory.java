package com.example.pol.testapplication.Factories;

import com.example.pol.testapplication.Implementations.DataProviderFromJsonLocal;
import com.example.pol.testapplication.Interfaces.DataProviderService;

/**
 * Created by pol on 7/22/17.
 */
public class ServiceDataProviderFactory {
    private ServiceDataProviderFactory() {}

    public static DataProviderService getLocalService(){
        return new DataProviderFromJsonLocal();
    }
}