package com.example.students_camp_practise.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Yellow,
    primaryVariant = Blue,
    secondary = Grey,
    onPrimary = LightGrey,
    secondaryVariant = DarkBlue
)

private val LightColorPalette = lightColors(
    primary = Yellow,
    primaryVariant = Blue,
    secondary = LightGrey,
    onPrimary = LightGrey,
    secondaryVariant = DarkBlue

)

@Composable
fun Students_camp_practiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}