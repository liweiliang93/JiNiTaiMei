package com.weiliang.jinitaimei.data

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * @author liweiliang
 * @create 2023-09-23 08:47:57
 * @description 数据库实体类:定义了一个 Room 数据库,获取到用于执行数据库操作的 UserDao 对象
 *              配置了它的版本号、包含的实体类，以及是否导出模式信息
 * @VERSION: 1.0
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
