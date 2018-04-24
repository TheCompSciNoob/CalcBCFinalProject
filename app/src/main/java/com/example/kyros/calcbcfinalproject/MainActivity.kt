package com.example.kyros.calcbcfinalproject

import android.app.Activity
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

class MainActivity : AppCompatActivity(), InstructionFragment.EventHandler {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //start new fragment if activity is relaunched
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, InstructionFragment())
                    .commit()
        }
    }

    override fun takeVideo(view: View) {
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (takeVideoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK && isExternalStorageWritable()) {
            /*videoUri = data.getData();

            String filename = getFileName(videoUri);
            Log.d(LOG_TAG, "video taken, filename: " + filename);*/
            //TODO: save to directory using getPublicVideoStorageDir()
            try {
                val videoAsset = contentResolver.openAssetFileDescriptor(data.data!!, "r")
                val fis = videoAsset!!.createInputStream()
                val root = File(Environment.getExternalStorageDirectory(), DIRECTORY_NAME)
                if (!root.exists()) {
                    root.mkdirs()
                }
                val file = File(root, "physics_app_" + System.currentTimeMillis() + ".mp4")
                val fos = FileOutputStream(file)
                val buf = ByteArray(1024)
                var len: Int
                do {
                    len = fis.read(buf)
                    fos.write(buf, 0, len)
                } while (len > 0)
                Log.d(TAG, "onActivityResult: $root")
                fis.close()
                fos.close()
            } catch (e: Exception) {
                Log.e(TAG, e.message)
            }

        }
    }

    companion object {

        private val REQUEST_VIDEO_CAPTURE = 1234
        private val TAG = "MainActivity"
        private val DIRECTORY_NAME = "Physics App"
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

