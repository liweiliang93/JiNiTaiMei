@file:Suppress("NAME_SHADOWING")

package com.weiliang.jinitaimei.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 定义暗色界面主题颜色配置
private val DarkColorScheme = darkColorScheme(
    primary = Blue200,
    onPrimary = Color.White,
    primaryContainer = Blue200,
    secondary = Gray900,
    tertiary = PurpleGrey500,
    onTertiary = Pink80,
)

// 定义亮色界面主题颜色配置
private val LightColorScheme = lightColorScheme(
    primary = Blue200,
    onPrimary = Color.White,
    primaryContainer = Blue200,
    secondary = Gray900,
    tertiary = PurpleGrey500,
    onTertiary = Pink80,
)


@Composable
fun JiNiTaiMeiTheme(
    theme: Int = 0,
    content: @Composable() () -> Unit
) {

    // 明暗主题切换
    val (colorScheme,theme) = when(theme % 2){
            0 -> LightColorScheme to DarkChess
            1 ->  DarkColorScheme to LightChess
        else -> error("")
    }

    // 重写MaterialTheme主题配色和颜色形状配置
    MaterialTheme(
        colorScheme = LightColorScheme,
        shapes = JiNiTaiMeiShapes,
        typography = Typography,
        content = content
    )
}