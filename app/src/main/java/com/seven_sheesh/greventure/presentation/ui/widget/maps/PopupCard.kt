package com.seven_sheesh.greventure.presentation.ui.widget.maps

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
@Preview
fun PopupCard(
    onClick: () -> Unit = {}
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(240.dp)
        .shadow(32.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.cardColors(GreventureScheme.White.color)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Lorem Ipsum dolor sit Amet", color = GreventureScheme.Black.color, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    Box(modifier = Modifier
                        .height(32.dp)
                        .width(72.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.DarkGray),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Category", color = GreventureScheme.White.color, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Lorem Ipsum dolor sit Amet", color = GreventureScheme.Black.color)
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = CardDefaults.cardColors(GreventureScheme.Secondary.color)
            ) {
                Box(modifier = Modifier.fillMaxSize().clickable {
                    onClick()
                }, contentAlignment = Alignment.Center){
                    Text(text = "Selengkapnya", color = GreventureScheme.White.color, modifier = Modifier)
                }
            }
        }
    }
}