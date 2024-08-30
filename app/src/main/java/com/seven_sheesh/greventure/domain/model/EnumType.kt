package com.seven_sheesh.greventure.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.rounded.EnergySavingsLeaf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

enum class BubbleType {
    Event, Place
}

enum class SocialMedia {
    Instagram, Tiktok, Facebook
}

fun SocialMediaVector(socialMedia: SocialMedia): ImageVector{
    when(socialMedia){
        SocialMedia.Instagram -> return Icons.Default.CameraAlt
        SocialMedia.Tiktok -> return Icons.Default.MusicNote
        SocialMedia.Facebook -> return Icons.Default.Facebook
    }
}

enum class EventType {
    Budaya, Lingkungan, Masyarakat, Komunitas, Official
}

fun EventColor(type: EventType): Color{
    when(type){
        EventType.Budaya -> return GreventureScheme.SecondaryVariant3.color
        EventType.Lingkungan -> return GreventureScheme.PrimaryVariant3.color
        EventType.Masyarakat -> return GreventureScheme.SecondaryVariant2.color
        EventType.Komunitas -> return GreventureScheme.PrimaryVariant2.color
        EventType.Official -> return GreventureScheme.Primary.color
    }
}

fun EventVector(type: EventType): ImageVector {
    when(type){
        EventType.Budaya -> return Icons.Default.Language
        EventType.Lingkungan -> return Icons.Rounded.EnergySavingsLeaf
        EventType.Masyarakat -> return Icons.Default.People
        EventType.Komunitas -> return Icons.Default.Groups
        EventType.Official -> return Icons.Default.Verified
    }
}

fun EventBubble(type: EventType): Int {
    when(type) {
        EventType.Budaya -> return R.drawable.bubble_2
        EventType.Lingkungan -> return R.drawable.bubble_1
        EventType.Masyarakat -> return R.drawable.bubble_3
        EventType.Komunitas -> return R.drawable.bubble_3
        EventType.Official -> return R.drawable.bubble_1
    }
}

enum class ReportType {
    NOT_EXIST, FRAUD, VIOLENCE, DUPLICATE, LOCATION_INCORRECT, EVENT_FINISHED
}