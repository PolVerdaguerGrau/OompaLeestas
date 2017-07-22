package com.example.pol.testapplication.Implementations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.pol.testapplication.Interfaces.ImageRetrieverService;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;
import com.example.pol.testapplication.Oompa;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by pol on 7/22/17.
 */
public class ImageRetrieverImpl implements ImageRetrieverService {
    private static ImageRetrieverImpl instance = null;

    private static String PLACEHOLDER_IMAGE = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png";
    Map<Integer, Bitmap> idImages;

    protected ImageRetrieverImpl() {
        idImages = new HashMap<>();
    }

    public static ImageRetrieverImpl getInstance() {
        if(instance == null) {
            instance = new ImageRetrieverImpl();
        }
        return instance;
    }

    @Override
    public void loadImage(int id, String urlString) {

        Bitmap bmp = getImageBitmap(urlString);
        idImages.put(id, bmp);

    }

    @Nullable
    private Bitmap getImageBitmap(String urlString) {
        Bitmap bmp = null;
        try {
            URL url = new URL(urlString);
             bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    public Bitmap getImage(int id) {
        return idImages.get(id) == null ? getImageBitmap(PLACEHOLDER_IMAGE) : idImages.get(id);
    }
}
