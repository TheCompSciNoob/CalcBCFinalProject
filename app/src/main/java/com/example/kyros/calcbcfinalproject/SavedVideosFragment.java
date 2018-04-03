package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kyros on 4/3/2018.
 */

public class SavedVideosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: implement recyclerview and stuff
        return inflater.inflate(R.layout.saved_videos_fragment, container, false);
    }
}
