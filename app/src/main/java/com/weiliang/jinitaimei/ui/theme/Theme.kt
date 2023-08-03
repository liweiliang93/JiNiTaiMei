package com.weiliang.jinitaimei.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Blue200,
    onPrimary = Color.White,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Blue200,
    onPrimary = Color.White,
    primaryContainer = Blue200,
    secondary = Gray900,
    tertiary = PurpleGrey500,
    onTertiary = Pink80,
)

private val WoodColorPalette = lightColorScheme(
    primary = Wood500,
    primaryContainer = Purple700,
    secondary = Wood700,
    background = Color.LightGray

)

@Composable
fun JiNiTaiMeiTheme(
    theme: Int = 0,
    content: @Composable() () -> Unit
) {

    val (colorScheme,theme) = when(theme % 3){
            0 -> LightColorScheme to DarkChess
            1 ->  DarkColorScheme to LightChess
            2 -> WoodColorPalette to WoodChess
        else -> error("")
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        shapes = JiNiTaiMeiShapes,
        typography = Typography,
        content = content
    )
}