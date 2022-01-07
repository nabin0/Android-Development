package com.newpubgnewstatewalpaper.pubgnewstatewalpapers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.window.SplashScreenView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class ShowImageInFullDisplay extends AppCompatActivity {
    ImageView image;
    Button btndownload;
    OutputStream outputStream;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_in_full_display);

        Intent intent = getIntent();
        String url = intent.getStringExtra("imageUrl");

        image = findViewById(R.id.imageFullView);
        btndownload = findViewById(R.id.downloadImage);

        Picasso.get().load(url).placeholder(R.drawable.img_placeholder).into(image);


        // Save image on click
        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, WRITE_EXTERNAL_STORAGE_CODE);
                    } else {
                        saveImg();
                    }
                }
            }
        });
    }

    private void saveImg() {
        // Get directory of the internal storage
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "SaveImage");
        if (!dir.exists()) { // Create directory if not exist
            dir.mkdirs();
        }

        // Get bitmap (i.e image from the imageView)
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        // Create a file in a dir with name of timestamp
        File file = new File(dir, System.currentTimeMillis() + ".PNG");

        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // to save image to folder
        Toast.makeText(getApplicationContext(), "Saved To DCIM", Toast.LENGTH_SHORT).show();
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Accepted", Toast.LENGTH_SHORT).show();
                    saveImg();

                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}