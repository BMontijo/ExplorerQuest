package com.example.montijo.explorerquest_android;

import java.io.Serializable;

/**
 * Created by Brian on 2/26/2017.
 */

public class Character implements Serializable {
    private String mName;
    private int mHealth;
    private int mMaxHealth;
    private int mAttack;
    private int mMagic;
    private int mDefense;
    private int mDamage;
    private int mMagicDamage;
    private int mAgility;
    private boolean mIsAlive;

    public Character(String character) {
        if (character.equals("Dora")) {
            // create Dora character - warrior
            this.setmName("Dora");
            this.setmHealth(100);
            this.setmMaxHealth(100);
            this.setmAttack(10);
            this.setmMagic(1);
            this.setmDefense(8);
            this.setmDamage(10);
            this.setmMagicDamage(3);
            this.setmAgility(3);
            this.setmIsAlive(true);
        } else if (character.equals("Boots")) {
            // create Boots character - mage
            this.setmName("Boots");
            this.setmHealth(100);
            this.setmMaxHealth(100);
            this.setmAttack(3);
            this.setmMagic(10);
            this.setmDefense(5);
            this.setmDamage(4);
            this.setmMagicDamage(10);
            this.setmAgility(6);
            this.setmIsAlive(true);
        }
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmMagicDamage() {
        return mMagicDamage;
    }

    public void setmMagicDamage(int mMagicDamage) {
        this.mMagicDamage = mMagicDamage;
    }

    public int getmAttack() {
        return mAttack;
    }

    public void setmAttack(int mAttack) {
        this.mAttack = mAttack;
    }

    public int getmMagic() {
        return mMagic;
    }

    public void setmMagic(int mMagic) {
        this.mMagic = mMagic;
    }

    public int getmDefense() {
        return mDefense;
    }

    public void setmDefense(int mDefense) {
        this.mDefense = mDefense;
    }

    public int getmDamage() {
        return mDamage;
    }

    public void setmDamage(int mDamage) {
        this.mDamage = mDamage;
    }

    public int getmAgility() {
        return mAgility;
    }

    public void setmAgility(int mAgility) {
        this.mAgility = mAgility;
    }

    public boolean ismIsAlive() {
        return mIsAlive;
    }

    public void setmIsAlive(boolean mIsAlive) {
        this.mIsAlive = mIsAlive;
    }

    public int getmHealth() {
        return mHealth;
    }

    public void setmHealth(int mHealth) {
        this.mHealth = mHealth;
    }

    public int getmMaxHealth() {
        return mMaxHealth;
    }

    public void setmMaxHealth(int mMaxHealth) {
        this.mMaxHealth = mMaxHealth;
    }

    public void levelUp() {
        // increase character stats
        this.setmMaxHealth(this.getmMaxHealth() + this.getmMaxHealth());
        this.setmAgility(mAgility + 2);

        // increase mAttack and mDefense depending on character
        if (this.getmName().equals("Dora")) {
            // Dora - warrior increase focus on power
            this.setmAttack(mAttack + 10);
            this.setmMagic(mMagic + 2);
            this.setmDefense(mDefense + 8);
            this.setmDamage(mDamage + 5);
            this.setmMagicDamage(mMagicDamage + 3);
        } else if (this.getmName().equals("Boots")) {
            // Boots - mage increase focus on mMagic
            this.setmAttack(mAttack + 3);
            this.setmMagic(mMagic + 10);
            this.setmDefense(mDefense + 3);
            this.setmDamage(mDamage + 3);
            this.setmMagicDamage(mMagicDamage + 5);
        }

        //replenish mHealth
        this.setmHealth(this.getmMaxHealth());
    }
}