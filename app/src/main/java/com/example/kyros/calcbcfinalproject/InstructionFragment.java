package com.example.kyros.calcbcfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kyros.calcbcfinalproject.databinding.InstructionFragmentBinding;

/**
 * Created by Angela on 2/25/18.
 */


public class InstructionFragment extends Fragment {

    static final int REQUEST_VIDEO_CAPTURE = 1234;

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
}