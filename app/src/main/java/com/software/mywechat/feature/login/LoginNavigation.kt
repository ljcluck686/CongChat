package com.software.mywechat.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable

const val LOGIN_ROUTE = "login"

fun NavController.navigationToLogin():Unit{
    navigate(LOGIN_ROUTE)
}

fun NavGraphBuilder.loginScreen():Unit{
    myComposable(LOGIN_ROUTE){

    }
}