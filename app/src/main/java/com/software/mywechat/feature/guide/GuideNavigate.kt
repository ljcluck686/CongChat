package com.software.mywechat.feature.guide

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable
import com.software.mywechat.feature.guide.GuideRoute
const val GUIDE_ROUTE = "guide"

//fun NavController.navigationToGuide():Unit{
//    navigate(MAIN_ROUTE){
//        launchSingleTop = true
//        popUpTo(SPLASH_ROUTE){
//            inclusive = true
//        }
//    }
//
//}
//
fun NavGraphBuilder.guideScreen(
    toMain: () -> Unit,
): Unit {
    myComposable(GUIDE_ROUTE) {
        GuideRoute(
            toMain = toMain
        )
    }
}