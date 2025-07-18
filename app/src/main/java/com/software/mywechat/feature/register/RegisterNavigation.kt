package com.software.mywechat.feature.register

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.software.mywechat.ui.myComposable

const val REGISTER_ROUTE = "register"

fun NavController.navigationToRegister():Unit{
    navigate(REGISTER_ROUTE)
}

fun NavGraphBuilder.registerScreen(
    toBack:()->Unit,
): Unit {
    myComposable(REGISTER_ROUTE) {
        RegisterRoute (
            toBack = toBack,
        )
    }
}