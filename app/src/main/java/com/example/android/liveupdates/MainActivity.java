package com.example.android.liveupdates;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button topicBtn = findViewById(R.id.topicButton);
        Button genr = findViewById(R.id.genreButton);

        topicBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent toTopicActivity = new Intent(MainActivity.this, TopicActivity.class);
                startActivity(toTopicActivity);
            }
        });

        genr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTTopicActivity = new Intent(MainActivity.this, NewMainActivity.class);
                startActivity(toTTopicActivity);
            }
        });
    }
}
