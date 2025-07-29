package com.software.mywechat.feature.main

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
import com.software.mywechat.core.design.component.MyNavigationBar
import com.software.mywechat.core.design.component.MyTopAppBar
import com.software.mywechat.feature.addressbook.AddressBookRoute
import com.software.mywechat.feature.discovery.DiscoveryRoute
import com.software.mywechat.feature.splash.SplashRoute
import com.software.mywechat.feature.me.MeRoute
import com.software.mywechat.ui.MyAppUiState
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    appUiState: MyAppUiState,
    toLogin:()->Unit,
    toRegister:()->Unit,
    toLoginHome:()->Unit,
    toNewFriend:()->Unit,
    toProfile:()->Unit,
){
    MainScreen(
        appUiState=appUiState,
        toLogin = toLogin,
        toRegister = toRegister,
        toLoginHome = toLoginHome,
        toNewFriend = toNewFriend,
        toProfile = toProfile,
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    appUiState: MyAppUiState,
    toLogin:()->Unit={},
    toRegister:()->Unit={},
    toLoginHome:()->Unit={},
    toNewFriend:()->Unit={},
    toProfile:()->Unit={},
){
    var currentDestination by rememberSaveable() {
        mutableStateOf(BottomLevelDestination.SPLASH.route)
    }

    val scope = rememberCoroutineScope()


//    val topLevelDestination = when (currentDestination) {
//        BottomLevelDestination.SPLASH.route -> TopLevelDestination.SPLASH
//        BottomLevelDestination.ADDRESS_BOOK.route -> TopLevelDestination.ADDRESS_BOOK
//        BottomLevelDestination.DISCOVERY.route -> TopLevelDestination.DISCOVERY
//        else -> null
//    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        if (topLevelDestination != null) {
//            MyTopAppBar(
//                currentDestination = topLevelDestination,
//                onSearchClick = {  },
//                onAddClick = { },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }

        val pagerState = rememberPagerState{
            BottomLevelDestination.entries.size
        }

        LaunchedEffect(pagerState.currentPage) {
            currentDestination = BottomLevelDestination.values()[pagerState.currentPage].route
        }


        HorizontalPager(
            state= pagerState,
            userScrollEnabled = true,
            modifier= Modifier
                .weight(1f).fillMaxWidth()
        ) { page ->
            when(page){
                0 -> SplashRoute()
                1 -> AddressBookRoute(
                    toNewFriend = toNewFriend,
                )
                2 -> DiscoveryRoute(toLogin)
                3 -> MeRoute(
                    toLoginHome  = toLoginHome,
                    toProfile = toProfile,
                )
            }
        }
        MyNavigationBar(
            destinations = BottomLevelDestination.entries,
            onNavigateToDestination = {
                currentDestination = BottomLevelDestination.values()[it].route
                scope.launch {
                    pagerState.scrollToPage(it)
                }
            },
            currentDestination = currentDestination,
            modifier = Modifier,
        )
    }
}
