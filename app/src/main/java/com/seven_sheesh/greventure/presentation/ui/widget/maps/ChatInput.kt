package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
@Preview
fun ChatInput(
    isComment: Boolean = false
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
                .size(54.dp)
                .background(GreventureScheme.SoftGray.color, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row(modifier = Modifier
            .background(GreventureScheme.SoftGray.color, RoundedCornerShape(24.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f)
            )

            Button(
                onClick = { },
                modifier = Modifier.padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(GreventureScheme.Secondary.color)
            ) {
                Text(text = "Kirim")
            }
        }
    }
}