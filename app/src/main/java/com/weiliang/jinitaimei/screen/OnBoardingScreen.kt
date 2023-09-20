package com.weiliang.jinitaimei.screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.control.ImageLoad
import com.weiliang.jinitaimei.control.Show
import com.weiliang.jinitaimei.control.imageLoadForPaint
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme


//FirstPage:游戏准备界面,通过点击button到一定次数,即可激活进入游戏按钮,同时可选择下方你喜欢的角色名字
@SuppressLint("AutoboxingStateCreation")
@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val imagePainter = imageLoadForPaint(R.drawable.sz)
    Column(
        modifier = modifier.fillMaxSize()
            .paint(imagePainter,contentScale = ContentScale.Crop),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Spacer(modifier = modifier.padding(vertical = 20.dp))
    
        //进度条进度设置为可记忆的
        var progressLinear = remember {
            mutableStateOf(0.1f)
        }
        
        Row {
            LinearProgressIndicator(progress = progressLinear.value,color = MaterialTheme.colorScheme.inverseSurface)
        }
        Row(
            Modifier.background(MaterialTheme.colorScheme.onTertiary)
                   .alpha(0.5f)
        ) {
            //加载图片kun1
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

            //点击按钮提示语句
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
            Button(onClick = {if (progressLinear.value < 1.0f) progressLinear.value = progressLinear.value + 0.1f}) {
                Text(text = "Click")
            }
            Spacer(modifier = modifier.padding(horizontal = 16.dp))
            
            //加载GIF图片,使图片借助构建的imageLoader加载进来
            ImageLoad(R.drawable.cxk2,100.dp)
            
            Spacer(modifier = modifier.padding(horizontal = 16.dp))
            Button(onClick = {if (progressLinear.value < 1.0f) progressLinear.value = progressLinear.value + 0.1f}) {
                Text(text = "charge")
            }
        }
        
        //设置进入游戏按钮的点击事件为跳转进入游戏页面
        Row {
            Button(
                modifier = Modifier.padding(vertical = 26.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    navController.navigate("second_page")
                },
                enabled = progressLinear.value >= 1.0f
            ) {
                Text(
                    text = "StartGame"
                )
            }
        }
        
        //喜欢的角色名字的提示语句
        TextHint(modifier = modifier, text = "Choose the four ikuns from them")
        
        //进行可划去列表的展示
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


