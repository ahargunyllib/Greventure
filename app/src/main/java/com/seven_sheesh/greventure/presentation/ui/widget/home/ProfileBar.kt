package com.seven_sheesh.greventure.presentation.ui.widget.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Notifications
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
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj

@Composable
@Preview
fun ProfileBar(
    homeNavController: NavController = rememberNavController(),
){
    Column(modifier = Modifier.fillMaxWidth().background(GreventureScheme.White.color)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(GreventureScheme.Primary.color),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(60.dp),
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(GreventureScheme.White.color),
                        border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                homeNavController.navigate(HomeNavObj.ProfileScreen.route)
                            }, contentAlignment = Alignment.Center
                        ) {
                            // profile picture
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Halo, Lorem Ipsum!",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = GreventureScheme.White.color
                        )
                        Text(
                            text = "Jelajahi kotamu bersama",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp),
                            color = GreventureScheme.White.color
                        )
                        Text(text = "Greventure",
                            color = GreventureScheme.White.color,
                            modifier = Modifier
                                .padding(top = 2.dp)
                        )
                    }
                }

                Card(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(GreventureScheme.White.color),
                    border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                ) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            homeNavController.navigate(HomeNavObj.NotificationScreen.route)
                        }, contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notification",
                            tint = GreventureScheme.Primary.color
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Malang, Jawa Timur", fontSize = 16.sp, color = GreventureScheme.Black.color)
            Text(text = "Ganti Kota", color = GreventureScheme.Primary.color, fontSize = 16.sp, modifier = Modifier.clickable {
                homeNavController.navigate(HomeNavObj.CityScreen.route)
            }, textDecoration = TextDecoration.Underline)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}