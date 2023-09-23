package com.weiliang.jinitaimei.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.control.imageLoadForPaint
import com.weiliang.jinitaimei.data.UserViewModel
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
) {
    //设置账号密码
    var name by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember { mutableStateOf(true) }
    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None
    //加载背景图片
    val imagePainter = imageLoadForPaint(R.drawable.sz)
    val imagePainterHK = imageLoadForPaint(R.drawable.hk)
    val userViewModel: UserViewModel = viewModel()
    
    JiNiTaiMeiTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(imagePainter, contentScale = ContentScale.FillHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            //登录提示
            Text(
                text = "LOGIN YOUR ACCOUNT",
                color = Color.White,
                maxLines = 1,
                modifier = Modifier.padding(vertical = 50.dp)
            )
            
            //图标
            Icon(
                painter = imagePainterHK,
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .padding(vertical = 30.dp)
                ,
                tint = Color.White
            )
            
            //账号输入框
            OutlinedTextField(
                isError = name.isEmpty(),
                singleLine = true,
                modifier = Modifier
                    .alpha(0.6f)
                    .width(370.dp),
                value = name,
                placeholder = {
                    Text("请输入用户名")
                },
                label = {
                    if (name.isEmpty()) {
                        Text("Input your username")
                    }
                },
                onValueChange = { str -> name = str },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Gray
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(16.dp), // 设置圆角
            )
    
            //密码输入框
            OutlinedTextField(
                modifier = Modifier
                    .alpha(0.6f)
                    .width(370.dp),
                isError = pwd.isEmpty(),
                singleLine = true,
                value = pwd,
                onValueChange = { str -> pwd = str },
                placeholder = {
                    Text("请输入密码")
                },
                visualTransformation = transformation,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Gray
                ),
                label = {
                    if (pwd.isEmpty()) {
                        Text("Input your password")
                    }
                },
                shape = RoundedCornerShape(16.dp), // 设置圆角
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (showPwd) {
                        IconButton(onClick = { showPwd = !showPwd }) {
                            Icon(
                                painter = painterResource(id = R.drawable.kt1),
                                contentDescription = null,
                                Modifier.size(30.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { showPwd = !showPwd }) {
                            Icon(
                                painter = painterResource(id = R.drawable.kt2),
                                contentDescription = null,
                                Modifier.size(30.dp)
                            )
                        }
                    }
                }
            )
    
            Spacer(modifier = Modifier.height(20.dp))
    
            Button(
                modifier = Modifier.width(250.dp)
                ,
                onClick = {
                    //启动一个后台协程
                    CoroutineScope(Dispatchers.IO).launch {
                        val user = userViewModel.loginUser(name, pwd)
                        withContext(Dispatchers.Main) {
                            if (user != null) {
                                println("登录成功")
                                navController.navigate("first_page")
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xff5c59fe)),
                contentPadding = PaddingValues(12.dp, 16.dp)
            ) {
                Text("登录", color = Color.White, fontSize = 18.sp)
            }
            
            Divider(
                color = Color.White.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(top = 48.dp)
                    .width(360.dp)
            )
            
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Don't have a account?",color = Color.White)
                TextButton(onClick = {
                    navController.navigate("second")
                }) {
                    Text(text = "SIGN UP")
                }
            }
            
        }
    }
}