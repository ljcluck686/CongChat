package com.software.mywechat.feature.guide

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable
import com.software.mywechat.feature.guide.GuideRoute
import com.software.mywechat.feature.main.MAIN_ROUTE
import com.software.mywechat.feature.splash.SPLASH_ROUTE
import com.software.mywechat.ui.MyAppUiState

const val GUIDE_ROUTE = "guide"

fun NavController.navigationToGuide():Unit{
    navigate(GUIDE_ROUTE){
//        launchSingleTop = true
//        popUpTo(SPLASH_ROUTE){
//            inclusive = true
//        }
    }
}

fun NavGraphBuilder.guideScreen(
    appUiState : MyAppUiState,
    toMain: () -> Unit,
    toLoginHome: () -> Unit,
): Unit {
    myComposable(GUIDE_ROUTE) {
        GuideRoute(
            appUiState = appUiState,
            toMain = toMain,
            toLoginHome = toLoginHome,
        )
    }
}