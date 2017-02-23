package com.example.montijo.explorerquest_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find start button
        Button start = (Button) findViewById(R.id.button_start);

        // set onClick listener to button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create intent to start Character Selection Activity
                Intent characterSelectionIntent = new Intent(MainActivity.this, CharacterSelectionActivity.class);

                // start the new activity
                startActivity(characterSelectionIntent);
            }
        } );
    }
}
