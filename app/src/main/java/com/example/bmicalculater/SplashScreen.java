package com.example.bmicalculater;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    ImageView bmi;
    TextView bmi_txt,splash_txt;
    Animation tTb_anim,bTt_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        bmi=findViewById(R.id.bmi_img);
        bmi_txt=findViewById(R.id.bmi_calculator);
        splash_txt=findViewById(R.id.splash_txt);
        tTb_anim= AnimationUtils.loadAnimation(this,R.anim.top_to_bottom_animation);
        bTt_anim = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top_animation);

        bmi.setAnimation(tTb_anim);
        bmi_txt.setAnimation(bTt_anim);
        splash_txt.setAnimation(bTt_anim);


        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Intent intent = new Intent(SplashScreen.this, WelcomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(R.anim.left_side_in,R.anim.right_side_out);
            }
        });
        thread.start();

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}