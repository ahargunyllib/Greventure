package com.seven_sheesh.greventure.presentation.view.profile

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.seven_sheesh.greventure.presentation.ui.widget.profile.ProfileItem
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun ProfileScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(3)

    Scaffold(
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                    colors = CardDefaults.cardColors(GreventureScheme.Secondary.color),
                    shape = RoundedCornerShape(50.dp)
                    ) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .clickable { }, contentAlignment = Alignment.Center){
                        Text(text = "Keluar", color = GreventureScheme.White.color, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(GreventureScheme.White.color)
            .safeDrawingPadding(),
            contentAlignment = Alignment.Center){
            LazyColumn(modifier = Modifier
                .fillMaxSize())
            {
                
                item {
                    Box(modifier = Modifier.fillMaxWidth().height(220.dp).background(GreventureScheme.Primary.color)){

                    }
                }

//                item {
//                    Spacer(modifier = Modifier.height(60.dp))
//                    Row(modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Card(
//                            modifier = Modifier.size(100.dp),
//                            shape = RoundedCornerShape(50),
//                            colors = CardDefaults.cardColors(GreventureScheme.White.color),
//                            border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
//                        ) {
//
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                }

                item {
                    ProfileItem(
                        icon = Icons.Default.Person,
                        title = "Edit Profil"
                    ){
                        homeNavController.navigate(HomeNavObj.EditProfileScreen.route)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider()
                    ProfileItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifikasi"
                    ){
                        homeNavController.navigate(HomeNavObj.NotificationScreen.route)
                    }
                    ProfileItem(
                        icon = Icons.Default.History,
                        title = "Bubble Buatanmu"
                    ){
                        homeNavController.navigate(HomeNavObj.YourBubbleScreen.route)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider()
                    ProfileItem(
                        icon = Icons.Default.Help,
                        title = "Bantuan"
                    ){}
                }
            }
        }
    }
}