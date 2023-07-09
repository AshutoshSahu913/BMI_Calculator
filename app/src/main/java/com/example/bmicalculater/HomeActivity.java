package com.example.bmicalculater;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    EditText height, weight, age;
    ImageView weightMinus, weightPlus, ageMinus, agePlus;
    Button calculateBMI;
    TextView userNameTxt;
    SeekBar seekBar;
    LinearLayout linearLayout1,linearLayout2;

    int int_weight=55;
    int int_age=18;
    int current_progress;

    String m_int_progress = "170";
    String type_of_user ="0";
    String weight2="50";
    String age2="22";




    public String userName;

    @SuppressLint({"MissingInflatedId", "WrongViewCast", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);
        Objects.requireNonNull(getSupportActionBar()).hide();

        userNameTxt = findViewById(R.id.userName);

        linearLayout1=findViewById(R.id.linearLayout1);
        linearLayout2=findViewById(R.id.linearLayout2);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        age = findViewById(R.id.age);

        seekBar=findViewById(R.id.seekBar);

        weightMinus = findViewById(R.id.weightMinus);
        weightPlus = findViewById(R.id.weigtPlus);

        ageMinus = findViewById(R.id.ageMinus);
        agePlus = findViewById(R.id.agePlus);

        calculateBMI = findViewById(R.id.calculateBMI);


        //Someone click on male or female change there background

        linearLayout1.setOnClickListener(v -> {
            linearLayout1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_foucs));
            linearLayout2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_nofoucs));
            type_of_user ="Man";
        });

        linearLayout2.setOnClickListener(v -> {
            linearLayout2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_foucs));
            linearLayout1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_nofoucs));
            type_of_user ="Woman";
        });

        seekBar.setMax(300);
        seekBar.setProgress(170);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_progress=progress;
                m_int_progress=String.valueOf(current_progress);
                height.setText(m_int_progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        agePlus.setOnClickListener(v->{
            int_age=int_age+1;
            age2=String.valueOf(int_age);
            age.setText(age2);
        });

        weightPlus.setOnClickListener(v->{
            int_weight=int_weight+1;
            weight2=String.valueOf(int_weight);
            weight.setText(weight2);
        });

        ageMinus.setOnClickListener(v->{
            int_age=int_age-1;
            age2=String.valueOf(int_age);
            age.setText(age2);
        });

        weightMinus.setOnClickListener(v->{
            int_weight=int_weight-1;
            weight2=String.valueOf(int_weight);
            weight.setText(weight2);
        });



        Intent intent1 = getIntent();
        userName = intent1.getStringExtra("enterName");
        userNameTxt.setText("Hey ! " + userName);

        calculateBMI.setOnClickListener(v -> {
            if (type_of_user.equals("0")) {//check select gender
                Toast.makeText(this, "Select Your Gender First", Toast.LENGTH_SHORT).show();
            } else if (m_int_progress.equals("0")) {//check select height
                Toast.makeText(this, "Select Your Height First", Toast.LENGTH_SHORT).show();
            } else if (int_age == 0 || int_age < 0) {//check select age
                Toast.makeText(this, "Age is Incorrect", Toast.LENGTH_SHORT).show();
            } else if (int_weight == 0 || int_weight < 0) {//check select weight
                Toast.makeText(this, "Weight is Incorrect", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), ResultBMI.class);
                intent.putExtra("gender", type_of_user);
                intent.putExtra("height", m_int_progress);
                intent.putExtra("weight", weight2);
                intent.putExtra("age", age2);
                startActivity(intent);
                overridePendingTransition(R.anim.left_side_in,R.anim.right_side_out);
            }
        });
    }
}