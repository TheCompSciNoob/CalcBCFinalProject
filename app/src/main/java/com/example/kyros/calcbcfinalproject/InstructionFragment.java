package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Angela on 2/25/18.
 */

public class InstructionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.instruction_fragment, container, false);
        Button button = view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        return view;
    }

    private void openCamera() {
        
    }

    public void showDetails(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DetailFragment())
                .addToBackStack(null)
                .commit();
    }
}