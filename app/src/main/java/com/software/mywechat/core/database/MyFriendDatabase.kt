package com.software.mywechat.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.software.mywechat.core.database.dao.FriendDao
import com.software.mywechat.core.database.model.FriendEntity

@Database(
    entities = [
        FriendEntity::class
    ],
    version = 1,
    exportSchema = false , //为true能导出一个json文件通过这个能回到任何一版本的数据库
)
abstract class MyFriendDatabase :RoomDatabase() {

    abstract fun friendDao(): FriendDao
}