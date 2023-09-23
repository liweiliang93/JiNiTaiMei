package com.weiliang.jinitaimei.data


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiliang.jinitaimei.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @author liweiliang
 * @create 2023-09-23 08:47:57
 * @description UserViewModel:使用viewModelScope协程允许在后台线程上执行用户注册和登录的数据库操作，以确保不会阻塞主线程并保持应用的响应性
 *                            使用MainActivity中同步创建的user-database数据库
 * @VERSION: 1.0
 */
class UserViewModel : ViewModel() {
    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            // 在后台线程执行注册操作
            MainActivity.database.userDao().registerUser(user)
        }
    }
    suspend fun loginUser(username: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            // 在这里执行数据库操作，例如插入数据或查询数据
            return@withContext MainActivity.database.userDao().loginUser(username, password)
        }
    }
    
}
