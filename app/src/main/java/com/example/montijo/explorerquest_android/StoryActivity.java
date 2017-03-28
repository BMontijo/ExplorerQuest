package com.example.montijo.explorerquest_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StoryActivity extends AppCompatActivity {

    private Character mHero;
    private TextView mStory;
    private Button mNext;
    private int storyPosition = 0;
    private String[] storyElements = new String[12];
    private Character map = new Character("Map");
    private Character grumpyTroll = new Character("GrumpyTroll");
    private Character swiper = new Character("Swiper");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // capture mHero character
        mHero = (Character) getIntent().getSerializableExtra("Hero");

        // find mStory text view
        mStory = (TextView) findViewById(R.id.text_story);

        // find next button
        mNext = (Button) findViewById(R.id.button_next);

        // initialize mStory elements array
        storyElements[0] = getString(R.string.story_intro_1, mHero.getmName());
        storyElements[1] = getString(R.string.story_intro_2, mHero.getmName(), map.getmName());
        storyElements[2] = getString(R.string.story_intro_3, map.getmName(), mHero.getmName(), mHero.getmName(), map.getmName());
        storyElements[3] = getString(R.string.story_adventure_1, map.getmName(), mHero.getmName(), grumpyTroll.getmName(), mHero.getmName());
        storyElements[4] = getString(R.string.story_adventure_2, grumpyTroll.getmName());
        storyElements[5] = getString(R.string.story_adventure_3, mHero.getmName(), grumpyTroll.getmName(), mHero.getmName(), grumpyTroll.getmName());
        storyElements[6] = getString(R.string.story_finale_1, grumpyTroll.getmName(), swiper.getmName());
        storyElements[7] = getString(R.string.story_finale_2, mHero.getmName(), swiper.getmName());
        storyElements[8] = getString(R.string.story_finale_3, mHero.getmName(), swiper.getmName(), mHero.getmName(), swiper.getmName());
        storyElements[9] = getString(R.string.story_ending, mHero.getmName(), swiper.getmName(), mHero.getmName());
        storyElements[10] = getString(R.string.story_credits);
        storyElements[11] = getString(R.string.story_game_over);

        // set first mStory element to mStory text view
        mStory.setText(storyElements[storyPosition]);

        // set onClick listener on next button
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call advance mStory function
                advanceStory(storyPosition);
            }
        });
    }

    private void advanceStory(int lStoryPosition) {
        // check mStory element for combat flag
        if (lStoryPosition == 2 || lStoryPosition == 5 || lStoryPosition == 8) {
            // TODO add combat activity launch code here
            // test toast
            Toast.makeText(this, "Combat starts now!", Toast.LENGTH_SHORT).show();
        }

        // increment mStory position
        storyPosition++;

        if (storyPosition < storyElements.length) {
            // if there are more mStory elements update mStory text view
            mStory.setText(storyElements[storyPosition]);
        }
    }
}
