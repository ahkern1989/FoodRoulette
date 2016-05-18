package com.jk.foodroulette.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Created by Jacob on 5/18/2016.
 */
public class Helper {

    static Context myContext;
    static Activity myActivity;

    public Helper(Activity activity, Context context) {

        myContext = context;
        myActivity = activity;
    }

    public Helper() {
        // TODO Auto-generated constructor stub
    }

    public static void saveStringData(String key, String value) {
        SharedPreferences.Editor spe = PreferenceManager.getDefaultSharedPreferences(myContext.getApplicationContext()).edit();
        spe.putString(key, value);
        spe.commit();
    }

    public static String loadStringData(String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(myContext.getApplicationContext());
        return sp.getString(key, null);
    }

    public static boolean saveBitmapFile(Bitmap image) {
        try {
            // Use the compress method on the Bitmap object to write image to
            // the OutputStream
            FileOutputStream fos = myContext.openFileOutput("1.png", Context.MODE_PRIVATE);

            // Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }

    public static Bitmap loadBitmap(String fileName) {
        String imageName = fileName + ".png";
        Bitmap b = null;
        try {
            FileInputStream fis = myContext.openFileInput(imageName);
            b = BitmapFactory.decodeStream(fis);
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return b;

    }
}
