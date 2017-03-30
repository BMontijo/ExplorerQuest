package com.example.montijo.explorerquest_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CombatActivity extends AppCompatActivity {

    // variables for activity views
    private TextView combatText;
    private TextView heroStatus;
    private TextView enemyStatus;
    private Button attack;
    private Button magic;

    // variables for characters
    private Character hero;
    private Character enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        // find views
        combatText = (TextView) findViewById(R.id.text_combat);
        heroStatus = (TextView) findViewById(R.id.text_hero_stats);
        enemyStatus = (TextView)findViewById(R.id.text_enemy_stats);
        attack = (Button) findViewById(R.id.button_attack);
        magic = (Button)findViewById(R.id.buton_magic);

        // capture characters
        hero = (Character) getIntent().getSerializableExtra("hero");
        enemy = (Character) getIntent().getSerializableExtra("enemy");

        // set up hero and enemy status views
        heroStatusUpdate();
        enemyStatusUpdate();

        // onClick listeners for buttons
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heroAttack();
                enemyAttack();
            }
        });

        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heroMagic();
                enemyAttack();
            }
        });

        // check initiative
        initiativeCheck();
    }

    private void heroStatusUpdate() {
        // update the hero status view with updated info
        heroStatus.setText(hero.getmName() + "\n" + hero.getmHealth() + "/" + hero.getmMaxHealth());
    }

    private void enemyStatusUpdate() {
        // update the enemy status view with updated info
        enemyStatus.setText(enemy.getmName() + "\n" + enemy.getmHealth() + "/" + enemy.getmMaxHealth());
    }

    private void heroAttack() {
        // get hero attack roll
        int aHeroAttack = hero.getmAttack() + Dice.rollD6();

        // get enemy defense roll
        int aEnemyDefense = enemy.getmDefense() + Dice.rollD6();

        if (aHeroAttack > aEnemyDefense) {
            // attack successful
            // roll damage
            int aHeroDamage = hero.getmDamage() + Dice.rollD6();

            // damage enemy
            enemy.setmHealth(enemy.getmHealth() - aHeroDamage);

            // update enemy status
            enemyStatusUpdate();

            // display success message
            combatText.setText(getString(R.string.combat_hero_attack_success, enemy.getmName(), aHeroDamage));
        } else {
            // attack failed
            // display failure message
            combatText.setText(getString(R.string.combat_hero_attack_failed, enemy.getmName()));
        }
    }

    private void enemyAttack() {
        // variable for enemy attack, damage, and type
        int aEnemyAttack;
        int aEnemyDamage;
        int aEnemyAttackType = Dice.rollD20();

        if (aEnemyAttackType <= 19) {
            // normal attack and damage
            aEnemyAttack = enemy.getmAttack() + Dice.rollD6();
            aEnemyDamage = enemy.getmDamage() + Dice.rollD6();
        } else {
            // magic attack and damage
            aEnemyAttack = enemy.getmMagic() + Dice.rollD6();
            aEnemyDamage = enemy.getmMagicDamage() + Dice.rollD6();
        }

        // get hero defense roll
        int aHeroDefense = hero.getmDefense() + Dice.rollD6();

        if (aEnemyAttack > aHeroDefense) {
            // attack successful
            // damage hero
            hero.setmHealth(hero.getmHealth() - aEnemyDamage);

            // update hero status
            heroStatusUpdate();

            // display sucess message
            combatText.append("\n" + getString(R.string.combat_enemy_attack_success, hero.getmName(), aEnemyDamage));
        } else {
            // attack failed
            // display failure message
            combatText.append("\n" + getString(R.string.combat_enemy_attack_failed, hero.getmName()));
        }


    }

    private void heroMagic() {
        // get hero attack roll
        int aHeroMagic = hero.getmMagic() + Dice.rollD6();

        // get enemy defense roll
        int aEnemyDefense = enemy.getmDefense() + Dice.rollD6();

        if (aHeroMagic > aEnemyDefense) {
            // attack successful
            // roll damage
            int aHeroMagicDamage = hero.getmMagicDamage() + Dice.rollD6();

            // damage enemy
            enemy.setmHealth(enemy.getmHealth() - aHeroMagicDamage);

            // update enemy status
            enemyStatusUpdate();

            // display success message
            combatText.setText(getString(R.string.combat_hero_attack_success, enemy.getmName(), aHeroMagicDamage));
        } else {
            // attack failed
            // display failure message
            combatText.setText(getString(R.string.combat_hero_attack_failed, enemy.getmName()));
        }
    }

    private void initiativeCheck() {
        // get character initiative rolls
        int heroInitiative = hero.getmAgility() + Dice.rollD6();
        int enemyInitiative = enemy.getmAgility() + Dice.rollD6();

        //compare initiatives
        if (heroInitiative >= enemyInitiative){
            // user wins - gets to attack first
            // set intro combat message
            combatText.setText(getString(R.string.combat_start, enemy.getmName()));
        } else {
            // enemy wins - gets to attack first
            enemyAttack();
        }
    }
}
