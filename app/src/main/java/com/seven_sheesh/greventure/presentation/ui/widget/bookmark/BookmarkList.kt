package com.seven_sheesh.greventure.presentation.ui.widget.bookmark

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj

@Composable
@Preview
fun BookmarkList(){
    val dummyArray = listOf(0, 1, 2, 3)
    Spacer(modifier = Modifier.height(32.dp))
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        dummyArray.forEach {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color.White),
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Lorem Ipsum",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "Lorem ipsum Dolor Sit Amet",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 4.dp),
                                color = Color.Black
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.size(48.dp),
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(Color.White),
                        border = BorderStroke(2.dp, Color.LightGray)
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clickable {

                            }, contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notification"
                            )
                        }
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color.Blue)) {
                        Text(text = "Kunjungi", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}