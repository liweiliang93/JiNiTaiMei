package com.weiliang.jinitaimei.activity

import android.os.Build
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ComponentRegistry
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.control.KunDongAndSpacer
import kotlinx.coroutines.delay
import java.util.Locale

/*
    LandingScreen:游戏加载界面,通过点击坤坤和拖动坤坤来等待游戏加载完成
*/

//使用 SPLASHWAITTIME 来进行与sleep进行延时,SPLASHWAITTIME 表示延长的时间
private const val SPLASHWAITTIME: Long = 60000

@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components(fun ComponentRegistry.Builder.() {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }).build()
    
    //加载背景图片
    val imagePainter: Painter =  rememberImagePainter(
        data = R.drawable.cxkdlq,
        imageLoader = imageLoader,
        builder = {
            placeholder(R.drawable.cxk1)//占位图
            crossfade(true)//淡出效果
        
        })
    
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
            
                CircularProgressIndicator()
                //加载提示框
                TopAppBar(modifier)
                Spacer(modifier = Modifier.padding(15.dp))
            
                //文字提示：诸神黄昏,迎接黎明
                TextHint(modifier, text = "Ragnarok, meet the dawn of a new era.")
            
                //诸神列表
                CharacterEventRow()
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
            
                //文字提示：请选择你最喜欢的一个坤坤
                TextHint(text = "Please choose the one you like best")
                Spacer(modifier = modifier.padding(vertical = 5.dp))
            
                //定义并使用渐变色colors
                val colors = listOf(Color(0xFF005599), Color(0xFF3FFFED))
                Row{
                    Column(
                        //边框设置为黑色
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier
                            .background(Color.White)
                            .border(  //设置渐变边框
                                border = BorderStroke(
                                    2.dp,
                                    Brush.linearGradient(colors)
                                ), //设置边框粗细与边框渐变色
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
}

/*
    等待提示函数:Loading时间和两个可点击按钮进行衬托
 */
@Composable
fun TopAppBar(
    modifier: Modifier
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {},
            modifier = modifier.size(45.dp)
        ) {}
        Spacer(modifier = modifier.padding(5.dp))
        Text(
            text = "Loading..请等待1分钟",
            style = MaterialTheme.typography.headlineSmall,
            color = colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.padding(5.dp))
        Button(
            onClick ={  },
            modifier = modifier.size(45.dp)
        ) {}
    }
}

/*
    标题文字函数(是否带padding限制):使用到英文标题的地方可调用此函数进行展示
 */
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
            color = colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
        
                }
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
            color = colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
        
                }
        )
    }
}

/*
    角色列表:可通过左右滑动方式观看角色
 */
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
                .background(colorScheme.primary)
                .clickable(
                    onClick = {}
                )
        ) {
            /*
                使用迭代器遍历CharacterEventData并对每个对象都调用CharacterEvent函数
             */
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


/*
    单个角色展示,为多个角色列表进行组合遍历
 */
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

/*
    文件map数组:将图片和文字组合后放入map集合内
 */
private val CharacterEventData = listOf(
    R.drawable.cxk1 to R.string.TieShanKao,
    R.drawable.qmzzr to R.string.QuMinZhiZuoRen,
    R.drawable.lq to R.string.LanQiu,
    R.drawable.tiao to R.string.Tiao,
    R.drawable.toupiao to R.string.TouPiao,
    R.drawable.huanghun to R.string.HuangHun,
).map { DrawableStringPair(it.first, it.second) }

/*
    Kotlin数据类:封装drawable和text属性
 */
private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)