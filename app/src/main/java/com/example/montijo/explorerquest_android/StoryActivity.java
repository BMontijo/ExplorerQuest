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
    private int storyPosition = 0;
    private String[] storyElements = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // capture hero character
        hero = (Character) getIntent().getSerializableExtra("Hero");

        // find story text view
        story = (TextView) findViewById(R.id.text_story);

        // find next button
        next = (Button) findViewById(R.id.button_next);

        // initialize story elements array
        storyElements[0] = getString(R.string.story_intro_1);
        storyElements[1] = getString(R.string.story_intro_2);
        storyElements[2] = getString(R.string.story_test);

        // set first story element to story text view
        story.setText(storyElements[storyPosition]);

        // set onClick listener on next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call advance story function
                advanceStory(story.getText().toString());
            }
        });
    }

    private void advanceStory(String storyElement) {
        // check story element for combat flag
        if (storyElement.equals("Combat")) {
            // TODO add combat activity launch code here
        } else {
            // increment story position
            storyPosition++;

            if (storyPosition < storyElements.length) {
                // if there are more story elements update story text view
                story.setText(storyElements[storyPosition]);
            }
        }
    }
}
