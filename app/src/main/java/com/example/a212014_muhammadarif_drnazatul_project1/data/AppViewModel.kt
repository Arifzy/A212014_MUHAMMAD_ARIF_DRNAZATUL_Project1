package com.example.a212014_muhammadarif_drnazatul_project1.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _userData = MutableStateFlow(UserSession())
    val userData: StateFlow<UserSession> = _userData.asStateFlow()

    fun setTimeGoal(time: String) {
        _userData.value = _userData.value.copy(timeGoal = time)
    }

    fun addScore(score: Int) {
        _userData.value = _userData.value.copy(totalScore = _userData.value.totalScore + score)
    }

    fun unlockLevel(levelId: String) {
        val currentUnlocked = _userData.value.unlockedLevels.toMutableSet()
        currentUnlocked.add(levelId)
        _userData.value = _userData.value.copy(unlockedLevels = currentUnlocked)
    }

    fun updateName(newName: String) {
        _userData.value = _userData.value.copy(name = newName)
    }
}