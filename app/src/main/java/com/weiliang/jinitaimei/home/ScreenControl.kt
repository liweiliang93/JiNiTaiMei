package com.weiliang.jinitaimei.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JiNiTaiMeiTheme {
        ScreenControl()
    }
}

@Composable
fun ScreenControl() {
    // 创建NavController
    val navController = rememberNavController()
    //创建开始页面
    var showLandingScreen by remember {
        mutableStateOf(true)
    }
    //判断是否显示LandingScreen
    if(showLandingScreen){
        //显示LandingScreen
        LandingScreen(onTimeout = { showLandingScreen = false })
    }else{
        // 用NavHost将NavController和导航图相关联，startDestination指定起始的可组合项
        NavHost(navController = navController, startDestination = "first_page") {

            // 给FirstPage可组合项指定路径
            composable("first_page") {
                OnboardingScreen(navController)
            }
            // 给SecondPage可组合项指定路径
            composable("second_page") {
                MainScreen(navController)
            }

        }
    }
}