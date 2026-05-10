package com.example.a212014_muhammadarif_drnazatul_project1.data

data class UserSession(
    val name: String = "User",
    val timeGoal: String = "0",
    val totalScore: Int = 0,
    val unlockedLevels: Set<String> = setOf("1")
)
