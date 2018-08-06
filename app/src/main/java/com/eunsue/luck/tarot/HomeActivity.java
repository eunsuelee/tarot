package com.eunsue.luck.tarot;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.home_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable)constraintLayout.getBackground();
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(3400);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            HomeActivity.this.finish();
        }
    }
}
