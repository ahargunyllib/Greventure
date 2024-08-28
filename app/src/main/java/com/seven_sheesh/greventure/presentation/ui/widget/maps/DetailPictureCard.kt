package com.seven_sheesh.greventure.presentation.ui.widget.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.domain.model.BubblePhoto

@Composable
fun PictureCard(currentBubblePhoto: State<Pair<String, List<BubblePhoto>>>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        shape = RectangleShape
    ) {
        AsyncImage(model = currentBubblePhoto.value.second.firstOrNull()?.url, contentDescription = "photo", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
    }
}