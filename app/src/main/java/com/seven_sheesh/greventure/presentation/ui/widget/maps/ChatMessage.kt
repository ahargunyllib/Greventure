package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.domain.model.Comment
import com.seven_sheesh.greventure.domain.model.PlaceholderData
import com.seven_sheesh.greventure.domain.model.Thread
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj

@Composable
@Preview
fun ChatMessage(
    message: Thread = PlaceholderData.thread1,
    comment: Comment = PlaceholderData.comment1,
    userID: String = "",
    isComment: Boolean = true,
    homeNavController: NavController = rememberNavController()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        val textHeight = remember { mutableStateOf(0) }

        if (isComment) {
            Column(
                modifier = Modifier
                    .width(48.dp)
                    .height(with(LocalDensity.current) { textHeight.value.toDp() }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VerticalDivider(color = GreventureScheme.SoftGray.color, thickness = 3.dp)
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textHeight.value = coordinates.size.height
                }
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(GreventureScheme.SoftGray.color, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "User", color = GreventureScheme.Black.color, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if(isComment) comment.createdAt.toString().substring(0,10) else message.createdAt.toString().substring(0,10) , color = GreventureScheme.Gray.color, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if(isComment) comment.content else message.content,
                style = MaterialTheme.typography.bodyLarge,
                color = GreventureScheme.Black.color,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "2 Suka", color = GreventureScheme.Gray.color, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                if(!isComment){
                    Text(text = "Balas", color = GreventureScheme.Gray.color, textDecoration = TextDecoration.Underline, fontSize = 12.sp, modifier = Modifier.clickable {
                        homeNavController.navigate(HomeNavObj.DiscussionScreen.createRoute(message.id))
                    })
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}