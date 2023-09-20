package com.weiliang.jinitaimei.control

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.ComponentRegistry
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.weiliang.jinitaimei.R

/**
 * @author liweiliang
 * @create 2023-09-20 09:16:44
 * @description 控制painter图片的加载
 * @VERSION: 1.0
 */
@Composable
fun imageLoadForPaint(
    drawable: Int
): Painter {
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
        data = drawable,
        imageLoader = imageLoader,
        builder = {
            placeholder(R.drawable.cxk1)//占位图
            crossfade(true)//淡出效果
        }
    )
    return imagePainter
}

@Composable
fun ImageLoad(
    drawable: Int,
    size: Dp
){
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components(fun ComponentRegistry.Builder.() {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }).build()
    
    Image(
        modifier = Modifier
            .size(size),
        painter = rememberImagePainter(
            data = drawable,
            imageLoader = imageLoader,
            builder = {
                placeholder(R.drawable.cxk1)//占位图
                crossfade(true)//淡出效果
                
            }),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}