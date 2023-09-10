package com.weiliang.jinitaimei.home

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.weiliang.jinitaimei.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


@Composable
fun KunDongAndSpacer(
    modifier: Modifier,
    headImage: Int,
    headMusic: Int,
    music:Int,
    image :Int,
    hint:String
){
    Row {
        Spacer(modifier = modifier.padding(10.dp))
        val mediaPlayer: MediaPlayer?
        mediaPlayer = MediaPlayer.create(LocalContext.current,headMusic)
        Image(
            painterResource(headImage),
            contentDescription = "avatar",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    if(!mediaPlayer.isPlaying){
                        mediaPlayer?.start()
                    }else{
                        mediaPlayer?.pause()
                    }
                }
        )

        KunDong(modifier, music, image, hint)
    }
    Spacer(modifier = modifier.padding(10.dp))
}


@Composable
fun KunDong(
    modifier: Modifier,
    music:Int,
    image :Int,
    hint:String
) {
    val mediaPlayer: MediaPlayer? = MediaPlayer.create(LocalContext.current,music)
    var offset by remember { mutableStateOf(Offset.Zero) }
    Row {
        //文字提示：移动或者点击坤坤
        Text(
            text = hint.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(MaterialTheme.colorScheme.tertiary)
        )
        Spacer(modifier = modifier.height(0.dp).width(40.dp))
        Box(
            modifier = Modifier
        ) {
            Box(Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .background(Color.White)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            mediaPlayer?.start()
                        },
                        onDragEnd = {
                            mediaPlayer?.pause()
                        },
                        onDragCancel = {
                            mediaPlayer?.stop()
                            mediaPlayer?.release()
                        },
                        onDrag = { _: PointerInputChange, dragAmount: Offset ->
                            offset += dragAmount
                        }
                    )
                }
            ){
                var big by remember {
                    mutableStateOf(false)
                }
                val size by animateDpAsState(if (big) 80.dp else 50.dp, label = "")

                Image(
                    painterResource(id = image),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(size)
                        .fillMaxSize()
                        .clickable {
                            big = !big
                        },
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
fun Show(){
    val lazyListState = rememberLazyListState()
    val allTasks = stringArrayResource(R.array.tasks)
    val tasks = remember { mutableStateListOf(*allTasks) }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        state = lazyListState,
        modifier = Modifier
            .padding()
            .background(MaterialTheme.colorScheme.tertiary)
    ){
        if (tasks.isEmpty()) {
            item {
                TextButton(onClick = { tasks.clear(); tasks.addAll(allTasks) }) {
                    Text(stringResource(R.string.add_tasks))
                }
            }
        }
        items(count = tasks.size) { i ->
            val task = tasks.getOrNull(i)
            if (task != null) {
                key(task) {
                    TaskRow(
                        task = task,
                        onRemove = { tasks.remove(task) }
                    )
                }
            }
        }
    }
}


//加载出诸神列表:
@Composable
fun TaskRow(task: String, onRemove: () -> Unit) {
    Surface(
        modifier = Modifier
            .swipeToDismiss(onRemove)
            .fillMaxWidth(),
        shadowElevation = 2.dp


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = task,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@SuppressLint("ReturnFromAwaitPointerEventScope", "MultipleAwaitPointerEventScopes")
private fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            while (true) {
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetX.stop()
                val velocityTracker = VelocityTracker()
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->
                        val horizontalDragOffset = offsetX.value + change.positionChange().x
                        launch {
                            offsetX.snapTo(horizontalDragOffset)
                        }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        if (change.positionChange() != Offset.Zero) change.consume()
                    }
                }
                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue <= size.width) {
                        offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                    } else {
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    }
                }
            }
        }
    }
}