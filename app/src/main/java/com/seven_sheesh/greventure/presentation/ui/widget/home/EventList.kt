package com.seven_sheesh.greventure.presentation.ui.widget.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj

@Composable
@Preview
fun EventList(
    homeNavController: NavController = rememberNavController(),
){
    val dummyArray = listOf(0, 1, 2)
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Event", fontSize = 18.sp, color = GreventureScheme.Black.color, fontWeight = FontWeight.SemiBold)
        Text(text = "Lainnya", color = GreventureScheme.Primary.color, fontSize = 16.sp, modifier = Modifier.clickable {
            homeNavController.navigate(HomeNavObj.EventScreen.route)
        }, textDecoration = TextDecoration.Underline)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Column(modifier = Modifier
        .fillMaxWidth()) {
        dummyArray.forEach {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {  },
                colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Lorem ipsum dolor", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = GreventureScheme.White.color)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "Star", tint = GreventureScheme.White.color)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "4.9", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = GreventureScheme.White.color)
                        }
                    }

                    Text(text = "Lorem ipsum dolor sit amet", fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp), color = GreventureScheme.White.color)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}