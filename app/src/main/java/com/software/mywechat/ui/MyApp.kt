package com.software.mywechat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.feature.addFriend.addFriendScreen
import com.software.mywechat.feature.addFriend.navigationToAddFriend
import com.software.mywechat.feature.addressbook.addressBookScreen
import com.software.mywechat.feature.addressbook.navigationToAddressBook
import com.software.mywechat.feature.applyfriend.applyFriendScreen
import com.software.mywechat.feature.applyfriend.finish
import com.software.mywechat.feature.applyfriend.toApplyFriend
import com.software.mywechat.feature.discovery.discoveryScreen
import com.software.mywechat.feature.fixname.fixNameScreen
import com.software.mywechat.feature.fixname.navigationToFixName
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
import com.software.mywechat.feature.profile.finishAllLoginPage
import com.software.mywechat.feature.profile.navigationToProfile
import com.software.mywechat.feature.profile.profileScreen
import com.software.mywechat.feature.register.navigationToRegister
import com.software.mywechat.feature.register.registerScreen
import com.software.mywechat.feature.search.navigationToSearch
import com.software.mywechat.feature.search.searchScreen
import com.software.mywechat.feature.setting.navigationToSetting
import com.software.mywechat.feature.setting.settingScreen
import com.software.mywechat.feature.splash.splashScreen
import com.software.mywechat.feature.userdetail.navigationToUserDetail
import com.software.mywechat.feature.userdetail.userDetailScreen


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
            toProfile = navController::navigationToProfile,
            toSetting = navController::navigationToSetting
        )
        splashScreen()
        addressBookScreen(
            toNewFriend = navController::navigationToNewFriend
        )
        discoveryScreen(
            toLogin = navController::navigationToLogin
        )
        meScreen(
            toLoginHome = navController::navigateToLoginHome,
            toProfile = navController::navigationToProfile,
            toSetting = navController::navigationToSetting,
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
            toBack = navController::popBackStack,
            toAddFriend = navController::navigationToAddFriend,
            toSearch = navController::navigationToSearch,
            toUserDetail = navController::navigationToUserDetail
        )
        addFriendScreen(
            toBack = navController::popBackStack,
            toSearch = navController::navigationToSearch
        )
        searchScreen(
            toBack = navController::popBackStack,
            toUserDetail = navController::navigationToUserDetail,
        )
        userDetailScreen(
            toBack = navController::popBackStack,
            toApplyFriend = navController::toApplyFriend,
        )
        applyFriendScreen(
            toBack = navController::popBackStack,
            toAddressBook = navController::navigationToAddressBook,
            finsh = navController::finish
        )
        profileScreen(
            toBack = navController::popBackStack,
            toFixName = navController::navigationToFixName,
        )
        fixNameScreen(
            toBack = navController::popBackStack,
            toProfile = navController::navigationToProfile,
            finishAllLoginPages = navController::finishAllLoginPage,
        )
        settingScreen(
            toBack = navController::popBackStack,
            toProfile = navController::navigationToProfile,
            toLoginHome = navController::navigateToLoginHome
        )
    }
}