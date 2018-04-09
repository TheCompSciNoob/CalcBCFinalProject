package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kyros.calcbcfinalproject.databinding.InstructionFragmentBinding;

/**
 * Created by Angela on 2/25/18.
 */

public class InstructionFragment extends Fragment {

//    static final int REQUEST_VIDEO_CAPTURE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        InstructionFragmentBinding binding = InstructionFragmentBinding.inflate(inflater, container, false);
        try {
            binding.setEventHandler((EventHandler) getActivity());
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement InstructionFragment.EventHandler");
        }
        return binding.getRoot();
    }

    public interface EventHandler {

        void takeVideo(View view);

        void showDetails(View view);
    }


//    private void dispatchTakeVideoIntent() {
//        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
//            Uri videoUri = intent.getData();
//            mVideoView.setVideoURI(videoUri);
//        }
//    }
}