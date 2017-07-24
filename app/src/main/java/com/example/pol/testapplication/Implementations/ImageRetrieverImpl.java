package com.example.pol.testapplication.Implementations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pol.testapplication.Factories.ImageRetrieverFactory;
import com.example.pol.testapplication.Factories.ServiceDataProviderFactory;
import com.example.pol.testapplication.Factories.ServiceRegistrationFactory;
import com.example.pol.testapplication.Interfaces.DataProviderService;
import com.example.pol.testapplication.Interfaces.ImageRetrieverService;
import com.example.pol.testapplication.Interfaces.OompaRegistrationService;
import com.example.pol.testapplication.Oompa;
import com.example.pol.testapplication.R;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

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
        if (instance == null) {
            instance = new ImageRetrieverImpl();
        }
        return instance;
    }

    private void loadImage(int id, String urlString) {

        Bitmap bmp = getImageBitmap(urlString);
        idImages.put(id, bmp);

    }

    private void loadDefaultImage() {
        if (idImages.get(-1) == null) {
            Bitmap bmp = null;
            try {
                URL url = new URL(PLACEHOLDER_IMAGE);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            idImages.put(-1, bmp);
        }
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
    public void loadBulkImages(Map<Integer, String> imageUrls) {
        new ImageThread(imageUrls).execute();
    }

    private void loadImagesInBackground(Map<Integer, String> imageUrls) {
        loadDefaultImage();
        for(int key : imageUrls.keySet()) {
            loadImage(key, imageUrls.get(key));
        }
    }

    @Override
    public Bitmap getImage(int id) {
        return idImages.get(id) == null ? idImages.get(-1) : idImages.get(id);
    }

    private class ImageThread extends AsyncTask<Void, Void, String> {

        Map<Integer, String> urls;
        public ImageThread (Map<Integer, String> imageUrls) {
            super();
            urls = imageUrls;
        }
        @Override
        protected String doInBackground(Void... params) {
            String text = "Please wait";
            try {
                loadImagesInBackground(urls);
            }catch (OutOfMemoryError e) {
                
            }
            return text;
        }

        protected void onPostExecute(String results) {
            Log.d("images retrieved!", "all images have been loaded");
        }
    }
}
