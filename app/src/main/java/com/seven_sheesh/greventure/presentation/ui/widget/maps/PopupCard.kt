package com.seven_sheesh.greventure.presentation.ui.widget.maps

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
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.PlaceholderData
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
@Preview
fun PopupCard(
    bubble: Bubble = PlaceholderData.bubble1,
    onClick: (String) -> Unit = {}
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(240.dp)
        .shadow(32.dp),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(GreventureScheme.White.color),
        border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
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
                    Text(text = bubble.title, color = GreventureScheme.Black.color, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Box(modifier = Modifier
                        .height(32.dp)
                        .width(92.dp)
                        .clip(RoundedCornerShape(50))
                        .background(GreventureScheme.PrimaryVariant3.color),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = bubble.eventType.toString(), color = GreventureScheme.White.color, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "location", tint = GreventureScheme.Black.color, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = bubble.latitude.toString() + " / " + bubble.longitude.toString(), fontSize = 16.sp, color = GreventureScheme.Black.color)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = bubble.description, fontSize = 16.sp, color = GreventureScheme.Black.color)
                Spacer(modifier = Modifier.height(16.dp))
                Card(modifier = Modifier
                    .width(140.dp)
                    .height(36.dp),
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(GreventureScheme.Primary.color)
                ) {
                    Box(modifier = Modifier.fillMaxSize().clickable {
                        onClick(bubble.id)
                    }, contentAlignment = Alignment.Center){
                        Text(text = "Selengkapnya", color = GreventureScheme.White.color, modifier = Modifier)
                    }
                }
            }
        }
    }
}