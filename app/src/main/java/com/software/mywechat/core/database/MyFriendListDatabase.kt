package com.software.mywechat.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.software.mywechat.core.database.dao.FriendDao
import com.software.mywechat.core.database.dao.MyFriendDao
import com.software.mywechat.core.database.model.FriendEntity
import com.software.mywechat.core.database.model.MyFriendEntity

@Database(
    entities = [
        MyFriendEntity::class
    ],
    version = 1,
    exportSchema = false , //为true能导出一个json文件通过这个能回到任何一版本的数据库
)
abstract class MyFriendListDatabase : RoomDatabase() {

    abstract fun myFriendDao(): MyFriendDao
}