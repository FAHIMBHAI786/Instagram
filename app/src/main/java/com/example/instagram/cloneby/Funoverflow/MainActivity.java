package com.example.instagram.cloneby.Funoverflow;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.instagram.cloneby.Funoverflow.fragments.HomeFragment;
import com.example.instagram.cloneby.Funoverflow.fragments.SearchFragment;
import com.example.instagram.cloneby.Funoverflow.fragments.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView homeFragment = findViewById(R.id.homeFragment);
        ImageView searchFragment = findViewById(R.id.seachFragment);
        ImageView uploadFragment = findViewById(R.id.uploadFragment);

        loadFragment(new HomeFragment(),0);


        homeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new HomeFragment(),1);


            }
        });
        searchFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new SearchFragment(),1);

            }
        });
        uploadFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new UploadFragment(),1);

            }
        });



    }
    public void loadFragment(Fragment fragment,int flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(flag ==0)
        fragmentTransaction.add(R.id.fragment_container,fragment);
        else
            fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

}