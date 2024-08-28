package com.seven_sheesh.greventure.presentation.ui.widget.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.rounded.EnergySavingsLeaf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
@Preview
fun SnackBar(){
    LazyRow(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(GreventureScheme.PrimaryVariant3.color)) {
                Icon(imageVector = Icons.Rounded.EnergySavingsLeaf, contentDescription = "Leaf")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Lingkungan")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(GreventureScheme.PrimaryVariant3.color)) {
                Icon(imageVector = Icons.Default.Language, contentDescription = "Earth")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Budaya")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(GreventureScheme.PrimaryVariant3.color)) {
                Icon(imageVector = Icons.Default.People, contentDescription = "People")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Masyarakat")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(GreventureScheme.PrimaryVariant3.color)) {
                Text(text = "Komunitas")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        item {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(GreventureScheme.PrimaryVariant3.color)) {
                Text(text = "Official")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

private fun isHighLighted2(currentPage: Int, index: Int): Color {
    return if(currentPage == index){
        GreventureScheme.Primary.color
    } else {
        Color.Transparent
    }
}

private fun isCurrentPage2(currentPage: Int, index: Int): Boolean {
    return currentPage == index
}