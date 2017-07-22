package com.example.pol.testapplication;

import com.example.pol.testapplication.Implementation.DataProviderFromJsonLocal;
import com.example.pol.testapplication.Interface.DataProviderService;

/**
 * Created by pol on 7/22/17.
 */
public class ServiceDataProviderFactory {
    private ServiceDataProviderFactory() {}

    public static DataProviderService getLocalService(){
        return new DataProviderFromJsonLocal();
    }
}