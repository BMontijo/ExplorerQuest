package com.example.montijo.explorerquest_android;

/**
 * Created by Brian on 2/26/2017.
 */

public class Character {
    private String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int magic;
    private int defense;
    private int damage;
    private int magicDamage;
    private int agility;
    private boolean isAlive;

    public Character(String character) {
        if (character.equals("Dora")) {
            // create Dora character - warrior
            this.setName("Dora");
            this.setHealth(100);
            this.setAttack(10);
            this.setMagic(1);
            this.setDefense(8);
            this.setDamage(10);
            this.setMagicDamage(3);
            this.setAgility(3);
            this.setAlive(true);
        } else if (character.equals("Boots")) {
            // create Boots character - mage
            this.setName("Boots");
            this.setHealth(100);
            this.setAttack(3);
            this.setMagic(10);
            this.setDefense(5);
            this.setDamage(4);
            this.setMagicDamage(10);
            this.setAgility(6);
            this.setAlive(true);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMagicDamage() {
        return magicDamage + Dice.rollD6();
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getAttack() {
        return attack + Dice.rollD6();
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMagic() {
        return magic + Dice.rollD6();
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDefense() {
        return defense + Dice.rollD6();
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage + Dice.rollD6();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAgility() {
        return agility + Dice.rollD6();
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void levelUp() {
        // increase character stats
        this.setMaxHealth(this.getMaxHealth() + this.getMaxHealth());
        this.setAgility(agility + 2);

        // increase attack and defense depending on character
        if (this.getName().equals("Dora")) {
            // Dora - warrior increase focus on power
            this.setAttack(attack + 10);
            this.setMagic(magic + 2);
            this.setDefense(defense + 8);
            this.setDamage(damage + 5);
            this.setMagicDamage(magicDamage + 3);
        } else if (this.getName().equals("Boots")) {
            // Boots - mage increase focus on magic
            this.setAttack(attack + 3);
            this.setMagic(magic + 10);
            this.setDefense(defense + 3);
            this.setDamage(damage + 3);
            this.setMagicDamage(magicDamage + 5);
        }

        //replenish health
        this.setHealth(this.getMaxHealth());
    }
}