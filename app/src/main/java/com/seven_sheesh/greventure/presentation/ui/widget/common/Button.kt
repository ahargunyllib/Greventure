package com.seven_sheesh.greventure.presentation.ui.widget.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.PlusJakartaSans

enum class ButtonSize {
    SMALL, MEDIUM, LARGE
}

enum class ButtonType {
    PRIMARY, SECONDARY, OUTLINED, TEXT
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.MEDIUM,
    type: ButtonType = ButtonType.PRIMARY,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    text: String,
    leadingContent: @Composable (() -> Unit)? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        border = when (type) {
            ButtonType.PRIMARY -> null
            ButtonType.SECONDARY -> null
            ButtonType.OUTLINED -> BorderStroke(1.dp, GreventureScheme.Primary.color)
            ButtonType.TEXT -> null
        },
        contentPadding = when (type) {
            ButtonType.PRIMARY -> PaddingValues(vertical = 16.dp, horizontal = 24.dp)
            ButtonType.SECONDARY -> PaddingValues(vertical = 16.dp, horizontal = 24.dp)
            ButtonType.OUTLINED -> PaddingValues(vertical = 16.dp, horizontal = 24.dp)
            ButtonType.TEXT -> PaddingValues(0.dp)
        },
        shape = RoundedCornerShape(
            when (size) {
                ButtonSize.SMALL -> 16.dp
                ButtonSize.MEDIUM -> 20.dp
                ButtonSize.LARGE -> 24.dp
            }
        ),
        colors = ButtonColors(
            containerColor = when (type) {
                ButtonType.PRIMARY -> GreventureScheme.Primary.color
                ButtonType.SECONDARY -> GreventureScheme.SoftGray.color
                ButtonType.OUTLINED -> Color.Transparent
                ButtonType.TEXT -> Color.Transparent
            },
            contentColor = when (type) {
                ButtonType.PRIMARY -> GreventureScheme.White.color
                ButtonType.SECONDARY -> GreventureScheme.Gray.color
                ButtonType.OUTLINED -> GreventureScheme.Primary.color
                ButtonType.TEXT -> GreventureScheme.Primary.color
            },
            disabledContentColor = when (type) {
                ButtonType.PRIMARY -> GreventureScheme.White.color.copy(alpha = 0.38f)
                ButtonType.SECONDARY -> GreventureScheme.Gray.color.copy(alpha = 0.38f)
                ButtonType.OUTLINED -> GreventureScheme.Primary.color.copy(alpha = 0.38f)
                ButtonType.TEXT -> GreventureScheme.Primary.color.copy(alpha = 0.38f)
            },
            disabledContainerColor = when (type) {
                ButtonType.PRIMARY -> GreventureScheme.Primary.color.copy(alpha = 0.38f)
                ButtonType.SECONDARY -> GreventureScheme.SoftGray.color.copy(alpha = 0.38f)
                ButtonType.OUTLINED -> GreventureScheme.SoftGray.color.copy(alpha = 0.38f)
                ButtonType.TEXT -> GreventureScheme.SoftGray.color.copy(alpha = 0.38f)
            }
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingContent != null) {
                leadingContent()
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                style = TextStyle(
                    fontFamily = PlusJakartaSans,
                    fontSize = 16.sp,
                    fontWeight = when (type) {
                        ButtonType.PRIMARY -> FontWeight.SemiBold
                        ButtonType.SECONDARY -> FontWeight.SemiBold
                        ButtonType.OUTLINED -> FontWeight.SemiBold
                        ButtonType.TEXT -> FontWeight.Bold
                    }
                )
            )
        }
    }
}