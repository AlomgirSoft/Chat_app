package com.bdcit.mymasssenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;

import com.bdcit.mymasssenger.databinding.ActivityMainBinding;
import com.bdcit.mymasssenger.fragmaent.HomeFragment;
import com.bdcit.mymasssenger.fragmaent.ProfileFragment;
import com.bdcit.mymasssenger.fragmaent.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



ActivityMainBinding binding;

FirebaseAuth firebaseAuth;
FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        firebaseAuth = FirebaseAuth.getInstance();
        fragmentManager = getSupportFragmentManager();



        binding.bottomBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){



                    case R.id.home:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment(),null).commit();
                        break;


                    case R.id.User:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment(),null).commit();
                        break;


                    case R.id.Profile:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ProfileFragment(),null).commit();
                        break;
                }




            }
        });








    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.tom_menu_bar,menu);



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.logout:
                logout();
                break;

        }






        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        firebaseAuth.signOut();

        startActivity(new Intent(getApplicationContext(),LoingActivity2.class));
        finish();

    }
}