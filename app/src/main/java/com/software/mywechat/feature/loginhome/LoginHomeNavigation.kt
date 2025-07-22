package com.software.mywechat.feature.loginhome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.guide.GUIDE_ROUTE
import com.software.mywechat.ui.myComposable

const val LOGIN_HOME_ROUTE = "login_home"

fun NavController.navigateToLoginHome() {
    navigate(LOGIN_HOME_ROUTE){
        launchSingleTop = true
        popUpTo(GUIDE_ROUTE){
            inclusive = true
        }
    }
}

fun NavGraphBuilder.loginHomeScreen(
    toLogin: () -> Unit ,
    toRegister: () -> Unit ,
    finishAllLoginPages: () -> Unit ,
){
    myComposable(LOGIN_HOME_ROUTE){
        LoginHomeRoute(
            toLogin = toLogin,
            toRegister = toRegister,
            finishAllLoginPages = finishAllLoginPages,
        )
    }
}
