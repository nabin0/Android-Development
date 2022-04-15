package com.nabin.fragment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment1 frg = new BlankFragment1();
                FragmentManager mgr = getSupportFragmentManager();

                if( getSupportFragmentManager().getFragments().size() > 0){
                    for(int i = 0; i < mgr.getFragments().size(); i++){
                        Fragment fragment = mgr.getFragments().get(i);
                        if(fragment != null){
                            mgr.beginTransaction().remove(fragment).commit();
                        }
                    }
                }
                FragmentTransaction trn = mgr.beginTransaction();
                trn.add(R.id.wrapper, frg);
                trn.addToBackStack(null);
                trn.commit();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mgr = getSupportFragmentManager();

                if( getSupportFragmentManager().getFragments().size() > 0){
                    for(int i = 0; i < mgr.getFragments().size(); i++){
                        Fragment fragment = mgr.getFragments().get(i);
                        if(fragment != null){
                            mgr.beginTransaction().remove(fragment).commit();
                        }
                    }
                }

                BlankFragment2 fragment2 = new BlankFragment2();
                mgr.beginTransaction()
                        .add(R.id.wrapper, fragment2)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}