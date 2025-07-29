package com.software.mywechat.feature.fixname

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.discovery.DiscoveryRoute
import com.software.mywechat.ui.myComposable

const val FIX_NAME_ROUTE = "fix_name"

fun NavController.navigationToFixName():Unit{
    navigate(FIX_NAME_ROUTE)
}

fun NavGraphBuilder.fixNameScreen(
    toBack:()->Unit,
    ToProfile:()->Unit,
    finishAllLoginPages:()->Unit,
): Unit {
    myComposable(FIX_NAME_ROUTE) {
        FixNameRoute(
            toBack = toBack,
            ToProfile = ToProfile,
            finishAllLoginPages = finishAllLoginPages,
        )
    }
}