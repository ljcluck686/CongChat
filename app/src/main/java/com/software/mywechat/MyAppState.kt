package com.software.mywechat

import android.content.Context
import com.software.mywechat.core.database.MyFriendDatabase
import com.software.mywechat.core.database.MyFriendListDatabase

object MyAppState {
    lateinit var applicationContext: Context
    var session: String = ""
    var userId: String = ""
    var userName: String =""
    var phone: String = ""
    var localAvatar:String = ""
    var myFriendDatabase: MyFriendDatabase? = null
    var myFriendListDatabase: MyFriendListDatabase? = null

}