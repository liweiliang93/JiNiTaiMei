package com.weiliang.jinitaimei.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author liweiliang
 * @create 2023-09-23 08:47:57
 * @description 实体类User:Entity 类定义了一个表，该类的每个实例都表示数据库表中的一行数据
 * @VERSION: 1.0
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") val password: String?
)