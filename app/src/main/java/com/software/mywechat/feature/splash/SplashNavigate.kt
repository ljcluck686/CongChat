package com.software.mywechat.feature.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable

const val SPLASH_ROUTE = "splash"

fun NavController.navigationToSplash():Unit{
    navigate(SPLASH_ROUTE)
}

fun NavGraphBuilder.splashScreen(

): Unit {
    myComposable(SPLASH_ROUTE) {

    }
}