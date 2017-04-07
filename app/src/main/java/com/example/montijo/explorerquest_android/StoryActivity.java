package com.example.montijo.explorerquest_android;

import android.content.Intent;
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
    private String[] storyElements = new String[12];
    private Character map = new Character("Map");
    private Character grumpyTroll = new Character("GrumpyTroll");
    private Character swiper = new Character("Swiper");
    private boolean combat = false;

    // Combat return ID
    public static final int COMBAT_RETURN = 444;

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
        storyElements[0] = getString(R.string.story_intro_1, hero.getmName());
        storyElements[1] = getString(R.string.story_intro_2, hero.getmName(), map.getmName());
        storyElements[2] = getString(R.string.story_intro_3, map.getmName(), hero.getmName(), hero.getmName(), map.getmName());
        storyElements[3] = getString(R.string.story_adventure_1, map.getmName(), hero.getmName(), grumpyTroll.getmName(), hero.getmName());
        storyElements[4] = getString(R.string.story_adventure_2, grumpyTroll.getmName());
        storyElements[5] = getString(R.string.story_adventure_3, hero.getmName(), grumpyTroll.getmName(), hero.getmName(), grumpyTroll.getmName());
        storyElements[6] = getString(R.string.story_finale_1, grumpyTroll.getmName(), swiper.getmName());
        storyElements[7] = getString(R.string.story_finale_2, hero.getmName(), swiper.getmName());
        storyElements[8] = getString(R.string.story_finale_3, hero.getmName(), swiper.getmName(), hero.getmName(), swiper.getmName());
        storyElements[9] = getString(R.string.story_ending, hero.getmName(), swiper.getmName(), hero.getmName());
        storyElements[10] = getString(R.string.story_credits);
        storyElements[11] = getString(R.string.story_game_over);

        // set first story element to story text view
        story.setText(storyElements[storyPosition]);

        //increment story position
        storyPosition++;

        // set onClick listener on next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call advance story function
                advanceStory(storyPosition);
            }
        });
    }

    private void advanceStory(int lStoryPosition) {
        // check story element for combat flag
        if (lStoryPosition == 2 || lStoryPosition == 5 || lStoryPosition == 8) {
            // create combat activity intent
            Intent combatIntent = new Intent(StoryActivity.this, CombatActivity.class);

            // pass hero to intent
            combatIntent.putExtra("hero", hero);

            // check lStoryPosition to pass proper enemy
            if (lStoryPosition == 2) {
                // pass Map enemy to intent
                combatIntent.putExtra("enemy", map);
            } else if (lStoryPosition == 5) {
                // pass Grumpy Troll to intent
                combatIntent.putExtra("enemy", grumpyTroll);
            } else {
                // pass Swiper to intent
                combatIntent.putExtra("enemy", swiper);
            }

            // start the new activity
            startActivityForResult(combatIntent, COMBAT_RETURN);
        } else {
            // not combat situation call next story element
            nextStory();
        }
    }

    private void nextStory() {
        if (storyPosition < storyElements.length) {
            // if there are more story elements update story text view
            story.setText(storyElements[storyPosition]);
        }

        // increment story position
        storyPosition++;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // ensure processing proper results
        if (resultCode == RESULT_OK && requestCode == COMBAT_RETURN) {
            // grab the updated character
            Bundle results = data.getExtras();

            // assign updated character to variable
            hero = (Character) results.getSerializable("updatedHero");

            // assert that hero is not null
            assert hero != null;

            // check to see if hero is alive
            if (hero.ismIsAlive()) {
                //gather pre-level stats
                int preHealth = hero.getmMaxHealth();
                int preAttack = hero.getmAttack();
                int preMagic = hero.getmMagic();
                int preDefense = hero.getmDefense();
                int preDamage = hero.getmDamage();
                int preMagicDamage = hero.getmMagicDamage();
                int preAgility = hero.getmAgility();

                // level up
                hero.levelUp();

                // display level up message
                story.setText(getString(R.string.story_level_up, hero.getmName(), preHealth, hero.getmMaxHealth(), preAttack, hero.getmAttack(), preMagic, hero.getmMagic(), preDefense, hero.getmDefense(), preDamage, hero.getmDamage(), preMagicDamage, hero.getmMagicDamage(), preAgility, hero.getmAgility()));
            } else {
                // advance story position to end/game over screen
                storyPosition = 11;

                // update story text to display game over screen
                story.setText(storyElements[storyPosition]);

                // disable next button
                next.setEnabled(false);
            }
        }
    }
}
