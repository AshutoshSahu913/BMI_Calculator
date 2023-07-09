package com.example.bmicalculater;

import static android.graphics.Color.YELLOW;
import static com.example.bmicalculater.R.anim;
import static com.example.bmicalculater.R.color;
import static com.example.bmicalculater.R.drawable;
import static com.example.bmicalculater.R.id;
import static com.example.bmicalculater.R.layout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Objects;


public class ResultBMI extends AppCompatActivity {
    Animation tTb_anim, bTt_anim;
    ImageView ansImg;
    Button recalculateBMI;

    TextView bmiResult, bmiCategory, bmi_gender;

    Intent intent;

    String m_bmi;
    float int_mbi;
    String height;
    String weight;
    float int_height, int_weight;

    LinearLayout bmiBackground;


    @SuppressLint({"MissingInflatedId", "ResourceAsColor", "ResourceType", "UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.activity_result_bmi);
        Objects.requireNonNull(getSupportActionBar()).
                hide();

        bmiBackground = findViewById(id.bmiBackground);
        intent = getIntent();
        ansImg = findViewById(id.ansImg);
        bmiBackground = findViewById(id.bmiBackground);
        tTb_anim = AnimationUtils.loadAnimation(this, anim.top_to_bottom_animation);
        bTt_anim = AnimationUtils.loadAnimation(this, anim.bottom_to_top_animation);
        recalculateBMI = findViewById(id.RecalculateBMI);
        ansImg.setAnimation(tTb_anim);
        bmi_gender = findViewById(id.bmi_gender);
        bmiCategory = findViewById(id.bmiCategory);
        bmiResult = findViewById(id.bmiResult);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        int_height = Float.parseFloat(height);
        int_weight = Float.parseFloat(weight);

        int_height = int_height / 100;
        int_mbi = int_weight / (int_height * int_height);

        m_bmi = Float.toString(int_mbi);

        if (int_mbi < 18.5) {
            bmiCategory.setText("YOU ARE UNDERWEIGHT");
            bmiBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, drawable.gradient_warning));
            recalculateBMI.setBackgroundColor(YELLOW);
            ansImg.setImageResource(drawable.diet);
        } else if (int_mbi >= 18.5 && int_mbi < 24.9) {
            bmiCategory.setText("YOU ARE IDEAL CONDITION");
            bmiBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, drawable.gradient_splash_screen));
            recalculateBMI.setBackgroundColor(getResources().getColor(color.light_blue));
            ansImg.setImageResource(drawable.muscleman);
        } else if (int_mbi >= 24.9 && int_mbi < 30) {
            bmiCategory.setText("YOU ARE OVERWEIGHT");
            bmiBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, drawable.gradient_error));
            recalculateBMI.setBackgroundColor(getResources().getColor(color.error1));
            ansImg.setImageResource(drawable.overweight);
        } else {
            bmiCategory.setText("YOU ARE SUFFERING FROM OBESITY");
            bmiBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, drawable.gradient_error));
            recalculateBMI.setBackgroundColor(getResources().getColor(color.error3));
            ansImg.setImageResource(drawable.obesity);
        }

        bmi_gender.setText(intent.getStringExtra("gender"));
        bmiResult.setText(m_bmi);

        recalculateBMI.setOnClickListener(v ->
        {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
    }
}