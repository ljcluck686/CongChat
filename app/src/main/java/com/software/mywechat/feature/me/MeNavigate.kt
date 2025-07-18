package com.software.mywechat.feature.me

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.software.mywechat.feature.guide.GuideRoute
import com.software.mywechat.feature.main.MAIN_ROUTE
import com.software.mywechat.ui.myComposable
import com.software.mywechat.util.CurrentActivityHolder

const val ME_ROUTE = "me"

fun NavController.navigationToMe():Unit{
    navigate(ME_ROUTE)
}


fun NavGraphBuilder.meScreen(
    toLogin:()->Unit,
    toRegister:()->Unit,
): Unit {
    myComposable(ME_ROUTE) {
        MeRoute (
            toLogin = toLogin,
            toRegister = toRegister,
        )


    }
}