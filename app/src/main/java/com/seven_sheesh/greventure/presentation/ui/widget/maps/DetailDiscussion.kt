package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seven_sheesh.greventure.domain.model.Thread
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
fun DiscussionSection(
    threads: List<Thread>,
    homeNavController: NavController,
){
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Diskusi",
            color = GreventureScheme.Black.color,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))
        threads.forEach{thread ->
            ChatMessage(message = thread, isComment = false, homeNavController = homeNavController)
        }
        if(threads.isEmpty()){
            Text(text = "Belum ada diskusi yang tersedia", color = GreventureScheme.Gray.color, fontSize = 14.sp)
        }
    }
}

