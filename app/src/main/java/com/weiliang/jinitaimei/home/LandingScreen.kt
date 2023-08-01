package com.weiliang.jinitaimei.home


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.weiliang.jinitaimei.R
import kotlinx.coroutines.delay
import java.util.Locale


private const val SplashWaitTime: Long = 1000
//LandingScreen
@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column (
            modifier = modifier.fillMaxSize()
        ){

            //LandingScreen跳转结束时间设置
            val currentOnTimeout by rememberUpdatedState(onTimeout)
            LaunchedEffect(Unit) {
                delay(SplashWaitTime)
                currentOnTimeout()
            }
            Spacer(modifier = Modifier.padding(20.dp))

            //加载提示框
            TopAppBar(modifier)
            Spacer(modifier = Modifier.padding(15.dp))

            //文字提示：诸神黄昏,迎接黎明
            TextHint(modifier, text = "      Ragnarok, meet the dawn of a new era.")

            //诸神列表
            CharacterEventRow()
            Spacer(modifier = Modifier.padding(vertical = 3.dp))

            TextHint(text = "          Please choose the one you like best")

            val colors = listOf(Color(0xFF005599), Color(0xFF3FFFED))
            Row {
                Column(
                    //边框设置为黑色
                    modifier = modifier
                        .background(Color.White)
                        .padding(25.dp)
                        .border(  //设置渐变边框
                            border = BorderStroke(1.dp, Brush.linearGradient(colors)), //设置边框粗细与边框渐变色
                            shape = RoundedCornerShape(5.dp) //圆角形状
                        )
                ) {
                    //加载提示框
                    KunDongAndSpacer(modifier,R.drawable.kt1, R.raw.nzj,R.raw.ngm, R.drawable.dlq,"巅峰引来虚伪的拥护")

                    KunDongAndSpacer(modifier,R.drawable.kt2, R.raw.dxj ,R.raw.jntm, R.drawable.cxk1,"    中分头背带裤     ")

                    KunDongAndSpacer(modifier,R.drawable.kt3, R.raw.music,R.raw.lblhnkg, R.drawable.cxk1,"    山外青山楼外楼  ")

                    KunDongAndSpacer(modifier,R.drawable.kt4, R.raw.ctrapnusic,R.raw.gy, R.drawable.cxk1,"    唱跳RAP打篮球    ")

                    KunDongAndSpacer(modifier,R.drawable.kt5, R.raw.cj,R.raw.qz, R.drawable.cxk1,"黄昏见证虔诚的信徒")
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                //设置主题为黑色

            },
            modifier = modifier.size(45.dp)
        ) {}
        Spacer(modifier = modifier.padding(5.dp))
        Text(
            text = "Loading..请等待1分钟",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.padding(5.dp))
        Button(
            onClick ={},
            modifier = modifier.size(45.dp)
        ) {}
    }
}

@Composable
fun TextHint(
    modifier: Modifier,
    text: String
){
    Row(
        modifier = modifier.padding(5.dp)
    ){
        Text(
            text = text.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun TextHint(
    text: String
){
    Row(
        modifier = Modifier
    ){
        Text(
            text = text.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun CharacterEventRow(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(10.dp),
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
                .background(MaterialTheme.colorScheme.primary)
        ) {
            //图片可组合项
            items(CharacterEventData) { item ->
                CharacterEvent(
                    item.drawable,
                    item.text,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun CharacterEvent(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //图片可组合项
        Image(
            //来源
            painter = painterResource(drawable),
            //描述
            contentDescription = null,
            //contentScale:缩放
            contentScale = ContentScale.Crop,
            //形状、大小
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        //文字可组合项
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}

private val CharacterEventData = listOf(
    R.drawable.cxk1 to R.string.TieShanKao,
    R.drawable.qmzzr to R.string.QuMinZhiZuoRen,
    R.drawable.lq to R.string.LanQiu,
    R.drawable.tiao to R.string.Tiao,
    R.drawable.toupiao to R.string.TouPiao,
    R.drawable.huanghun to R.string.HuangHun,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
