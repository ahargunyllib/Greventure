package com.seven_sheesh.greventure.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.rounded.EnergySavingsLeaf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

enum class BubbleType {
    Event, Place
}

enum class SocialMedia {
    Instagram, Tiktok, Facebook
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

enum class ReportType {
    NOT_EXIST, FRAUD, VIOLENCE, DUPLICATE, LOCATION_INCORRECT, EVENT_FINISHED
}