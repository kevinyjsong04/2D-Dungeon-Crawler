package com.example.dungeoncrawler.view;
import com.example.dungeoncrawler.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.dungeoncrawler.view.PreGameConfiguration;
public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen); // change screen resource
        Button startBtn = findViewById(R.id.start); // change button resource

        // Starts app on click
        startBtn.setOnClickListener(v -> {
            Intent start = new Intent(this, PreGameConfiguration.class);
            startActivity(start);
            finish();
        });

        Button exitBtn = (Button) findViewById(R.id.exit); // change button resource

        // Exits app on click
        exitBtn.setOnClickListener(v -> {
            System.exit(0);
            finish();
        });
    }
}
