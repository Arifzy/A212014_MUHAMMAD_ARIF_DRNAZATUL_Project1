package com.example.a212014_muhammadarif_drnazatul_project1.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.a212014_muhammadarif_drnazatul_project1.R
import com.example.a212014_muhammadarif_drnazatul_project1.component.Menu
import com.example.a212014_muhammadarif_drnazatul_project1.component.TopUi
import com.example.a212014_muhammadarif_drnazatul_project1.data.AppViewModel
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.theme.AppTheme

@Composable
fun AccountScreen(navController: NavController, viewModel: AppViewModel) {
    val userData by viewModel.userData.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)))) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopUi(displayedTime = "${userData.timeGoal}m", score = userData.totalScore.toString())
            
            Spacer(modifier = Modifier.height(30.dp))
            
            Text(
                text = "Account Profile",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(30.dp))
            
            // Profile Picture in a Circle
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(30.dp))
            
            Text(
                text = "Username: ${userData.name}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(10.dp))
            
            Text(
                text = "Current Score: ${userData.totalScore}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            
            Text(
                text = "Daily Goal: ${userData.timeGoal} Minutes",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
        Menu(navController = navController, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    val mockNavController = rememberNavController()
    val mockViewModel: AppViewModel = viewModel()
    AppTheme {
        AccountScreen(navController = mockNavController, viewModel = mockViewModel)
    }
}
