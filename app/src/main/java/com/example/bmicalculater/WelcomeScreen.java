package com.example.bmicalculater;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class WelcomeScreen extends AppCompatActivity {

    EditText enterName;
    Button nextBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        enterName = findViewById(R.id.enter_user_name);
        nextBtn = findViewById(R.id.nextBtn);

        nextBtn.setOnClickListener(v -> {
            //Explicit Intent
            Intent intent = new Intent(getApplicationContext(), SelectGender.class);
            Intent intent1=new Intent(getApplicationContext(),HomeActivity.class);
            String user_Name = enterName.getText().toString();
            //Passing UserName to next Activity
            intent1.putExtra("enterName", user_Name);

            if (enterName.getText().toString().trim().equalsIgnoreCase("")) {
                enterName.setError("This field can not be blank");
            } else {
                startActivity(intent);
                startActivity(intent1);
                overridePendingTransition(R.anim.left_side_in, R.anim.right_side_out);
            }
        });

    }
}