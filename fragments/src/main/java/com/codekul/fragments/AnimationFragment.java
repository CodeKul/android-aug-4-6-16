package com.codekul.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimationFragment extends Fragment {

    public static final String KEY_MINION = "minon";

    public static AnimationFragment getInstance(int minion){

        AnimationFragment fragment = new AnimationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MINION,minion);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);

        changeMinion(rootView, getArguments().getInt(KEY_MINION));

        return rootView;
    }

    private void changeMinion(View rootView, int minion){

        ((ImageView)rootView.findViewById(R.id.imageMinion)).setImageResource(minion);
    }
}
