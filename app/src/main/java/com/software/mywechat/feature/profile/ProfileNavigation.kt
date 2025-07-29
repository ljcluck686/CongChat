package com.software.mywechat.feature.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.fixname.FIX_NAME_ROUTE
import com.software.mywechat.ui.myComposable

const val PROFILE_ROUTE = "profile"

fun NavController.navigationToProfile():Unit{
    navigate(PROFILE_ROUTE)
}
fun NavController.finishAllLoginPage() {
    popBackStack(FIX_NAME_ROUTE, inclusive = true)
}

fun NavGraphBuilder.profileScreen(
    toBack:()->Unit,
    toFixName:()->Unit,
):Unit{
    myComposable(
        PROFILE_ROUTE
    ){
        ProfileRoute(
            toBack = toBack,
            toFixName = toFixName,
        )
    }
}