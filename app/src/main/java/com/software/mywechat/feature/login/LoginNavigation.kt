package com.software.mywechat.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.loginhome.LOGIN_HOME_ROUTE
import com.software.mywechat.ui.myComposable

const val LOGIN_ROUTE = "login"

fun NavController.navigationToLogin():Unit{
    navigate(LOGIN_ROUTE)
}

fun NavController.finishAllLoginPages() {
    popBackStack(LOGIN_HOME_ROUTE, inclusive = true)
}

fun NavGraphBuilder.loginScreen(
    toBack: () -> Unit,
    toRegister: () -> Unit,
):Unit{
    myComposable(LOGIN_ROUTE){
        LoginRoute(
            toBack=toBack,
            toRegister=toRegister,
        )
    }
}