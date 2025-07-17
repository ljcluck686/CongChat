package com.software.mywechat.feature.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.software.mywechat.core.design.component.MyNavigationBar
import com.software.mywechat.feature.addressbook.AddressBookRoute
import com.software.mywechat.feature.discovery.DiscoveryRoute
import com.software.mywechat.feature.splash.SplashRoute
import com.software.mywechat.feature.me.MeRoute
import kotlinx.coroutines.launch

@Composable
fun MainRoute(

){
    MainScreen(

    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(

){
    var currentDestination by rememberSaveable() {
        mutableStateOf(TopLevelDestination.SPLASH.route)
    }

    //协程作用域
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState{
            TopLevelDestination.entries.size
        }

        HorizontalPager(
            state= pagerState,
            userScrollEnabled = false,
            modifier= Modifier
                .weight(1f).fillMaxWidth()
        ) { page ->
            when(page){
                0 -> SplashRoute()
                1 -> AddressBookRoute()
                2 -> DiscoveryRoute()
                3 -> MeRoute()
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
