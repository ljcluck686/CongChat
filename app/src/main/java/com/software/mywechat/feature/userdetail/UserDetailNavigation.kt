package com.software.mywechat.feature.userdetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.software.mywechat.ui.myComposable


const val USER_DETAIL_ROUTE = "user_detail"
const val USER_ID = "user_id"

fun NavController.navigationToUserDetail(userId:String):Unit{
    navigate("${USER_DETAIL_ROUTE}/$userId")
}

fun NavGraphBuilder.userDetailScreen(
    toBack:()->Unit
):Unit{
    myComposable(
        "${USER_DETAIL_ROUTE}/{${USER_ID}}",
        arguments = listOf(
            navArgument(USER_ID){type = NavType.StringType}
        )
    ){
        UserDetailRoute(
            toBack = toBack
        )
    }

}
