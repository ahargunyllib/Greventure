package com.seven_sheesh.greventure.presentation.view.profile

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.SearchBarComponent
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PopupCard
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.ProfileViewModel
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun YourBubbleScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(3)
    val user = profileViewModel.user.collectAsState(initial = Pair("", null)).value.second
    val bubbleViewModel = hiltViewModel<BubbleViewModel>()
    bubbleViewModel.loadBubbleByUserId(user?.id ?: "")
    val bubbleList = bubbleViewModel.bubbleListState.collectAsState()

    Scaffold(
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
        bottomBar = { Column(modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)){} },
        floatingActionButton = {
            Box(modifier = Modifier.size(72.dp).padding(0.dp).clip(CircleShape).background(GreventureScheme.Primary.color), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add", tint = GreventureScheme.White.color, modifier = Modifier.size(48.dp).clickable {
                    homeNavController.navigate(HomeNavObj.CreateBubble1.route)
                })
            }
        }
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding(),
            contentAlignment = Alignment.Center) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ){
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
                                homeNavController.navigate(HomeNavObj.ProfileScreen.route)
                            }, tint = GreventureScheme.White.color)
                            Column {
                                Text(text = "Bubble Buatanmu", fontWeight = FontWeight.SemiBold, fontSize = 22.sp, color = GreventureScheme.White.color)
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        }
                    }
                }

                items(bubbleList.value.second) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = it.title,
                                color = GreventureScheme.Black.color,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Box(
                                modifier = Modifier
                                    .height(32.dp)
                                    .width(92.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(GreventureScheme.PrimaryVariant3.color),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = it.eventType.toString(), color = GreventureScheme.White.color, fontSize = 12.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Edit", color = GreventureScheme.Black.color, fontSize = 18.sp, modifier = Modifier.clickable {

                        }, textDecoration = TextDecoration.Underline)
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}