package com.example.bmicalculater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

public class SelectGender extends AppCompatActivity {

    ImageView man,woman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender);
        Objects.requireNonNull(getSupportActionBar()).hide();
        man=findViewById(R.id.man);
        woman=findViewById(R.id.woman);

        man.setOnClickListener(v->{
            Intent i=new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.left_side_in,R.anim.right_side_out);
        });

        woman.setOnClickListener(v->{
            Intent i=new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.left_side_in,R.anim.right_side_out);
        });

    }
}