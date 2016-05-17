package com.jk.foodroulette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int CAM_REQUEST = 1313;
    Button btn_camera;
    ImageView iv_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        btn_camera = (Button) findViewById(R.id.btn_camera);
        iv_camera = (ImageView) findViewById(R.id.iv_camera);


        btn_camera.setOnClickListener(new btn_cameraClicker());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAM_REQUEST) {
            if (data != null) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                //iv_camera.setImageBitmap(thumbnail);
                saveBitmapFile(thumbnail);
                loadImageFromStorage();
            }
        }
    }

    public boolean saveBitmapFile(Bitmap image) {
        try {
            // Use the compress method on the Bitmap object to write image to
            // the OutputStream
            FileOutputStream fos = this.openFileOutput("1.png", Context.MODE_PRIVATE);

            // Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }

    private void loadImageFromStorage() {

        try {
            FileInputStream fis = openFileInput("1.png");
            Bitmap b = BitmapFactory.decodeStream(fis);
            iv_camera.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    class btn_cameraClicker implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAM_REQUEST);
        }
    }

}
