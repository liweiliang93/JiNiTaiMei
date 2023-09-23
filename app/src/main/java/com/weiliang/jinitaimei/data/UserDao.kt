package com.weiliang.jinitaimei.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @author liweiliang
 * @create 2023-09-23 08:47:57
 * @description UserDao通过提供抽象接口将持久性层与应用的其余部分分离
 * @VERSION: 1.0
 */
@Dao
interface UserDao {
    @Insert
    fun registerUser(user: User)
    
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun loginUser(username: String, password: String): User?
}
