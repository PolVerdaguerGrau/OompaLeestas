package com.example.pol.testapplication.Interfaces;

import android.graphics.Bitmap;

/**
 * Created by pol on 7/22/17.
 */
public interface ImageRetrieverService {
    void loadImage(int id, String url);
    Bitmap getImage(int id);
}
