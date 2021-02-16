package com.example.montijo.explorerquest_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivityNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)

        // find start button
        val start = findViewById<View>(R.id.button_start) as Button

        // set onClick listener to button
        start.setOnClickListener { // create intent to start Character Selection Activity
            val characterSelectionIntent = Intent(this@MainActivityNew, CharacterSelectionActivityNew::class.java)

            // start the new activity
            startActivity(characterSelectionIntent)
        }
    }
}