package com.weiliang.jinitaimei.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.control.ImageLoad
import com.weiliang.jinitaimei.control.KunDongAndSpacer
import com.weiliang.jinitaimei.control.imageLoadForPaint
import kotlinx.coroutines.delay
import java.util.Locale

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
        modifier  = Modifier.paint(imagePainter,contentScale = ContentScale.Crop)
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

