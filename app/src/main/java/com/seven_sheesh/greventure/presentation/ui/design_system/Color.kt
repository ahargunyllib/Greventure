package com.seven_sheesh.greventure.presentation.ui.design_system

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val lightColorScheme = lightColorScheme()
val darkColorScheme = darkColorScheme()

sealed class GreventureScheme(val color: Color){
    data object Primary: GreventureScheme(Color(0xFF4DAF76))
    data object PrimaryVariant1: GreventureScheme(Color(0xFFC3E4D1))
    data object PrimaryVariant2: GreventureScheme(Color(0xFF88C9A3))
    data object PrimaryVariant3: GreventureScheme(Color(0xFF33744E))
    data object PrimaryVariant4: GreventureScheme(Color(0xFF193A27))

    data object Secondary: GreventureScheme(Color(0xFF4980F1))
    data object SecondaryVariant1: GreventureScheme(Color(0xFFB7D4D4))
    data object SecondaryVariant2: GreventureScheme(Color(0xFF3055A0))
    data object SecondaryVariant3: GreventureScheme(Color(0xFF45BFBE))

    data object Success: GreventureScheme(Color(0xFF00C566))
    data object Error: GreventureScheme(Color(0xFFFF4747))
    data object Warning: GreventureScheme(Color(0xFFECB826))

    data object White: GreventureScheme(Color(0xFFFAFAFA))
    data object LineDark: GreventureScheme(Color(0xFF484848))
    data object SoftGray: GreventureScheme(Color(0xFFE3E7EC))
    data object Black: GreventureScheme(Color(0xFF111111))
    data object Gray: GreventureScheme(Color(0xFF6C6C6C))
}