package com.nabin.fragmentsdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DemoFragment extends Fragment {
    TextView textView, addView, ageView;


    public DemoFragment() {
        // Required empty public constructor
    }

    public static DemoFragment getInstance(Employee employee){
        DemoFragment demoFragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("model",employee);
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);

        textView = view.findViewById(R.id.setTextHere);
        addView = view.findViewById(R.id.setAddress);
        ageView = view.findViewById(R.id.setAge);

        // Setting Task
        Bundle bundle = getArguments();
        if (bundle != null){
            Employee employee = bundle.getParcelable("model");
            textView.setText(employee.getmName());
            addView.setText(employee.getmAddress());
            ageView.setText(employee.getAge().toString());
        }

        return  view;
    }
}