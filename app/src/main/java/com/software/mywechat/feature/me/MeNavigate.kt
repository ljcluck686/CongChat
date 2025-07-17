package com.software.mywechat.feature.me

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.guide.GuideRoute
import com.software.mywechat.ui.myComposable

const val ME_ROUTE = "me"

fun NavController.navigationToMe():Unit{
    navigate(ME_ROUTE)
}

fun NavGraphBuilder.meScreen(

): Unit {
    myComposable(ME_ROUTE) {

    }
}