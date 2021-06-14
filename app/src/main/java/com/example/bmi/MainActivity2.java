package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    Button back;
    ImageView togithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        back = findViewById(R.id.back_btn);
        togithub = findViewById(R.id.github);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        togithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url("https://github.com/aiman-azhar/MyBMI");
            }
        });
    }

    private void url(String s) {
        Uri url = Uri.parse(s);
        startActivity(new Intent (Intent.ACTION_VIEW, url));
    }
}