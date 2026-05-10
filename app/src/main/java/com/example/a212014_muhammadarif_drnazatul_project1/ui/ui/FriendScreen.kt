package com.example.a212014_muhammadarif_drnazatul_project1.ui.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.a212014_muhammadarif_drnazatul_project1.data.Friend
import com.example.a212014_muhammadarif_drnazatul_project1.ui.ui.theme.AppTheme

@Composable
fun FriendScreen(navController: NavController, viewModel: AppViewModel) {
    val userData by viewModel.userData.collectAsState()
    var selectedFriend by remember { mutableStateOf<Friend?>(null) }
    
    val friends = listOf(
        Friend("Ali", "Hi there!", "10:30 AM", R.drawable.user),
        Friend("Abu", "Jom makan", "Yesterday", R.drawable.freind),
        Friend("Siti", "Terima kasih", "Monday", R.drawable.user),
        Friend("Ahmad", "Apa khabar?", "02/05/2024", R.drawable.freind)
    )

    BackHandler(enabled = selectedFriend != null) {
        selectedFriend = null
    }

    Box(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)))) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedFriend == null) {
                TopUi(displayedTime = "${userData.timeGoal}m", score = userData.totalScore.toString())
                
                Spacer(modifier = Modifier.height(10.dp))
                
                Text(
                    text = "Chats",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Start).padding(start = 20.dp)
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    items(friends) { friend ->
                        FriendItem(friend) {
                            selectedFriend = friend
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 70.dp),
                            thickness = 0.5.dp,
                            color = Color.LightGray.copy(alpha = 0.5f)
                        )
                    }
                }
            } else {
                ChatFriend(friend = selectedFriend!!) {
                    selectedFriend = null
                }
            }
        }
        
        if (selectedFriend == null) {
            Menu(navController = navController, modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun FriendItem(friend: Friend, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = friend.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(15.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = friend.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = friend.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Text(
                text = friend.lastMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FriendScreenPreview() {
    val mockNavController = rememberNavController()
    val mockViewModel: AppViewModel = viewModel()
    AppTheme {
        FriendScreen(navController = mockNavController, viewModel = mockViewModel)
    }
}
