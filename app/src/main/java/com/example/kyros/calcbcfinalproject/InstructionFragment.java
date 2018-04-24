package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kyros.calcbcfinalproject.databinding.InstructionFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * Created by Angela on 2/25/18.
 */


public class InstructionFragment extends Fragment implements VideosRecyclerViewAdapter.OnRecyclerViewClickListener {

    public static final String VIDEO_FRAGMENT_STACK = "video fragment stack";
    private static final int REQUEST_VIDEO_CAPTURE = 1234;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recyclerview
        /*RecyclerView recyclerView = view.findViewById(R.id.videos_recycler_view);
        VideosRecyclerViewAdapter adapter = new VideosRecyclerViewAdapter(items, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);*/
    }

    @Override
    public void onItemClick(@NotNull View view, int position) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoFragment())
                .addToBackStack(VIDEO_FRAGMENT_STACK)
                .commit();
    }

    @Override
    public void onDeleteClick(@NotNull View view, int position) {
        //TODO: later
    }

    public interface EventHandler {
        void takeVideo(View view);
    }
}