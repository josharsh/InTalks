package com.example.android.liveupdates;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText searchBoxEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBoxEditText = findViewById(R.id.main_search);

        Button topicBtn = findViewById(R.id.topicButton);
        Button genr = findViewById(R.id.genreButton);

        topicBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String githubQuery = searchBoxEditText.getText().toString();
                Intent toTopicActivity = new Intent(MainActivity.this, TopicActivity.class);
                toTopicActivity.putExtra("query", githubQuery);
                startActivity(toTopicActivity);
            }
        });

        genr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String githubQuery = searchBoxEditText.getText().toString();
                Intent toTTopicActivity = new Intent(MainActivity.this, NewMainActivity.class);
                toTTopicActivity.putExtra("query", githubQuery);
                startActivity(toTTopicActivity);
            }
        });
    }
}
