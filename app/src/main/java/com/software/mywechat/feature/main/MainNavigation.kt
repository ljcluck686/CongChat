package com.software.mywechat.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable

const val MAIN_ROUTE = "main"

fun NavController.navigationToMain(){
    navigate(MAIN_ROUTE)
}



fun NavGraphBuilder.mainScreen(

): Unit {
    myComposable(MAIN_ROUTE) {
        MainRoute(

        )
    }
}