package com.example.montijo.explorerquest_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CharacterSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        // find Dora button
        Button dora = (Button) findViewById(R.id.button_dora);

        // set onClickListener to Dora Button
        dora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create Dora character
                Character hero = new Character("Dora");

                // create intent to start Story Activity
                Intent storyIntent = new Intent(CharacterSelectionActivity.this, StoryActivity.class);

                // pass character to activity intent
                //storyIntent.putExtra(, hero);

                // start the new activity
                startActivity(storyIntent);
            }
        });

        // find Boots button
        Button boots = (Button) findViewById(R.id.button_boots);

        // set onClickListener to Boots Button
        boots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO add character creation code here

                // create intent to start Story Activity
                Intent storyIntent = new Intent(CharacterSelectionActivity.this, StoryActivity.class);

                // start the new activity
                startActivity(storyIntent);
            }
        });
    }
}
