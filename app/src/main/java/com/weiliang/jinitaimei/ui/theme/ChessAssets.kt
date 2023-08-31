package com.weiliang.jinitaimei.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.weiliang.jinitaimei.R


//密封接口定义象棋棋子资源的属性
sealed interface ChessAssets {
    val huangzhong: Int
    val caocao: Int
    val zhaoyun: Int
    val machao: Int
    val zhangfei: Int
    val guanyu: Int
    val zu: Int
}

//暗色接口
object DarkChess : ChessAssets {
    override val huangzhong = R.drawable.huangzhong
    override val caocao = R.drawable.caocao
    override val zhaoyun = R.drawable.zhaoyun
    override val zhangfei = R.drawable.zhangfei
    override val guanyu = R.drawable.guanyu
    override val machao = R.drawable.machao
    override val zu = R.drawable.zu
}

//亮色接口
object LightChess : ChessAssets {
    override val huangzhong = R.drawable.huagnzhong_2
    override val caocao = R.drawable.caocao_2
    override val zhaoyun = R.drawable.zhaoyun_2
    override val zhangfei = R.drawable.zhangfei_2
    override val guanyu = R.drawable.guanyu_2
    override val machao = R.drawable.machao_2
    override val zu = R.drawable.zu_2
}

//在 Composable 中获取当前的棋盘资源，默认情况下,使用 DarkChess对象
internal var LocalChessAssets = compositionLocalOf<ChessAssets> { DarkChess }

//允许你在 Composable 中获取当前主题下的棋盘资源,使用局部组合对象 LocalChessAssets 的当前值作为资源提供
val MaterialTheme.chessAssets
    @Composable
    @ReadOnlyComposable
    get() = LocalChessAssets.current