package com.software.mywechat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.feature.addressbook.addressBookScreen
import com.software.mywechat.feature.discovery.discoveryScreen
import com.software.mywechat.feature.guide.GUIDE_ROUTE
import com.software.mywechat.feature.guide.guideScreen
import com.software.mywechat.feature.login.finishAllLoginPages
import com.software.mywechat.feature.login.loginScreen
import com.software.mywechat.feature.login.navigationToLogin
import com.software.mywechat.feature.loginhome.loginHomeScreen
import com.software.mywechat.feature.loginhome.navigateToLoginHome
import com.software.mywechat.feature.main.MAIN_ROUTE
import com.software.mywechat.feature.main.mainScreen
import com.software.mywechat.feature.main.navigationToMain
import com.software.mywechat.feature.me.meScreen
import com.software.mywechat.feature.newfriend.navigationToNewFriend
import com.software.mywechat.feature.newfriend.newFriendScreen
import com.software.mywechat.feature.register.navigationToRegister
import com.software.mywechat.feature.register.registerScreen
import com.software.mywechat.feature.splash.splashScreen


@Composable
fun MyApp(
    userDataRepository: UserDataRepository,
    appUiState: MyAppUiState = rememberMyAppUiState(
        userDataRepository = userDataRepository,
    ),
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GUIDE_ROUTE) {
        mainScreen(
            appUiState = appUiState,
            toLogin = navController::navigationToLogin,
            toRegister = navController::navigationToRegister,
            toLoginHome = navController::navigateToLoginHome,
            toNewFriend = navController::navigationToNewFriend,
        )
        splashScreen()
        addressBookScreen(
            toNewFriend = navController::navigationToNewFriend
        )
        discoveryScreen(
            toLogin = navController::navigationToLogin
        )
        meScreen(
            toLoginHome = navController::navigateToLoginHome
        )
        guideScreen(
            appUiState = appUiState,
            toMain = navController::navigationToMain,
            toLoginHome = navController::navigateToLoginHome
        )
        loginScreen(
            toBack = navController::popBackStack,
            toRegister = navController::navigationToRegister,
            toMain = navController::navigationToMain,
            finishAllLoginPages = navController::finishAllLoginPages
        )
        registerScreen(
            toBack = navController::popBackStack
        )
        loginHomeScreen(
            toLogin = navController::navigationToLogin,
            toRegister = navController::navigationToRegister,
            finishAllLoginPages = navController::finishAllLoginPages
        )
        newFriendScreen(
            toBack = navController::popBackStack
        )
    }
}