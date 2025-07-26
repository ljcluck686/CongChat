package com.software.mywechat.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable

const val SEARCH_ROUTE = "search"

fun NavController.navigationToSearch():Unit{
    navigate(SEARCH_ROUTE)
}

fun NavGraphBuilder.searchScreen(
    toBack:()->Unit,
): Unit {
    myComposable(SEARCH_ROUTE) {
        SearchRoute(
            toBack = toBack
        )
    }
}