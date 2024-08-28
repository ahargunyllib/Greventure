package com.seven_sheesh.greventure.presentation.ui.widget.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.PlusJakartaSans
import com.seven_sheesh.greventure.presentation.ui.design_system.Typography

@Composable
fun Input(
    label: String? = null,
    value: String,
    placeholder: String = "placeholder",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        if (label != null) {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    color = GreventureScheme.Gray.color,
                ),
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = GreventureScheme.Gray.color
                )
                .padding(vertical = 16.dp, horizontal = 16.dp),
            textStyle = TextStyle(
                fontFamily = PlusJakartaSans,
            ).merge(textStyle),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            decorationBox = { innerTextField ->
                Row {
                    if (leadingContent != null) {
                        leadingContent()
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = GreventureScheme.Gray.color,
                                style = TextStyle(
                                    fontFamily = PlusJakartaSans,
                                ).merge(textStyle),
                            )
                        } else {
                            innerTextField()
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    if (trailingContent != null) {
                        trailingContent()
                    }
                }
            }
        )
    }
}