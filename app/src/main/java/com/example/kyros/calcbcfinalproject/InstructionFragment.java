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