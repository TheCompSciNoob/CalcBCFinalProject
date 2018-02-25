package com.example.kyros.calcbcfinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Angela on 2/25/18.
 */

public class InstructionFragment extends Fragment {

    //views
    @BindView(R.id.instructions_to_imageview_switcher)
    ViewSwitcher instructionsSwitcher;
    @BindView(R.id.instructions_textview)
    TextView instructions;
    @BindView(R.id.video_preview_imageview)
    AppCompatImageView videoPreviewImageView;
    @BindView(R.id.duration_textview)
    TextView durationTextView;
    @BindView(R.id.objects_traced_textview)
    TextView objectsTracedTextView;
    @BindView(R.id.details_button)
    Button detailsButton;
    private Unbinder unbinder;

    //binding


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.instruction_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.details_button)
    public void showDetails(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.scrollview_container, new DetailFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}