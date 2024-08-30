package com.seven_sheesh.greventure.presentation.view.profile

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.ButtonSize
import com.seven_sheesh.greventure.presentation.ui.widget.common.CustomButton
import com.seven_sheesh.greventure.presentation.ui.widget.common.SearchBarComponent
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PopupCard
import com.seven_sheesh.greventure.presentation.ui.widget.profile.ProfileItem
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun ProfileScreen(
    homeNavController: NavController = rememberNavController(),
    parentNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val user = profileViewModel.user.collectAsState(initial = Pair("", null)).value.second
    val message = profileViewModel.message.collectAsState(initial = "").value
    val context = LocalContext.current

    navbarViewModel.setPageState(3)

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }

            if (message.contains("Successfully")) {
                delay(1000)
                parentNavController.navigate(ParentNavObj.Auth.route)
            }
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                CustomButton(
                    onClick = {
                        profileViewModel.onSignOut()
                    },
                    text = if (user?.id == null) "Masuk" else "Keluar",
                    size = ButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GreventureScheme.White.color)
                .safeDrawingPadding(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .background(GreventureScheme.Primary.color)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(60.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier.size(100.dp),
                                shape = RoundedCornerShape(50),
                                colors = CardDefaults.cardColors(GreventureScheme.White.color),
                                border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                            ) {
                                if (user?.profilePictureUrl != null) {
                                    AsyncImage(
                                        model = user.profilePictureUrl,
                                        contentDescription = "Profile Picture",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = user?.name ?: "Login dulu yuk",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreventureScheme.White.color
                        )
                        Text(
                            text = user?.email ?: "Login dulu yuk",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = GreventureScheme.SoftGray.color
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        ProfileItem(
                            icon = Icons.Default.Person,
                            title = "Edit Profil"
                        ) {
                            if(user != null){
                                homeNavController.navigate(HomeNavObj.EditProfileScreen.route)
                            } else {
                                Toast.makeText(context, "Login dulu yuk", Toast.LENGTH_SHORT).show()
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider()
                        ProfileItem(
                            icon = Icons.Default.Notifications,
                            title = "Notifikasi"
                        ) {
                            homeNavController.navigate(HomeNavObj.NotificationScreen.route)
                        }
                        ProfileItem(
                            icon = Icons.Default.History,
                            title = "Bubble Buatanmu"
                        ) {
                            if(user != null){
                                homeNavController.navigate(HomeNavObj.YourBubbleScreen.route)
                            } else {
                                Toast.makeText(context, "Login dulu yuk", Toast.LENGTH_SHORT).show()
                            }

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider()
                        ProfileItem(
                            icon = Icons.Default.Help,
                            title = "Bantuan"
                        ) {}
                    }
                }
            }
        }
    }
}