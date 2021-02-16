package com.example.montijo.explorerquest_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CharacterSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_selection)

        // find Dora button
        val dora = findViewById<View>(R.id.button_dora) as Button

        // set onClickListener to Dora Button
        dora.setOnClickListener { // call character creation for Dora
            createCharacter("Dora")
        }

        // find Boots button
        val boots = findViewById<View>(R.id.button_boots) as Button

        // set onClickListener to Boots Button
        boots.setOnClickListener { // call character creation for Boots
            createCharacter("Boots")
        }
    }

    private fun createCharacter(character: String) {
        // create chosen character
        val hero = Character(character)

        // create intent to start Story activity
        val storyIntent = Intent(this@CharacterSelectionActivity, StoryActivity::class.java)

        // pass character to activity intent
        storyIntent.putExtra("Hero", hero)

        // start the new activity
        startActivity(storyIntent)
    }
}