package com.seven_sheesh.greventure.presentation.ui.widget.maps

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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.utils.formatDateTime

@Composable
fun DateLocationCard(homeNavController: NavController, currentBubble: State<Pair<String, Bubble?>>) {
    var date = remember { mutableStateOf("No date") }

    LaunchedEffect(currentBubble.value) {
        val startTime = currentBubble.value.second?.startTime
        date.value = startTime?.let { formatDateTime(it) } ?: "No date"
    }

    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(42.dp),
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
            border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        homeNavController.navigate(HomeNavObj.NotificationScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location", tint = GreventureScheme.White.color)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = currentBubble.value.second?.latitude.toString()+ "° / " + currentBubble.value.second?.longitude.toString() + "°", color = GreventureScheme.Black.color)
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(42.dp),
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
            border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        homeNavController.navigate(HomeNavObj.NotificationScreen.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Location", tint = GreventureScheme.White.color)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = date.value, color = GreventureScheme.Black.color)
        }
    }
}