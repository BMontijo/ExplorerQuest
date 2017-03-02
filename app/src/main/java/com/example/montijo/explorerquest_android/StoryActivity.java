package com.example.montijo.explorerquest_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    private Character hero;
    private TextView story;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // capture hero character
        hero = (Character) getIntent().getSerializableExtra("Hero");

        // find story text view
        story = (TextView) findViewById(R.id.text_story);

        // Test code for getting character correctly
        story.setText("Name: " + hero.getmName() + "\nAgility: " + hero.getmAgility() + "\nMagic damage: "
                + hero.getmMagicDamage() + "\nMagic: " + hero.getmMagic() + "\nDamage: " +
                hero.getmDamage() + "\nDefense: " + hero.getmDefense() + "\nAttack: " + hero.getmAttack()
                + "\nHealth: " + hero.getmHealth()+ "\nMax Health: " + hero.getmMaxHealth());

        // find next button
        next = (Button) findViewById(R.id.button_next);

        // set onClick listener on next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Add story advancing code here
            }
        });


    }
}
