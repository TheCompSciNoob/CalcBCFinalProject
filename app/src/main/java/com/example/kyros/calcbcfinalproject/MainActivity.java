package com.example.kyros.calcbcfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity
        implements InstructionFragment.EventHandler {

    private static final int REQUEST_VIDEO_CAPTURE = 1234;
    private static final String TAG = "MainActivity", DIRECTORY_NAME = "Physics App";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start new fragment if activity is relaunched
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new InstructionFragment())
                    .commit();
        }
    }

    @Override
    public void takeVideo(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    public void showDetails(View view) {
        //TODO: open details fragment to display calculations
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: OUTISIDE IF");
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK && FileUtilKt.isExternalStorageWritable()) {
            Log.d(TAG, "onActivityResult: I'M HERE");
            /*videoUri = data.getData();
           
            String filename = getFileName(videoUri);
            Log.d(LOG_TAG, "video taken, filename: " + filename);*/
            //TODO: save to directory using getPublicVideoStorageDir()
            try {
                AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                FileInputStream fis = videoAsset.createInputStream();
                File root = new File(Environment.getExternalStorageDirectory(), "Physics App");
                if (!root.exists()) {
                    root.mkdirs();
                }
                File file = new File(root, "physics_app_" + System.currentTimeMillis() + ".mp4");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                while ((len = fis.read(buf)) > 0) {
                    fos.write(buf, 0, len);
                }
                Log.d(TAG, "onActivityResult: " + root.toString());
                fis.close();
                fos.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /*public Uri getVideoUri() {
        return videoUri;
    }*/

    /*private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }*/
}

