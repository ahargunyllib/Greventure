package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
fun DiscussionSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Diskusi",
            color = GreventureScheme.Black.color,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))
        ChatMessage(message = "Lorem ipsum dolor sit amet,", isUser = false, isReply = false)
        ChatMessage(message = "Reply to above message,", isUser = false, isReply = true)
        ChatMessage(message = "Another message from the user,", isUser = true, isReply = false)
        ChatMessage(message = "Another reply,", isUser = false, isReply = true)
        ChatInput()
    }
}