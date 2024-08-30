package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.EventColor
import com.seven_sheesh.greventure.domain.model.EventType
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
fun HeaderSection(currentBubble: State<Pair<String, Bubble?>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentBubble.value.second?.title ?: "",
                    color = GreventureScheme.Black.color,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if(currentBubble.value.second?.eventType != null){
                    Box(modifier = Modifier
                        .height(32.dp)
                        .width(92.dp)
                        .clip(RoundedCornerShape(50))
                        .background(if(currentBubble.value.second?.eventType != null) EventColor(
                            currentBubble.value.second?.eventType!!
                        ) else GreventureScheme.Success.color),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = currentBubble.value.second?.eventType.toString(), color = GreventureScheme.White.color, fontSize = 12.sp)
                    }
                } else {
                    Box(modifier = Modifier
                        .height(32.dp)
                        .width(92.dp)
                        .clip(RoundedCornerShape(50))
                        .background(GreventureScheme.Success.color),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Lokasi", color = GreventureScheme.White.color, fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = currentBubble.value.second?.description ?: "", color = GreventureScheme.Black.color)
        }
    }
}