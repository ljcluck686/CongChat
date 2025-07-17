package com.software.mywechat.feature.discovery

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable

const val DISCOVERY_ROUTE = "discovery"

fun NavController.navigationToDiscovery():Unit{
    navigate(DISCOVERY_ROUTE)
}

fun NavGraphBuilder.discoveryScreen(

): Unit {
    myComposable(DISCOVERY_ROUTE) {

    }
}