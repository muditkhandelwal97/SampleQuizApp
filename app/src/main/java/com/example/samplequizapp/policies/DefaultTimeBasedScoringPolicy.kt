package com.example.samplequizapp.policies

class DefaultTimeBasedScoringPolicy: ScoringPolicy {

    val SCORE_MULTIPLIER = 1
    override fun calcualteScore(timeLeft: Double, totalTime: Double): Int {
        return (totalTime - timeLeft).toInt() * SCORE_MULTIPLIER
    }
}