package com.example.montijo.explorerquest_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StoryActivity : AppCompatActivity() {
    private var hero: Character? = null
    private var story: TextView? = null
    private var next: Button? = null
    private var storyPosition = 0
    private val storyElements = arrayOfNulls<String>(12)
    private val map = Character("Map")
    private val grumpyTroll = Character("GrumpyTroll")
    private val swiper = Character("Swiper")
    private var combat = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        // capture hero character
        hero = intent.getSerializableExtra("Hero") as Character?

        // find story text view
        story = findViewById<View>(R.id.text_story) as TextView

        // find next button
        next = findViewById<View>(R.id.button_next) as Button

        // set combat flag to false
        combat = false

        // initialize story elements array
        storyElements[0] = getString(R.string.story_intro_1, hero!!.getmName())
        storyElements[1] = getString(R.string.story_intro_2, hero!!.getmName(), map.getmName())
        storyElements[2] = getString(R.string.story_intro_3, map.getmName(), hero!!.getmName(), hero!!.getmName(), map.getmName())
        storyElements[3] = getString(R.string.story_adventure_1, map.getmName(), hero!!.getmName(), grumpyTroll.getmName(), hero!!.getmName())
        storyElements[4] = getString(R.string.story_adventure_2, grumpyTroll.getmName())
        storyElements[5] = getString(R.string.story_adventure_3, hero!!.getmName(), grumpyTroll.getmName(), hero!!.getmName(), grumpyTroll.getmName())
        storyElements[6] = getString(R.string.story_finale_1, grumpyTroll.getmName(), swiper.getmName())
        storyElements[7] = getString(R.string.story_finale_2, hero!!.getmName(), swiper.getmName())
        storyElements[8] = getString(R.string.story_finale_3, hero!!.getmName(), swiper.getmName(), hero!!.getmName(), swiper.getmName())
        storyElements[9] = getString(R.string.story_ending, hero!!.getmName(), swiper.getmName(), hero!!.getmName())
        storyElements[10] = getString(R.string.story_credits)
        storyElements[11] = getString(R.string.story_game_over)

        // set first story element to story text view
        story!!.text = storyElements[storyPosition]

        // set onClick listener on next button
        next!!.setOnClickListener { // call advance story function
            advanceStory()
        }
    }

    private fun advanceStory() {
        // check combat flag
        if (combat == true) {
            // create combat activity intent
            val combatIntent = Intent(this@StoryActivity, CombatActivity::class.java)

            // pass hero to intent
            combatIntent.putExtra("hero", hero)

            // check storyPosition to pass proper enemy
            if (storyPosition == 2) {
                // pass Map enemy to intent
                combatIntent.putExtra("enemy", map)
            } else if (storyPosition == 5) {
                // pass Grumpy Troll to intent
                combatIntent.putExtra("enemy", grumpyTroll)
            } else if (storyPosition == 8) {
                // pass Swiper to intent
                combatIntent.putExtra("enemy", swiper)
            }

            // start the new activity
            startActivityForResult(combatIntent, COMBAT_RETURN)

            // reset combat flag
            combat = false
        } else {
            nextStory()
        }
    }

    private fun nextStory() {
        // increment story position
        storyPosition++

        // check if combat position, set combat flag
        if (storyPosition == 2 || storyPosition == 5 || storyPosition == 8) {
            combat = true
        }
        if (storyPosition < storyElements.size) {
            if (storyPosition == 10) {
                // arrived at credit screen disable next button
                next!!.isEnabled = false
            }
            // if there are more story elements update story text view
            story!!.text = storyElements[storyPosition]
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // ensure processing proper results
        if (resultCode == RESULT_OK && requestCode == COMBAT_RETURN) {
            // grab the updated character
            val results = data!!.extras

            // assign updated character to variable
            hero = results!!.getSerializable("updatedHero") as Character?
            if (BuildConfig.DEBUG && hero == null) {
                error("Assertion failed")
            }

            // check to see if hero is alive
            if (hero!!.ismIsAlive()) {
                //gather pre-level stats
                val preHealth = hero!!.getmMaxHealth()
                val preAttack = hero!!.getmAttack()
                val preMagic = hero!!.getmMagic()
                val preDefense = hero!!.getmDefense()
                val preDamage = hero!!.getmDamage()
                val preMagicDamage = hero!!.getmMagicDamage()
                val preAgility = hero!!.getmAgility()

                // level up
                hero!!.levelUp()

                // display level up message
                story!!.text = getString(R.string.story_level_up, hero!!.getmName(), preHealth, hero!!.getmMaxHealth(), preAttack, hero!!.getmAttack(), preMagic, hero!!.getmMagic(), preDefense, hero!!.getmDefense(), preDamage, hero!!.getmDamage(), preMagicDamage, hero!!.getmMagicDamage(), preAgility, hero!!.getmAgility())
            } else {
                // advance story position to end/game over screen
                storyPosition = 11

                // update story text to display game over screen
                story!!.text = storyElements[storyPosition]

                // disable next button
                next!!.isEnabled = false
            }
        }
    }

    companion object {
        // Combat return ID
        const val COMBAT_RETURN = 444
    }
}