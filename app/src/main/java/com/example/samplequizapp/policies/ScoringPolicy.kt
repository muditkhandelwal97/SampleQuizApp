package com.example.samplequizapp.policies

interface ScoringPolicy {
    fun calcualteScore(timeLeft: Double, totalTime: Double): Int
}