package com.seven_sheesh.greventure.presentation.view.maps

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.domain.model.PlaceholderData
import com.seven_sheesh.greventure.domain.model.Thread
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.maps.ChatInput
import com.seven_sheesh.greventure.presentation.ui.widget.maps.SocialMediaCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.DiscussionSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.FAQSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.HeaderSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.DateLocationCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PictureCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.TopBar
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.ProfileViewModel
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel
import com.seven_sheesh.greventure.ui.viewmodel.CommentViewModel
import com.seven_sheesh.greventure.ui.viewmodel.ThreadViewModel
import java.time.ZonedDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel(),
    threadViewModel: ThreadViewModel = hiltViewModel(),
    commentViewModel: CommentViewModel = hiltViewModel(),
    bubbleViewModel: BubbleViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    bubbleId: String = ""
) {
    LaunchedEffect(Unit) {
        navbarViewModel.setPageState(1)
        bubbleViewModel.loadBubbleById(bubbleId)
        bubbleViewModel.loadBubblePhotoByBubbleId(bubbleId)
        bubbleViewModel.loadBubbleSocialMediaByBubbleId(bubbleId)
    }

    val currentBubbleState = bubbleViewModel.bubbleState.collectAsState()
    val currentBubblePhotoState = bubbleViewModel.bubblePhotoListState.collectAsState()
    val currentBubbleSocialMediaState = bubbleViewModel.bubbleSocialMediaListState.collectAsState()
    val message = threadViewModel.messageState.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        if(message.contains("successfully")){
            homeNavController.navigate(HomeNavObj.DetailScreen.createRoute(bubbleId))
        }
    }

    val bubbleIdValue by remember { derivedStateOf { currentBubbleState.value.second?.id ?: "" } }
    LaunchedEffect(bubbleIdValue) {
        threadViewModel.loadThreadByBubbleId(bubbleIdValue)
    }
    val threadsState = threadViewModel.threadListState.collectAsState()
    val user = navbarViewModel.user.collectAsState().value.second

    Scaffold(
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
        topBar = {
            TopBar(homeNavController, bubbleId, user?.id ?: "")
        },
        bottomBar = {
            if(user != null){
                Column(modifier = Modifier.animateContentSize(animationSpec = tween(durationMillis = 1500))) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(GreventureScheme.White.color)
                    ) {
                        HorizontalDivider()
                        ChatInput(onSendClick = {
                            val thread = Thread(
                                id = UUID.randomUUID().toString(),
                                bubbleId = bubbleId,
                                userId = user?.id ?: "",
                                content = it,
                                createdAt =  java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX"))
                            )
                            threadViewModel.upsertThread(thread)
                        }, user = user)
                        Spacer(modifier = Modifier.height(96.dp))
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .safeDrawingPadding(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item { PictureCard(currentBubblePhotoState, currentBubbleState) }
                item { HeaderSection(currentBubbleState) }
                item { DateLocationCard(homeNavController, currentBubbleState) }
                item { SocialMediaCard(homeNavController, currentBubbleSocialMediaState) }
                if (currentBubbleState.value.second?.eventType != null) {
                    item { FAQSection() }
                } else {
                    item {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Spacer(modifier = Modifier.height(24.dp))
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
                item { DiscussionSection(threads = threadsState.value.second) }
                item { Spacer(modifier = Modifier.height(140.dp)) }
            }
        }
    }
}










