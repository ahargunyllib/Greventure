package com.seven_sheesh.greventure.presentation.view.home

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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.domain.model.EventColor
import com.seven_sheesh.greventure.domain.model.dummyNews
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PictureCard
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
@Preview
fun NewsDetailScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    newsId: String
){
    val news = dummyNews.find { it.id == newsId }!!

    navbarViewModel.setPageState(0)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .safeDrawingPadding(),
        contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(GreventureScheme.Primary.color),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Arrow Back", modifier = Modifier.clickable {
                            homeNavController.navigate(HomeNavObj.HomeScreen.route)
                        }, tint = GreventureScheme.White.color)
                        Column {
                            Text(text = "Berita", fontWeight = FontWeight.SemiBold, fontSize = 22.sp, color = GreventureScheme.White.color)
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                ) {
                    AsyncImage(model = news.photoUrl, contentDescription = "photo",
                        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = news.title,
                        color = GreventureScheme.Black.color,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "${news.author} - ${news.minutesToRead} menit dibaca", color = GreventureScheme.Black.color)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = news.createdAt, color = GreventureScheme.Black.color)
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ){
                    Text(text = news.description,
                        textAlign = TextAlign.Justify,
                        color = GreventureScheme.Black.color
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ){
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(24.dp))

//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {  },
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ){
//                        Text(text = "Berita Lainnya", fontSize = 18.sp, color = GreventureScheme.Black.color, fontWeight = FontWeight.SemiBold)
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Column(modifier = Modifier
//                        .fillMaxWidth()
//                    ) {
//                        dummyNews.forEach {
//                            Row(
//                                modifier = Modifier,
//                                verticalAlignment = Alignment.CenterVertically
//                            ){
//                                Card(modifier = Modifier.height(96.dp).width(120.dp),
//                                    shape = RoundedCornerShape(16.dp),
//                                    colors = CardDefaults.cardColors(GreventureScheme.PrimaryVariant1.color),
//                                ) {}
//                                Spacer(modifier = Modifier.width(12.dp))
//                                Column {
//                                    Text(text = "2 Maret 2024", fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp), color = GreventureScheme.Black.color)
//                                    Text(text = "Lorem Ipsum Dolor sit Amet", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = GreventureScheme.Black.color)
//                                    Text(text = "Lorem ipsum - 2 menit dibaca", fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp), color = GreventureScheme.Black.color)
//                                }
//                            }
//                            Spacer(modifier = Modifier.height(12.dp))
//                            HorizontalDivider()
//                            Spacer(modifier = Modifier.height(12.dp))
//                        }
//                    }
                }
            }

            item { Spacer(modifier = Modifier.height(140.dp)) }
        }
    }
}