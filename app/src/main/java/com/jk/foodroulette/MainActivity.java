package com.jk.foodroulette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
                iv_camera.setImageBitmap(thumbnail);
            }
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
