package com.example.a212014_muhammadarif_drnazatul_project1.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.a212014_muhammadarif_drnazatul_project1.data.AppViewModel
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.theme.AppTheme


@Composable
fun SetupScreen(navController: NavController, viewModel: AppViewModel) {
    var timeInput by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary))), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.padding(24.dp).fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Set Your Daily Goal", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(value = timeInput, onValueChange = { timeInput = it }, label = { Text("Minutes per day") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth(), singleLine = true)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    viewModel.setTimeGoal(if (timeInput.isEmpty()) "0" else timeInput)
                    navController.navigate("home") { popUpTo("setup") { inclusive = true } }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Start Learning", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SetupScreenPreview() {
    // Provide a dummy NavController and ViewModel for the preview
    val mockNavController = rememberNavController()
    val mockViewModel: AppViewModel = viewModel()

    AppTheme() {
        SetupScreen(
            navController = mockNavController,
            viewModel = mockViewModel
        )
    }
}