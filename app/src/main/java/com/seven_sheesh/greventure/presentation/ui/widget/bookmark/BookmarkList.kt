package com.seven_sheesh.greventure.presentation.ui.widget.bookmark

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.domain.model.Bookmark
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.PlusJakartaSans
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj

@Composable
fun BookmarkList(bookmarks: List<Bubble>, homeNavController: NavController){
    val dummyArray = listOf(0, 1, 2, 3)
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        bookmarks.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(GreventureScheme.White.color),
                border = BorderStroke(2.dp, GreventureScheme.SoftGray.color),
                onClick = {
                    homeNavController.navigate(HomeNavObj.DetailScreen.createRoute(it.id))
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = it.photoUrl,
                            contentDescription = "Bubble Photo",
                            contentScale = ContentScale.Crop,
                        )
                        Canvas(modifier = Modifier.fillMaxSize().alpha(0.8f)) {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        GreventureScheme.PrimaryVariant1.color,
                                        GreventureScheme.PrimaryVariant4.color
                                    ),
                                    startY = 0f,
                                    endY = size.height
                                ),
                                size = Size(size.width, size.height),
                                topLeft = Offset.Zero
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Column {
                            Text(
                                text = it.title,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = GreventureScheme.White.color,
                                style = TextStyle(
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    tint = GreventureScheme.White.color,
                                    contentDescription = "Rating"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = it.averageRating.toString(),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(top = 4.dp),
                                    color = GreventureScheme.White.color,
                                    style = TextStyle(
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                            }
                        }
                        Text(
                            text = it.description,
                            fontSize = 14.sp,
                            color = GreventureScheme.White.color,
                            style = TextStyle(
                                fontFamily = PlusJakartaSans
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}