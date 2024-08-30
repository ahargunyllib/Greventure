package com.seven_sheesh.greventure.presentation.view.maps

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.domain.model.Comment
import com.seven_sheesh.greventure.domain.model.Thread
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.maps.ChatInput
import com.seven_sheesh.greventure.presentation.ui.widget.maps.ChatMessage
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.ProfileViewModel
import com.seven_sheesh.greventure.ui.viewmodel.CommentViewModel
import com.seven_sheesh.greventure.ui.viewmodel.ThreadViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun DiscussionScreen(
    homeNavController: NavController = rememberNavController(),
    threadViewModel: ThreadViewModel = hiltViewModel(),
    commentViewModel: CommentViewModel = hiltViewModel(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    threadId: String = ""
) {
    val user = navbarViewModel.user.collectAsState().value.second

    LaunchedEffect(Unit) {
        navbarViewModel.setPageState(1)
        threadViewModel.loadThreadById(threadId)
        commentViewModel.loadCommentByThreadId(threadId)
    }

    val thread = threadViewModel.threadUserState.collectAsState().value
    val comments = commentViewModel.commentUserListState.collectAsState().value
    val message = commentViewModel.messageState.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(message) {
        if(message != "") {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        if(message.contains("Comment successfully inserted/updated")){
            homeNavController.navigate(HomeNavObj.DiscussionScreen.createRoute(threadId))
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Arrow Back",
                            modifier = Modifier.clickable {
                                homeNavController.navigate(HomeNavObj.MapsScreen.route)
                            },
                            tint = GreventureScheme.Black.color
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Kembali",
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = GreventureScheme.Black.color
                            )
                        }
                    }
                }
            }
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
                            val comment = Comment(
                                id = UUID.randomUUID().toString(),
                                threadId = threadId,
                                userId = user?.id ?: "",
                                content = it,
                                createdAt =  OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX"))
                            )
                            commentViewModel.upsertComment(comment)
                        }, user = user)
                        Spacer(modifier = Modifier.height(96.dp))
                    }
                }
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item { 
                    Spacer(modifier = Modifier.height(82.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    ) {
                        thread?.let { it.keys.firstOrNull()?.let { it1 -> it.values.firstOrNull()
                            ?.let { it2 -> ChatMessage(message = it1, user = it2, isComment = false) } } }
                        comments.forEach { map ->
                            map.keys.firstOrNull()?.let { it1 -> map.values.firstOrNull()
                                ?.let { it2 -> ChatMessage(comment = it1, user = it2, isComment = true, homeNavController = homeNavController) } }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(160.dp))
                }
            }
        }
    )
}
