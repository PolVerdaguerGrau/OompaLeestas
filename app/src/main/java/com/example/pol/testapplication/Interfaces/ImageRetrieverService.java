package com.example.pol.testapplication.Interfaces;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by pol on 7/22/17.
 */
public interface ImageRetrieverService {
    Bitmap getImage(int id);
    void loadBulkImages(Map<Integer, String> imageUrls);
}
