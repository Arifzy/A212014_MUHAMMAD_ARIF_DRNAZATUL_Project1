package com.example.a212014_muhammadarif_drnazatul_project1.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a212014_muhammadarif_drnazatul_project1.R

@Composable
fun TopUi(displayedTime: String, score: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.malaysiaflag),
            contentDescription = null,
            modifier = Modifier.size(45.dp).shadow(4.dp, CircleShape).clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(45.dp)) {
            Image(painter = painterResource(id = R.drawable.fire), contentDescription = null)
            Text(text = score, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
        }

        Text(
            text = displayedTime, 
            fontSize = 26.sp, 
            fontWeight = FontWeight.Black, 
            color = Color.Black, 
            style = TextStyle(shadow = Shadow(color = Color.White, blurRadius = 5f))
        )
    }
}

@Composable
fun LevelCard(title: String, description: String) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer)

    Card(modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 10.dp)) {
        Column(modifier = Modifier.animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium)).background(color = color)) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.onSurface)
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(painter = painterResource(id = R.drawable.downarrow), contentDescription = null, modifier = Modifier.size(30.dp).rotate(if (expanded) 180f else 0f), tint = MaterialTheme.colorScheme.outline)
                }
            }
            if (expanded) {
                Text(text = description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
            }
        }
    }
}

@Composable
fun Level(level: String, isLocked: Boolean, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(25.dp))
    Button(
        onClick = { if (!isLocked) onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isLocked) Color.Gray else color,
            disabledContainerColor = Color.Gray
        ),
        border = BorderStroke(4.dp, Color.Black.copy(alpha = 0.8f)),
        shape = CircleShape,
        modifier = modifier.size(80.dp).shadow(if (isLocked) 0.dp else 10.dp, CircleShape),
        contentPadding = PaddingValues(0.dp),
        enabled = !isLocked
    ) {
        if (isLocked) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "Locked", tint = Color.White)
        } else {
            Text(text = level, style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
fun Menu(navController: NavController, modifier: Modifier = Modifier) {
    Surface(color = Color.White, shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp), modifier = modifier.fillMaxWidth().shadow(20.dp)) {
        Row(modifier = Modifier.padding(vertical = 15.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(painterResource(id = R.drawable.home), contentDescription = null, modifier = Modifier.size(30.dp), tint = Color.Unspecified)
            }
            IconButton(onClick = { navController.navigate("account") }) {
                Icon(painterResource(id = R.drawable.user), contentDescription = null, modifier = Modifier.size(30.dp), tint = Color.Unspecified)
            }
            IconButton(onClick = { navController.navigate("friends") }) {
                Icon(painterResource(id = R.drawable.freind), contentDescription = null, modifier = Modifier.size(30.dp), tint = Color.Unspecified)
            }
            IconButton(onClick = { }) {
                Icon(painterResource(id = R.drawable.setting), contentDescription = null, modifier = Modifier.size(30.dp), tint = Color.Unspecified)
            }
        }
    }
}
