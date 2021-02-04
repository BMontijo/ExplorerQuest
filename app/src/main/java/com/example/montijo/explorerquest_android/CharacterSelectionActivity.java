package com.example.montijo.explorerquest_android;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
                // call character creation for Dora
                createCharacter("Dora");
            }
        });

        // find Boots button
        Button boots = (Button) findViewById(R.id.button_boots);

        // set onClickListener to Boots Button
        boots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call character creation for Boots
                createCharacter("Boots");
            }
        });
    }

    private void createCharacter (String character) {
        // create chosen character
        Character hero = new Character(character);

        // create intent to start Story activity
        Intent storyIntent = new Intent(CharacterSelectionActivity.this, StoryActivity.class);

        // pass character to activity intent
        storyIntent.putExtra("Hero", hero);

        // start the new activity
        startActivity(storyIntent);
    }
}
