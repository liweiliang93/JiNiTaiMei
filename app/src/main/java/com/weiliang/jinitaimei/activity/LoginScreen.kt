package com.weiliang.jinitaimei.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weiliang.jinitaimei.R
import com.weiliang.jinitaimei.ui.theme.JiNiTaiMeiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
) {
    
    var name by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    
    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember {
        mutableStateOf(true)
    }
    
    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None
    
    
    JiNiTaiMeiTheme {
        Box(
            Modifier.fillMaxSize()
                .alpha(0.7f)
        ) {
            Image(painter = painterResource(id = R.drawable.background), contentDescription = null)
            Text(
                text = "注册",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 25.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Column() {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .background(Color.White)
                        .padding(40.dp)
                        .fillMaxWidth()
                ) {
                    //后面输入框等组件在这里加,由于代码过长,为了方便阅读,后续贴出的代码都是在这里的代码
                    Column() {
                    
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            placeholder = {
                                Text("请输入用户名")
                            },
                            onValueChange = { str -> name = str },
                            colors = TextFieldDefaults.textFieldColors(Color.Transparent),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.AccountBox,
                                    contentDescription = null
                                )
                            }
                        )
                    
                        TextField(
                            value = pwd, onValueChange = { str -> pwd = str },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("请输入密码")
                            },
                            visualTransformation = transformation,
                            colors = TextFieldDefaults.textFieldColors(Color.Transparent),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null
                                )
                            }, trailingIcon = {
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
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                if (name == "test" && pwd == "123") {
                                    navController.navigate("first_page")
                                } else {
                                
                                }
                            },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(Color(0xff5c59fe)),
                            contentPadding = PaddingValues(12.dp, 16.dp)
                        ) {
                            Text("登录", color = Color.White, fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(100.dp))
                        
                    }
                }
            }
        }
    }
}