package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.domain.model.PlaceholderData
import com.seven_sheesh.greventure.domain.model.User
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
fun ChatInput(
    user: User = PlaceholderData.user1,
    isComment: Boolean = false,
    onSendClick: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(GreventureScheme.SoftGray.color, CircleShape)
                .clip(CircleShape)
        ){
            AsyncImage(model = user.profilePictureUrl, contentDescription = "profile", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .background(GreventureScheme.SoftGray.color, RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = {
            onSendClick(text.text)
            text = TextFieldValue("")
        },
            colors = ButtonDefaults.buttonColors(GreventureScheme.Primary.color)
        ) {
            Text(text = "Kirim", color = GreventureScheme.White.color, fontSize = 14.sp)
        }
    }
}