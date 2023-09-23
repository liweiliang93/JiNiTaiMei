package com.weiliang.jinitaimei.screen

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.control.imageLoadForPaint
import com.weiliang.jinitaimei.data.User
import com.weiliang.jinitaimei.data.UserViewModel
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
) {
    //设置账号密码
    var name by remember { mutableStateOf(TextFieldValue()) }
    var pwd by remember { mutableStateOf(TextFieldValue()) }
    var conFirmpwd by remember { mutableStateOf("") }
    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember { mutableStateOf(true) }
    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None
    //加载背景图片
    val imagePainter = imageLoadForPaint(R.drawable.background1)
    val imagePainterZC = imageLoadForPaint(R.drawable.zc)
    val userViewModel = viewModel<UserViewModel>()
    
    JiNiTaiMeiTheme {
        Column(
            Modifier
                .fillMaxSize()
                .paint(imagePainter, contentScale = ContentScale.Crop),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            //图标
            Icon(
                painter = imagePainterZC,
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .padding(vertical = 30.dp)
                ,
                tint = Color.White
            )
            
            Row(
                modifier = Modifier.height(50.dp)
            ){
                IconButton(
                    onClick = {
                        navController.navigate("first")
                    },
                    modifier = Modifier
                        .size(80.dp)
                        .height(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "BackToLogin",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
    
                Text(
                    text = "PLEASE SIGN UP FOR PLAY",
                    color = Color.Black,
                    maxLines = 1
                )
    
                IconButton(
                    onClick = {
                        navController.navigate("first")
                    },
                    modifier = Modifier
                        .size(80.dp)
                        .height(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "BackToLogin",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            
            //账号输入框
            OutlinedTextField(
                isError = name.text.isEmpty(),
                singleLine = true,
                modifier = Modifier
                    .alpha(0.6f)
                    .width(390.dp),
                value = name,
                label ={
                   if(name.text.isEmpty()){
                       Text("Input your username")
                   }
                },
                onValueChange = {
                    str -> name = str
                },
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
                    .width(390.dp),
                isError = pwd.text.isEmpty(),
                singleLine = true,
                value = pwd, onValueChange = { str -> pwd = str },
                visualTransformation = transformation,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Gray
                ),
                placeholder = {
                    Text("输入你的密码")
                },
                label ={
                    if(pwd.text.isEmpty()){
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

            //密码确认框
            OutlinedTextField(
                isError = pwd.text != conFirmpwd || conFirmpwd.isEmpty(),
                singleLine = true,
                value = conFirmpwd, onValueChange = { str -> conFirmpwd = str },
                modifier = Modifier
                    .alpha(0.6f)
                    .width(390.dp),
                placeholder = {
                    Text("请确认你的密码")
                },
                label = {
                    Text("Input your password again")
                },
                visualTransformation = transformation,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Gray
                ),
                shape = RoundedCornerShape(16.dp), // 设置圆角
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp) // 设置圆角
                            )
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
            
            //注册按钮
            Button(
                modifier = Modifier
                    .width(250.dp),
                onClick = {
                    //如果账号存在,提示账号已存在
                    val user = User(Random(1).nextInt(),name.text, pwd.text)
                    // 使用 ViewModel 注册用户
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Main) {
                            if (userViewModel.loginUser(name.text, pwd.text) == user) {
                                println("账号已存在")
                            } else {
                                userViewModel.registerUser(user)
                                navController.navigate("first_page")
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xff5c59fe)),
                contentPadding = PaddingValues(12.dp, 16.dp)
            ) {
                Text("注册", color = Color.White, fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        
    }
}