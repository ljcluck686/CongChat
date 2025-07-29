package com.software.mywechat.feature.main

import androidx.activity.compose.BackHandler
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.software.mywechat.feature.guide.GUIDE_ROUTE
import com.software.mywechat.feature.loginhome.LOGIN_HOME_ROUTE
import com.software.mywechat.feature.splash.SPLASH_ROUTE
import com.software.mywechat.ui.MyAppUiState
import com.software.mywechat.ui.myComposable
import com.software.mywechat.util.CurrentActivityHolder

const val MAIN_ROUTE = "main"

fun NavController.navigationToMain(){
    navigate(MAIN_ROUTE){
        launchSingleTop = true
        popUpTo(GUIDE_ROUTE){
            inclusive = true
        }
        popUpTo(LOGIN_HOME_ROUTE){
            inclusive = true
        }
    }
}



fun NavGraphBuilder.mainScreen(
    appUiState : MyAppUiState,
    toLogin:()->Unit,
    toRegister:()->Unit,
    toLoginHome:()->Unit,
    toNewFriend:()->Unit,
    toProfile:()->Unit,
): Unit {
    myComposable(MAIN_ROUTE) {
        MainRoute(
            appUiState = appUiState,
            toLogin = toLogin,
            toRegister =toRegister,
            toLoginHome = toLoginHome,
            toNewFriend =toNewFriend,
            toProfile = toProfile,
        )

    }
}