@file:OptIn(ExperimentalMaterial3Api::class)
package com.weiliang.jinitaimei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.room.Room
import com.weiliang.jinitaimei.chess.ChessBoard
import com.weiliang.jinitaimei.chess.opening
import com.weiliang.jinitaimei.chess.toList
import com.weiliang.jinitaimei.control.ScreenControl
import com.weiliang.jinitaimei.data.UserDatabase
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme

/*
    ComponentActivity是Jetpack库类,用于支持 Jetpack Compose 的组件化开发。
*/
class MainActivity : ComponentActivity(){
    
    //建立一个MainActivity 的伴生对象database,将用于访问应用程序的 Room 数据库
    companion object {
        lateinit var database: UserDatabase
    }
     /*
        重写了 onCreate 方法,是 Android活动的生命周期方法之一,
        当活动第一次创建时，系统会调用此方法
     */
    override fun onCreate(savedInstanceState: Bundle?) {
    
         //创建数据库user-database
         database = Room.databaseBuilder(
             applicationContext,
             UserDatabase::class.java, "user-database"
         ).build()
         
         
        //调用父类的 onCreate 方法，确保它执行了必要的初始化工作
        super.onCreate(savedInstanceState)
        setContent {
            JiNiTaiMeiTheme {
                ScreenControl()
            }
        }
    }
}

// 预览亮色游戏界面
@Preview(showBackground = true)
@Composable
fun LightPreview() {
    JiNiTaiMeiTheme(0) {
        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}

// 预览暗色游戏界面
@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    JiNiTaiMeiTheme(1) {
        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}