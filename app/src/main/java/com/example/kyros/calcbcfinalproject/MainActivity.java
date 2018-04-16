package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InstructionFragment.EventHandler{

    private static final String LOG_TAG = "log tag";
    private boolean cameraHardware;
//    static final int REQUEST_VIDEO_CAPTURE = 1234;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        cameraHardware = checkCameraHardware(this);

        //Not auto generated after this point

        //start new fragment if activity is relaunched
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new GraphFragment())
                    .commit();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        if (id == R.id.instruction_fragment_item) {
            fragment = new InstructionFragment();
        } else if (id == R.id.saved_videos_fragment_item) {
            //TODO: saved videos fragment
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void takeVideo(View view) {
        //TODO: open camera and take video
//        dispatchTakeVideoIntent();
        // put into instruction fragment
    }

    @Override
    public void showDetails(View view) {
        //TODO: open details fragment to display calculations
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //TODO: save video to storage
//
//        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
//            Uri videoUri = data.getData();
//
//            if(isExternalStorageWritable()) {
//
//                boolean success = false;
//
//                //Save to storage
//                File vidDir = getPublicAlbumStorageDir("Calc BC");
//
//                Random generator = new Random();
//                int n = 100;
//                n = generator.nextInt(n);
//
//                String videoName = "Video_" + n + ".mp4";
//                File fileVideo = new File(vidDir.getAbsolutePath(), videoName);
//
//                try {
//                    fileVideo.createNewFile();
//                    success = true;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (success) {
//                    Toast.makeText(getApplicationContext(), "Video saved!",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "Error during video saving", Toast.LENGTH_LONG).show();
//                }
//
//            }
//            else {
//                Toast.makeText(this, "Storage Unavailable", Toast.LENGTH_SHORT).show();
//            }
////            mVideoView.setVideoURI(videoUri);
//        }
    }

    //idk
//    private void dispatchTakeVideoIntent() {
//        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//        }
//    }

    //idk
//    /* Checks if external storage is available for read and write */
//    public boolean isExternalStorageWritable() {
//        String state = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(state)) {
//            return true;
//        }
//        return false;
//    }

    //idk
//    public File getPublicAlbumStorageDir(String albumName) {
//        // Get the directory for the user's public pictures directory.
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), albumName);
//        if (!file.mkdirs()) {
//            Log.e(LOG_TAG, "Directory not created");
//        }
//        return file;
//    }

    //idk
//    private void saveFile(Uri sourceUri, File destination)
//    try {
//        File source = new File(sourceUri.getPath());
//        FileChannel src = new FileInputStream(source).getChannel();
//        FileChannel dst = new FileOutputStream(destination).getChannel();
//        dst.transferFrom(src, 0, src.size());
//        src.close();
//        dst.close();
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//}

//idk
//    /** Check if this device has a camera */
//    private boolean checkCameraHardware(Context context) {
//        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
//            // this device has a camera
//            return true;
//        } else {
//            // no camera on this device
//            return false;
//        }
//    }

