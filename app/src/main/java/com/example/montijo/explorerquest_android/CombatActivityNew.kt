package com.example.montijo.explorerquest_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CombatActivityNew : AppCompatActivity() {
    // variables for activity views
    private var combatText: TextView? = null
    private var heroHealth: TextView? = null
    private var enemyHealth: TextView? = null

    // variables for characters
    private var hero: Character? = null
    private var enemy: Character? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combat)

        // find views
        combatText = findViewById<View>(R.id.text_combat) as TextView
        val heroName = findViewById<View>(R.id.text_hero_name) as TextView
        heroHealth = findViewById<View>(R.id.text_hero_health) as TextView
        val enemyName = findViewById<View>(R.id.text_enemy_name) as TextView
        enemyHealth = findViewById<View>(R.id.text_enemy_health) as TextView
        val attack = findViewById<View>(R.id.button_attack) as Button
        val magic = findViewById<View>(R.id.buton_magic) as Button

        // capture characters
        hero = intent.getSerializableExtra("hero") as Character?
        enemy = intent.getSerializableExtra("enemy") as Character?

        // populate hero and enemy status area
        heroName.text = hero!!.getmName()
        heroHealth!!.text = getString(R.string.combat_health_display, hero!!.getmHealth(), hero!!.getmMaxHealth())
        enemyName.text = enemy!!.getmName()
        enemyHealth!!.text = getString(R.string.combat_health_display, enemy!!.getmHealth(), enemy!!.getmMaxHealth())

        // onClick listeners for buttons
        attack.setOnClickListener {
            heroAttack()
            enemyAttack()
        }
        magic.setOnClickListener {
            heroMagic()
            enemyAttack()
        }

        // check initiative
        initiativeCheck()
    }

    private fun heroStatusUpdate() {
        // update the hero health view with updated info
        heroHealth!!.text = getString(R.string.combat_health_display, hero!!.getmHealth(), hero!!.getmMaxHealth())
    }

    private fun enemyStatusUpdate() {
        // update the enemy status view with updated info
        enemyHealth!!.text = getString(R.string.combat_health_display, enemy!!.getmHealth(), enemy!!.getmMaxHealth())
    }

    private fun heroAttack() {
        // get hero attack roll
        val aHeroAttack = hero!!.getmAttack() + Dice.roll(6)

        // get enemy defense roll
        val aEnemyDefense = enemy!!.getmDefense() + Dice.roll(6)
        if (aHeroAttack > aEnemyDefense) {
            // attack successful
            // roll damage
            val aHeroDamage = hero!!.getmDamage() + Dice.roll(6)

            // damage enemy
            enemy!!.setmHealth(enemy!!.getmHealth() - aHeroDamage)

            // update enemy status
            enemyStatusUpdate()

            // display success message
            combatText!!.text = getString(R.string.combat_hero_attack_success, enemy!!.getmName(), aHeroDamage)
        } else {
            // attack failed
            // display failure message
            combatText!!.text = getString(R.string.combat_hero_attack_failed, enemy!!.getmName())
        }

        // check if character died
        checkDeath()
    }

    private fun enemyAttack() {
        // variable for enemy attack, damage, and type
        val aEnemyAttack: Int
        val aEnemyDamage: Int
        val aEnemyAttackType = Dice.roll(20)
        if (aEnemyAttackType <= 18) {
            // normal attack and damage
            aEnemyAttack = enemy!!.getmAttack() + Dice.roll(6)
            aEnemyDamage = enemy!!.getmDamage() + Dice.roll(6)
        } else {
            // magic attack and damage
            aEnemyAttack = enemy!!.getmMagic() + Dice.roll(6)
            aEnemyDamage = enemy!!.getmMagicDamage() + Dice.roll(6)
        }

        // get hero defense roll
        val aHeroDefense = hero!!.getmDefense() + Dice.roll(6)
        if (aEnemyAttack > aHeroDefense) {
            // attack successful
            // damage hero
            hero!!.setmHealth(hero!!.getmHealth() - aEnemyDamage)

            // update hero status
            heroStatusUpdate()

            // display sucess message
            combatText!!.append("""
    
    
    ${getString(R.string.combat_enemy_attack_success, hero!!.getmName(), aEnemyDamage)}
    """.trimIndent())
        } else {
            // attack failed
            // display failure message
            combatText!!.append("""
    
    
    ${getString(R.string.combat_enemy_attack_failed, hero!!.getmName())}
    """.trimIndent())
        }

        // check if character died
        checkDeath()
    }

    private fun heroMagic() {
        // get hero attack roll
        val aHeroMagic = hero!!.getmMagic() + Dice.roll(6)

        // get enemy defense roll
        val aEnemyDefense = enemy!!.getmDefense() + Dice.roll(6)
        if (aHeroMagic > aEnemyDefense) {
            // attack successful
            // roll damage
            val aHeroMagicDamage = hero!!.getmMagicDamage() + Dice.roll(6)

            // damage enemy
            enemy!!.setmHealth(enemy!!.getmHealth() - aHeroMagicDamage)

            // update enemy status
            enemyStatusUpdate()

            // display success message
            combatText!!.text = getString(R.string.combat_hero_attack_success, enemy!!.getmName(), aHeroMagicDamage)
        } else {
            // attack failed
            // display failure message
            combatText!!.text = getString(R.string.combat_hero_attack_failed, enemy!!.getmName())
        }

        //check if character died
        checkDeath()
    }

    private fun initiativeCheck() {
        // get character initiative rolls
        val heroInitiative = hero!!.getmAgility() + Dice.roll(6)
        val enemyInitiative = enemy!!.getmAgility() + Dice.roll(6)

        //compare initiatives
        if (heroInitiative >= enemyInitiative) {
            // user wins - gets to attack first
            // set intro combat message
            combatText!!.text = getString(R.string.combat_start, enemy!!.getmName())
        } else {
            // enemy wins - gets to attack first
            // set intro combat message
            combatText!!.text = getString(R.string.combat_start_enemy, enemy!!.getmName())

            // enemy attacks
            enemyAttack()
        }
    }

    private fun checkDeath() {
        // check if hero is dead
        if (hero!!.getmHealth() <= 0) {
            // hero is dead
            hero!!.setmIsAlive(false)

            // create intent to go back to story activity
            val storyIntent = Intent(this@CombatActivityNew, StoryActivityNew::class.java)

            // add updated hero to intent
            storyIntent.putExtra("updatedHero", hero)

            //  set result
            setResult(RESULT_OK, storyIntent)

            // close combat
            finish()
        }

        // check if enemy is dead
        if (enemy!!.getmHealth() <= 0) {
            // enemy is dead
            enemy!!.setmIsAlive(false)

            // create intent to go bakc to story activity
            val storyIntent = Intent(this@CombatActivityNew, StoryActivityNew::class.java)

            // add updated hero to intent
            storyIntent.putExtra("updatedHero", hero)

            // set result
            setResult(RESULT_OK, storyIntent)

            // close combat
            finish()
        }
    }
}