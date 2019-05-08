package com.example.montijo.explorerquest_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Media Player
    //MediaPlayer mMp;

    //TODO mediaManager listener onComplete loop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO mediaManager start main music

        // find start button
        Button start = findViewById(R.id.button_start);

        // set onClick listener to button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create intent to start Character Selection Activity
                Intent characterSelectionIntent = new Intent(MainActivity.this, CharacterSelectionActivity.class);

                //TODO stop mediaManager

                // start the new activity
                startActivity(characterSelectionIntent);
            }
        } );

    }

    @Override
    public void onStop() {
        super.onStop();

        //TODO stop mediaManager

    }
}
