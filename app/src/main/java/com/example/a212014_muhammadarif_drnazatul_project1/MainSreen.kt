package com.example.a212014_muhammadarif_drnazatul_project1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.a212014_muhammadarif_drnazatul_project1.component.*
import com.example.a212014_muhammadarif_drnazatul_project1.data.AppViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.theme.AppTheme

@Composable
fun MainScreen(navController: NavController, viewModel: AppViewModel) {
    val userData by viewModel.userData.collectAsState()
    val levels = remember { listOf("1", "2", "3", "4", "5", "6", "7", "8") }

    Box(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)))) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().background(Color.White.copy(alpha = 0.2f)).padding(top = 35.dp, bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopUi(displayedTime = "${userData.timeGoal}m", score = userData.totalScore.toString())
                    LevelCard(title = "Unit 1", description = "Learn Basic Bahasa Melayu")
                }
            }

            itemsIndexed(levels) { index, lvl ->
                val xOffset = when (index % 4) {
                    1 -> 70.dp
                    3 -> (-70).dp
                    else -> 0.dp
                }
                
                val isLocked = !userData.unlockedLevels.contains(lvl)
                
                Level(
                    level = lvl,
                    isLocked = isLocked,
                    color = MaterialTheme.colorScheme.scrim,
                    modifier = Modifier.offset(x = xOffset),
                    onClick = { navController.navigate("quiz/$lvl") }
                )
            }
        }
        Menu(navController = navController, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val mockNavController = rememberNavController()
    val mockViewModel: AppViewModel = viewModel()

    AppTheme {
        MainScreen(
            navController = mockNavController,
            viewModel = mockViewModel
        )
    }
}
