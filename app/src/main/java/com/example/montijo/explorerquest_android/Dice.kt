package com.example.montijo.explorerquest_android

import java.util.*

/**
 * Created by Brian on 2/26/2017. Simplified and updated 2/16/2021.
 */
object Dice {
    fun roll(sides: Int): Int {
        val dieRoll = Random()
        return dieRoll.nextInt(sides) + 1
    }
}
