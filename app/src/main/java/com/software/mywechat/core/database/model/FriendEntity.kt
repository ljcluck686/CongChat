package com.software.mywechat.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "friend_list"
)
data class FriendEntity (
    @PrimaryKey val userId:String,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name= "local_avatar_url")
    val avatarUrl :String ,
    //性别
    @ColumnInfo(name = "gender")
    val  gender :Int ,
    @ColumnInfo(name="greet_msg")
    val greetMsg  :String ,
    //是否为好友
    @ColumnInfo(name = "status")
    val status : Int,
    //权限，1：我不看他，2看
    @ColumnInfo(name = "permissions")
    val permissions:Int
){
    
}