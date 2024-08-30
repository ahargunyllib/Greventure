package com.seven_sheesh.greventure.presentation.ui.design_system

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.R

val PlusJakartaSans = FontFamily(
    Font(R.font.plus_jakarta_sans_extra_light, FontWeight.ExtraLight),
    Font(R.font.plus_jakarta_sans_light, FontWeight.Light),
    Font(R.font.plus_jakarta_sans_regular, FontWeight.Normal),
    Font(R.font.plus_jakarta_sans_medium, FontWeight.Medium),
    Font(R.font.plus_jakarta_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold),
    Font(R.font.plus_jakarta_sans_extra_bold, FontWeight.ExtraBold),

    // Italic variants
    Font(R.font.plus_jakarta_sans_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    displayMedium = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    displaySmall = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    bodyMedium = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    bodySmall = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    headlineLarge = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    headlineSmall = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    labelLarge = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    labelSmall = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    titleSmall = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    labelMedium = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    titleMedium = TextStyle(
        fontFamily = PlusJakartaSans,
    ),
    headlineMedium = TextStyle(
        fontFamily = PlusJakartaSans,
    ),

    )