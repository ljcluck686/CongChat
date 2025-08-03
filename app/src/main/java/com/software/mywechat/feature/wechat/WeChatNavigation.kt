package com.software.mywechat.feature.wechat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.software.mywechat.feature.userdetail.USER_ID

import com.software.mywechat.ui.myComposable

const val WE_CHAT_ROUTE = "we_chat"

fun NavController.navigationToWeChat(userId:String):Unit{
    navigate("$WE_CHAT_ROUTE/$userId")
}

fun NavGraphBuilder.weChatScreen(
):Unit{
    myComposable(
        "$WE_CHAT_ROUTE/{$USER_ID}",
        arguments = listOf(
            navArgument(USER_ID){type = NavType.StringType}
        )
    ){

    }

}