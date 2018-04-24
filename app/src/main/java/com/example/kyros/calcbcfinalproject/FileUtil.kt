package com.example.kyros.calcbcfinalproject

import android.os.Environment
import android.util.Log
import java.io.File

//can write?
fun isExternalStorageWritable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

//can read?
fun isExternalStorageReadable(): Boolean = Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)

//get directory for videos
fun getPublicVideoStorageDir(name: String) : File? {
    val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), name)
    if (!file.mkdirs()) {
        Log.e("getPublicVideoStorage: ", "Directory not created")
    }
    return file
}