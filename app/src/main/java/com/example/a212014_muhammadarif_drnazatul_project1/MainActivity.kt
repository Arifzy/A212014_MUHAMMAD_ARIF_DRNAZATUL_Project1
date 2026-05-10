package com.example.a212014_muhammadarif_drnazatul_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a212014_muhammadarif_drnazatul_project1.data.AppViewModel
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.*
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val viewModel: AppViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "setup") {
                        composable("setup") { SetupScreen(navController, viewModel) }
                        composable("home") { MainScreen(navController, viewModel) }
                        composable("account") { AccountScreen(navController, viewModel) }
                        composable("friends") { FriendScreen(navController, viewModel) }
                        composable("quiz/{levelId}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("levelId") ?: "1"
                            QuizScreen(id, navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}