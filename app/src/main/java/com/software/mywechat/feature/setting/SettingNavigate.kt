package com.software.mywechat.feature.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable


const val SETTING_ROUTE = "me"

fun NavController.navigationToSetting():Unit{
    navigate(SETTING_ROUTE)
}


fun NavGraphBuilder.settingScreen(
    toBack:()->Unit,
    toLoginHome:()->Unit,
    toProfile:()->Unit,
): Unit {
    myComposable(SETTING_ROUTE) {
        SettingRoute (
            toBack = toBack,
            toLoginHome = toLoginHome,
            toProfile = toProfile
        )
    }
}