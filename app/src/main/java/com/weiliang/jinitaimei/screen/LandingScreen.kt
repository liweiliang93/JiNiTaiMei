package com.weiliang.jinitaimei.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.control.imageLoadForPaint
import kotlinx.coroutines.delay


/*
    LandingScreen:游戏加载界面,通过点击坤坤和拖动坤坤来等待游戏加载完成
*/

//使用 SPLASHWAITTIME 来进行与sleep进行延时,SPLASHWAITTIME 表示延长的时间
private const val SPLASHWAITTIME: Long = 6000

@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imagePainter = imageLoadForPaint(R.drawable.cxkdlq)
    Box (
        modifier  = Modifier
            .paint(imagePainter, contentScale = ContentScale.Crop)
            .fillMaxSize()
            .alpha(0.8f)
    ){
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column (
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
            
                //LandingScreen跳转结束时间设置
                val currentOnTimeout by rememberUpdatedState(onTimeout)
                LaunchedEffect(Unit) {
                    delay(SPLASHWAITTIME)
                    currentOnTimeout()
                }
            }
        }
    }
}

