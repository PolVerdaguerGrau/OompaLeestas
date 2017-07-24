package com.example.pol.testapplication.Factories;

import com.example.pol.testapplication.Implementations.ImageRetrieverImpl;
import com.example.pol.testapplication.Implementations.OompaRegistrationImpl;
import com.example.pol.testapplication.Interfaces.ImageRetrieverService;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;

/**
 * Created by pol on 7/22/17.
 */
public class ImageRetrieverFactory {
    private ImageRetrieverFactory() {}

    public static ImageRetrieverService getLocalService(){
        return ImageRetrieverImpl.getInstance();
    }
}
