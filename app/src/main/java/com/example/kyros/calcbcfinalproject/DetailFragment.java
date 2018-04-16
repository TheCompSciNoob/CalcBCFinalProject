package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.VideoView;

/**
 * Created by Angela on 2/25/18.
 */

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        VideoView videoView = getView().findViewById(R.id.videoView);

        SeekBar seekBar = getView().findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //switch frames of video
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //itchanges
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        super.onViewCreated(view, savedInstanceState);

        videoView.setVideoURI(InstructionFragment.getURI());
    }



}
