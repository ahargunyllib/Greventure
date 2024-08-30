package com.seven_sheesh.greventure.presentation.view.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.widget.maps.ChatInput
import com.seven_sheesh.greventure.presentation.ui.widget.maps.ChatMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun DiscussionScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Diskusi") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    ChatMessage(isComment = false)
                    ChatMessage()
                    ChatMessage()
                    ChatMessage(isComment = false)
                }

                ChatInput()
            }
        }
    )
}
