package com.seven_sheesh.greventure.presentation.ui.widget.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.rounded.EnergySavingsLeaf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seven_sheesh.greventure.domain.model.EventType
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
@Preview
fun SnackBar(
    onClick: (EventType) -> Unit ={}
) {
    var selectedItem by remember { mutableStateOf<String?>("Lingkungan") }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            val isSelected = selectedItem == "Lingkungan"
            Button(
                onClick = {
                    selectedItem = "Lingkungan"
                    onClick(EventType.Lingkungan)
                          },
                colors = ButtonDefaults.buttonColors(
                    if (isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent,
                ),
                border = BorderStroke(2.dp, if (!isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent),
            ) {
                Icon(
                    imageVector = Icons.Rounded.EnergySavingsLeaf,
                    contentDescription = "Leaf",
                    tint = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Lingkungan", color = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            val isSelected = selectedItem == "Budaya"
            Button(
                onClick = {
                    selectedItem = "Budaya"
                    onClick(EventType.Budaya)
                          },
                colors = ButtonDefaults.buttonColors(
                    if (isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent,
                ),
                border = BorderStroke(2.dp, if (!isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent),
            ) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = "Earth",
                    tint = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Budaya", color = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            val isSelected = selectedItem == "Masyarakat"
            Button(
                onClick = {
                    selectedItem = "Masyarakat"
                    onClick(EventType.Masyarakat)
                          },
                colors = ButtonDefaults.buttonColors(
                    if (isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent,
                ),
                border = BorderStroke(2.dp, if (!isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent),
            ) {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = "People",
                    tint = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Masyarakat", color = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            val isSelected = selectedItem == "Komunitas"
            Button(
                onClick = {
                    selectedItem = "Komunitas"
                    onClick(EventType.Komunitas)
                          },
                colors = ButtonDefaults.buttonColors(if (isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent,
                ),
                border = BorderStroke(2.dp, if (!isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent),
            ) {
                Icon(
                    imageVector = Icons.Default.Groups,
                    contentDescription = "Community",
                    tint = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Komunitas", color = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            val isSelected = selectedItem == "Official"
            Button(
                onClick = {
                    selectedItem = "Official"
                    onClick(EventType.Official)
                          },
                colors = ButtonDefaults.buttonColors(
                    if (isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent,
                ),
                border = BorderStroke(2.dp, if (!isSelected) GreventureScheme.PrimaryVariant3.color else Color.Transparent),
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = "Official",
                    tint = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Official", color = if (isSelected) Color.White else GreventureScheme.PrimaryVariant3.color)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

private fun isHighLighted2(currentPage: Int, index: Int): Color {
    return if (currentPage == index) {
        GreventureScheme.Primary.color
    } else {
        Color.Transparent
    }
}

private fun isCurrentPage2(currentPage: Int, index: Int): Boolean {
    return currentPage == index
}
