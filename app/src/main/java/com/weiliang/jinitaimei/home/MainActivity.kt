@file:OptIn(ExperimentalMaterial3Api::class)
package com.weiliang.jinitaimei.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme


class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiNiTaiMeiTheme {
                ScreenControl()
            }
        }
    }
}



@Composable
fun GameScreen() {

}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    JiNiTaiMeiTheme(0) {
        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    JiNiTaiMeiTheme(1) {
        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WoodPreview() {
    JiNiTaiMeiTheme(2) {

        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}
