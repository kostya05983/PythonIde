package org.jetspirit

import kotlin.random.Random

object AuthTools {

    fun generateCode(): String {
        val n = Random.nextInt(15)
        val sb = StringBuilder()
        for (i in 0 until n) {
            sb.append(Random.nextInt(240).toChar())
        }
        return sb.toString()
    }
}