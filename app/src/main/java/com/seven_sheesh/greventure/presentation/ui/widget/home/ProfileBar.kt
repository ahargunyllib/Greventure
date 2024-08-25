package com.seven_sheesh.greventure.presentation.ui.widget.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj

@Composable
@Preview
fun ProfileBar(
    homeNavController: NavController = rememberNavController(),
){
    Spacer(modifier = Modifier.height(16.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Card(modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(50)
            ) {}
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Lorem ipsum dolor", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Color.Black)
                Text(text = "Lorem ipsum", fontSize = 14.sp, modifier = Modifier.padding(top = 2.dp), color = Color.Black)
                Text(text = "Ganti Kota", color = Color.Blue, modifier = Modifier.padding(top = 2.dp), textDecoration = TextDecoration.Underline)
            }
        }

        Card(modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(Color.White),
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            Box(modifier = Modifier.fillMaxSize().clickable {
               homeNavController.navigate(HomeNavObj.NotificationScreen.route)
            }, contentAlignment = Alignment.Center){
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notification")
            }
        }
    }
    HorizontalDivider()
}