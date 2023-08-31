package com.weiliang.jinitaimei.home

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ComponentRegistry
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme


//FirstPage:游戏准备界面,
@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by rememberSaveable { mutableStateOf(0) }
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components(fun ComponentRegistry.Builder.() {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }).build()
        Spacer(modifier = modifier.padding(vertical = 20.dp))

        Row(
            Modifier.background(MaterialTheme.colorScheme.onTertiary)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = R.drawable.kun1
                ),
                modifier = modifier
                    .size(60.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Column {
                Text(
                    text = "Welcome to the Huarong Road game!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
                Text(
                    text = "Tap kunkun's eyes until you can start the game",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {count++}) {
                Text(text = "Click")
            }
            Spacer(modifier = modifier.padding(horizontal = 16.dp))
            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = rememberImagePainter(
                    data = R.drawable.cxk2,
                    imageLoader = imageLoader,
                    builder = {
                        placeholder(R.drawable.cxk1)//占位图
                        crossfade(true)//淡出效果

                    }),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = modifier.padding(horizontal = 16.dp))
            Button(onClick = { count++ }) {
                Text(text = "charge")
            }
        }

        Row {
            Button(
                modifier = Modifier.padding(vertical = 26.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    navController.navigate("second_page")
                },
                enabled = count >= 10
            ) {
                Text(
                    text = "StartGame"
                )
            }
        }

        TextHint(modifier = modifier, text = "Choose the four ikuns from them")
        Show()
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    JiNiTaiMeiTheme {
        OnboardingScreen(navController = rememberNavController())
    }
}