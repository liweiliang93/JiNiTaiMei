package com.weiliang.jinitaimei.activity


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weiliang.jinitaimei.chess.Chess
import com.weiliang.jinitaimei.chess.ChessBoard
import com.weiliang.jinitaimei.chess.checkAndMoveX
import com.weiliang.jinitaimei.chess.checkAndMoveY
import com.weiliang.jinitaimei.chess.opening
import com.weiliang.jinitaimei.chess.toList
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme



//SecondPage
@Composable
fun MainScreen() {
    JiNiTaiMeiTheme {
        HuaRongDao()
    }
}


//导航栏:显示一个自定义的应用栏
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {

    //预定义组件:创建一个顶部应用栏
    TopAppBar(

        //windowInsets定义应用栏的系统栏仅在水平方向上填充
        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal),

        //定义应用栏的颜色
        colors = smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),

        //定义导航图标:Menu
        navigationIcon = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },

        //定义应用栏的标题部分
        title = {
            Text(
                text = "JiNiTaiMei",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        },

        //定义应用栏的操作按钮部分:Share、Settings
        actions = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    )
}

@Composable
fun HuaRongDao() {
    
    Surface(color = MaterialTheme.colorScheme.background) {

        Column {
            AppBar()
            Spacer(Modifier.height(20.dp))
            var chessState: List<Chess> by remember {
                mutableStateOf(opening.toList())
            }

            with(LocalDensity.current) {
                ChessBoard(
                    Modifier.weight(1f),
                    chessList = chessState,
                ) { cur, x, y ->
                    chessState = chessState.map {
                        if (it.name == cur) {
                            if (x != 0) it.checkAndMoveX(x, chessState)
                            else it.checkAndMoveY(y, chessState)
                        } else {
                            it
                        }
                    }
                }
            }

            Row {
                Spacer(modifier = Modifier.width(10.dp))
                
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { chessState = opening.toList() }) {
                    Text("Reset")
                }
                Spacer(modifier = Modifier.width(10.dp))

            }
            Spacer(Modifier.height(20.dp))

        }

    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    JiNiTaiMeiTheme {
        AppBar()
    }
}
