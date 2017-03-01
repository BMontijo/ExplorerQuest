package com.example.montijo.explorerquest_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // find story text view
        TextView story = (TextView) findViewById(R.id.text_story);

        // find next button
        Button next = (Button) findViewById(R.id.button_next);


    }
}
