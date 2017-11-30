package com.android.proyecto.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.proyecto.R;


public class fragmentvalores extends Fragment {
     TextView id, name, mision, vision;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentvalores, container, false);

        // Inflate the layout for this fragment
        return view;

    }

}
