package com.example.a212014_muhammadarif_drnazatul_project1.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.a212014_muhammadarif_drnazatul_project1.data.AppViewModel
import com.example.a212014_muhammadarif_drnazatul_project1.data.LevelContent
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.theme.AppTheme
import model.Question

@Composable
fun QuizScreen(levelId: String, navController: NavController, viewModel: AppViewModel) {
    val questions = LevelContent.questionsMap[levelId] ?: emptyList()
    val answers = remember { mutableStateListOf<String>().apply { 
        addAll(List(questions.size) { "" }) 
    } }
    var showResult by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz - Level $levelId", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(20.dp))

        if (questions.isEmpty()) {
            Text("No questions available for this level yet.", color = MaterialTheme.colorScheme.error)
        } else {
            questions.forEachIndexed { index, question ->
                QuizCard(
                    ques = "${index + 1}. ${question.text}",
                    opts = question.options,
                    selected = answers[index],
                    disabled = showResult,
                    onSelect = { answers[index] = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (!showResult && questions.isNotEmpty()) {
            Button(
                onClick = {
                    var correctCount = 0
                    questions.forEachIndexed { index, question ->
                        if (question.options.indexOf(answers[index]) == question.correctAnswerIndex) {
                            correctCount++
                        }
                    }
                    score = (correctCount * 100) / questions.size
                    viewModel.addScore(score)
                    
                    // Unlock next level if score is high enough (e.g., 50%)
                    if (score >= 50) {
                        val nextLevelInt = levelId.toIntOrNull()?.plus(1)
                        if (nextLevelInt != null) {
                            viewModel.unlockLevel(nextLevelInt.toString())
                        }
                    }
                    
                    showResult = true
                },
                enabled = answers.all { it.isNotEmpty() },
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
            ) {
                Text("Submit Answers")
            }
        } else if (showResult) {
            Text("Your Mark: $score / 100", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            if (score < 50) {
                Text("Score at least 50 to unlock the next level!", color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Return to Home")
            }
        } else if (questions.isEmpty()) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun QuizCard(ques: String, opts: List<String>, selected: String, disabled: Boolean, onSelect: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(ques, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            opts.forEach { opt ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (opt == selected),
                            onClick = { if (!disabled) onSelect(opt) }
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = (opt == selected), onClick = null)
                    Text(opt, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    val mockNavController = rememberNavController()
    val mockViewModel: AppViewModel = viewModel()

    AppTheme {
        QuizScreen(
            levelId = "1",
            navController = mockNavController,
            viewModel = mockViewModel
        )
    }
}
