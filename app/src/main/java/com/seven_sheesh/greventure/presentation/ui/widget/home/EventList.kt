package com.seven_sheesh.greventure.presentation.ui.widget.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.BubbleType
import com.seven_sheesh.greventure.domain.model.EventColor
import com.seven_sheesh.greventure.domain.model.EventType
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel

@Composable
@Preview
fun EventList(
    homeNavController: NavController = rememberNavController(),
    bubbleViewModel: BubbleViewModel = hiltViewModel(),
){
    val bubbleList = bubbleViewModel.bubbleListState.collectAsState()

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
        bubbleList.value.second.filter { it.type == BubbleType.Event }.take(3).forEach {
            EventCard(homeNavController, it)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EventCard(
    homeNavController: NavController,
    bubble: Bubble,
    bubbleViewModel: BubbleViewModel = hiltViewModel()
) {
    val bubblePhoto = bubbleViewModel.bubblePhotoListState.collectAsState().value.second.filter { it.bubbleId == bubble.id }.firstOrNull()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable {
                homeNavController.navigate(HomeNavObj.DetailScreen.createRoute(bubble.id))
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(EventColor(bubble.eventType ?: EventType.Masyarakat)) ,
        border = BorderStroke(2.dp, GreventureScheme.SoftGray.color),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = bubblePhoto?.url,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .blur(2.dp),
                contentScale = ContentScale.Crop
            )

            if (bubblePhoto?.url != "") {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GreventureScheme.LineDark.color.copy(alpha = 0.6f))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = bubble.title,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = GreventureScheme.White.color
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = GreventureScheme.White.color
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "4.9",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = GreventureScheme.White.color
                        )
                    }
                }

                Text(
                    text = bubble.description,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp),
                    color = GreventureScheme.White.color
                )
            }
        }
    }
}
