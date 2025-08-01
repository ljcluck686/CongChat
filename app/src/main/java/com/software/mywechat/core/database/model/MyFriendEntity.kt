package com.software.mywechat.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "my_friend_list"
)
data class MyFriendEntity (
    @PrimaryKey val userId:String,

    @ColumnInfo(name = "nickname")
    val nickname: String,
    @ColumnInfo(name= "local_avatar_url")
    val avatarUrl :String,
    //性别
    @ColumnInfo(name = "gender")
    val  gender :Int,
    //权限，1：我不看他，2看
    @ColumnInfo(name = "permissions")
    val permissions:Int
){

}