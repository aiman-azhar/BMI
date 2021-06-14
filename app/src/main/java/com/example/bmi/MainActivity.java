package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText weight, height;
    TextView resultext;
    String calculation, BMICategory, healthRisk;
    ImageView aboutUs;
    SharedPreferences sharedPref;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resultext = findViewById(R.id.result);
        reset = findViewById(R.id.reset_btn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText("");
                weight.setText("");
            }
        });

        aboutUs = findViewById(R.id.about);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        sharedPref = this.getSharedPreferences("height", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        String hg = sharedPref.getString("height", "");
        String hgg = sharedPref.getString("weight", "");
        height.setText(hg);
        weight.setText(hgg);
    }

    public void calculateBMI(View view) {

                try{

                    String s1 = weight.getText().toString();
                    String s2 = height.getText().toString();

                    float weightValue = Float.parseFloat(s1);
                    float heightValue = Float.parseFloat(s2) / 100;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("weight", s1);
                    editor.putString("height", s2);
                    editor.apply();
                    float bmi = weightValue / (heightValue * heightValue);


                    if (bmi < 18.4) {
                        BMICategory = "Underweight";
                        healthRisk = "Malnutrition risk";
                    }else if (bmi >= 18.5 && bmi <= 24.9) {
                        BMICategory = "Normal weight";
                        healthRisk = "Low risk";
                    }else if (bmi >= 25 && bmi <= 29.9) {
                        BMICategory = "Overweight";
                        healthRisk = "Enhanced risk";
                    }else if (bmi >= 30 && bmi <= 34.9) {
                        BMICategory = "Moderately obese";
                        healthRisk = "Medium risk";
                    }else if (bmi >= 35 && bmi <= 39.9) {
                        BMICategory = "Severely obese";
                        healthRisk = "High risk";
                    }else {
                        BMICategory = "Very severely obese";
                        healthRisk = "Very high risk";
                    }

                    calculation = "BMI Category: " + BMICategory + "\n" + "BMI Range: " + bmi + "\n" + "Health Risk: " + healthRisk;
                    resultext.setText(calculation);

                } catch (Exception exception) {

                    Toast.makeText(this, "Please insert height and weight!", Toast.LENGTH_SHORT).show();
                }

        }

    }