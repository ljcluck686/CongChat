package com.software.mywechat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.software.mywechat.feature.guide.GUIDE_ROUTE
import com.software.mywechat.feature.guide.guideScreen


@Composable
fun MyApp(

){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GUIDE_ROUTE) {
        guideScreen()
    }
}