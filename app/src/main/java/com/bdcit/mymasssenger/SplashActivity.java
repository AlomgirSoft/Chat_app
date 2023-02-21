package com.bdcit.mymasssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {


    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {

                  textpage();

              }


          },2000);

        }

    private void textpage() {

        if (firebaseUser!=null){

            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(SplashActivity.this,LoingActivity2.class));
            finish();
        }
    }
    }

