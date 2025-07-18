package com.software.mywechat.feature.main

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.software.mywechat.core.design.component.MyNavigationBar
import com.software.mywechat.feature.addressbook.AddressBookRoute
import com.software.mywechat.feature.discovery.DiscoveryRoute
import com.software.mywechat.feature.splash.SplashRoute
import com.software.mywechat.feature.me.MeRoute
import com.software.mywechat.util.CurrentActivityHolder
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    toLogin:()->Unit,
    toRegister:()->Unit,
){
    MainScreen(
        toLogin = toLogin,
        toRegister = toRegister,
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    toLogin:()->Unit={},
    toRegister:()->Unit={},
){
    var currentDestination by rememberSaveable() {
        mutableStateOf(TopLevelDestination.SPLASH.route)
    }

    val scope = rememberCoroutineScope()



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState{
            TopLevelDestination.entries.size
        }

        LaunchedEffect(pagerState.currentPage) {
            currentDestination = TopLevelDestination.values()[pagerState.currentPage].route
        }



        HorizontalPager(
            state= pagerState,
            userScrollEnabled = true,
            modifier= Modifier
                .weight(1f).fillMaxWidth()
        ) { page ->
            when(page){
                0 -> SplashRoute()
                1 -> AddressBookRoute()
                2 -> DiscoveryRoute()
                3 -> MeRoute(toLogin,toRegister)
            }
        }
        MyNavigationBar(
            destinations = TopLevelDestination.entries,
            onNavigateToDestination = {
                currentDestination = TopLevelDestination.values()[it].route
                scope.launch {
                    pagerState.scrollToPage(it)
                }
            },
            currentDestination = currentDestination,
            modifier = Modifier,
        )
    }
}
