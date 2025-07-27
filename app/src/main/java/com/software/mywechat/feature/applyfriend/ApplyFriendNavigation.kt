package com.software.mywechat.feature.applyfriend

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.software.mywechat.feature.userdetail.USER_DETAIL_ROUTE
import com.software.mywechat.feature.userdetail.USER_ID
import com.software.mywechat.feature.userdetail.UserDetailRoute
import com.software.mywechat.ui.myComposable

const val APPLY_FRIEND_ROUTE = "apply_friend"

fun NavController.toApplyFriend(userId:String):Unit{
    navigate("${APPLY_FRIEND_ROUTE}/$userId")
}

fun NavGraphBuilder.applyFriendScreen(
    toBack:()->Unit
):Unit{
    myComposable(
        "$APPLY_FRIEND_ROUTE/{$USER_ID}",
        arguments = listOf(
            navArgument(USER_ID){type = NavType.StringType}
        )
    ){
        ApplyFriendRoute(
            toBack = toBack
        )
    }

}