package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
fun ChatMessage(message: String, isUser: Boolean, isReply: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .padding(vertical = 8.dp)
            .padding(start = if (isReply) 22.dp else 0.dp),
        verticalAlignment = Alignment.Top
    ) {
        if (isReply) {
            VerticalDivider()
            Spacer(modifier = Modifier.padding(16.dp))
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(GreventureScheme.SoftGray.color, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = GreventureScheme.Black.color,
                modifier = Modifier
                    .background(
                        if (isUser) GreventureScheme.SecondaryVariant1.color else GreventureScheme.SoftGray.color,
                        CircleShape
                    )
                    .padding(8.dp)
            )
        }
    }
}