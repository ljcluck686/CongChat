package com.software.mywechat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.software.mywechat.feature.addressbook.addressBookScreen
import com.software.mywechat.feature.discovery.discoveryScreen
import com.software.mywechat.feature.guide.GUIDE_ROUTE
import com.software.mywechat.feature.guide.guideScreen
import com.software.mywechat.feature.main.MAIN_ROUTE
import com.software.mywechat.feature.main.mainScreen
import com.software.mywechat.feature.me.meScreen
import com.software.mywechat.feature.splash.splashScreen


@Composable
fun MyApp(

){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MAIN_ROUTE) {
        mainScreen()
        splashScreen()
        addressBookScreen()
        discoveryScreen()
        meScreen()
        guideScreen()
    }
}