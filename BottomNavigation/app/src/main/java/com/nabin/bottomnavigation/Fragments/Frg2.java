package com.nabin.bottomnavigation.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nabin.bottomnavigation.MainActivity2;
import com.nabin.bottomnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frg2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frg2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frg2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frg2.
     */
    // TODO: Rename and change types and number of parameters
    public static Frg2 newInstance(String param1, String param2) {
        Frg2 fragment = new Frg2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg2, container, false);

        Button button = view.findViewById(R.id.goToDetail);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity2.class);
            startActivity(intent);
        });

        return view;
    }
}