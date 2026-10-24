package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = MaroonPrimary,
    onPrimary = Color.White,
    primaryContainer = MaroonPrimaryDark,
    onPrimaryContainer = GoldLight,
    secondary = GoldAccent,
    onSecondary = MaroonPrimaryDark,
    secondaryContainer = GoldContainer,
    onSecondaryContainer = MaroonPrimaryDark,
    tertiary = DeepTerracotta,
    onTertiary = Color.White,
    background = CreamBackground,
    onBackground = TextCharcoal,
    surface = CreamSurface,
    onSurface = TextCharcoal,
    surfaceVariant = CreamSurfaceVariant,
    onSurfaceVariant = TextMuted,
    outline = BorderGold,
    outlineVariant = CardBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = GoldAccent,
    onPrimary = MaroonPrimaryDark,
    primaryContainer = MaroonLight,
    onPrimaryContainer = Color.White,
    secondary = DarkGold,
    onSecondary = DarkMaroonBg,
    secondaryContainer = DarkMaroonVariant,
    onSecondaryContainer = DarkGold,
    tertiary = DeepTerracotta,
    onTertiary = Color.White,
    background = DarkMaroonBg,
    onBackground = DarkTextPrimary,
    surface = DarkMaroonSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkMaroonVariant,
    onSurfaceVariant = DarkTextSecondary,
    outline = GoldMuted,
    outlineVariant = MaroonLight
)

@Composable
fun NrutyashalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Preserve custom Indian classical brand palette (do not override with generic wallpaper dynamic colors)
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
