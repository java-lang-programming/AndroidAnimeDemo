package com.java_lang_programming.android_anime_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.java_lang_programming.android_anime_demo.article90.FloatingActionButtonActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_fa = findViewById(R.id.btn_fa);
        btn_fa.setOnClickListener(view -> {
            moveFloatingActionButtonActivity();
        });
    }

    private void moveFloatingActionButtonActivity() {
        Intent intent = new Intent(this, FloatingActionButtonActivity.class);
        startActivity(intent);
    }
}
