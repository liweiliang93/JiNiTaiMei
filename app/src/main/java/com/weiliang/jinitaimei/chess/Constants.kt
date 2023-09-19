package com.weiliang.jinitaimei.chess

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.IntOffset
import com.weiliang.jinitaimei.ui.chess.chessAssets

/*
    定义各个角色的棋子：定义棋子的形状
*/
val zhang = Chess("张飞", { MaterialTheme.chessAssets.zhangfei }, 1, 2)
val cao = Chess("曹操", { MaterialTheme.chessAssets.caocao }, 2, 2)
val huang = Chess("黄忠", { MaterialTheme.chessAssets.huangzhong }, 1, 2)
val zhao = Chess("赵云", { MaterialTheme.chessAssets.zhaoyun }, 1, 2)
val ma = Chess("马超", { MaterialTheme.chessAssets.machao }, 1, 2)
val guan = Chess("关羽", { MaterialTheme.chessAssets.guanyu }, 2, 1)

val zu = buildList { repeat(4) { add(Chess("卒$it", { MaterialTheme.chessAssets.zu }, 1, 1)) } }

typealias ChessOpening = List<Triple<Chess, Int, Int>>


/*
   定义一个游戏开局：Triple(棋子,X坐标,Y坐标)
*/
@OptIn(ExperimentalStdlibApi::class)
val opening: ChessOpening = buildList {
    add(Triple(zhang, 0, 0))
    add(Triple(cao, 1, 0))
    add(Triple(zhao, 3, 0))
    add(Triple(huang, 0, 2))
    add(Triple(ma, 3, 2))
    add(Triple(guan, 1, 2))
    add(Triple(zu[0], 0, 4))
    add(Triple(zu[1], 1, 3))
    add(Triple(zu[2], 2, 3))
    add(Triple(zu[3], 3, 4))
}


/*
    将 gameOpening 转化为List<Chess>
 */
fun ChessOpening.toList() =
    map { (chess, x, y) ->
        chess.moveBy(IntOffset(x * boardGridPx, y * boardGridPx))
    }
