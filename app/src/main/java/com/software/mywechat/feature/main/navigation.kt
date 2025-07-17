package com.software.mywechat.feature.main

import com.software.mywechat.R
import com.software.mywechat.feature.addressbook.ADDRESS_BOOK_ROUTE
import com.software.mywechat.feature.discovery.DISCOVERY_ROUTE
import com.software.mywechat.feature.me.ME_ROUTE
import com.software.mywechat.feature.splash.SPLASH_ROUTE

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val titleTextId: Int,
    val route: String,
) {
    SPLASH(
        selectedIcon = R.mipmap.icon_chat_n,
        unselectedIcon = R.mipmap.icon_chat_m,
        titleTextId = R.string.splash,
        route = SPLASH_ROUTE,
    ),
    ADDRESS_BOOK(
        selectedIcon = R.mipmap.icon_address_book_n,
        unselectedIcon = R.mipmap.icon_address_book_m,
        titleTextId = R.string.address_book,
        route = ADDRESS_BOOK_ROUTE,
    ),
    DISCOVERY(
        selectedIcon = R.mipmap.icon_discovery_n,
        unselectedIcon = R.mipmap.icon_discovery,
        titleTextId = R.string.discovery,
        route = DISCOVERY_ROUTE,
    ),

    ME(
        selectedIcon = R.mipmap.icon_me_n,
        unselectedIcon = R.mipmap.icon_me_m,
        titleTextId = R.string.me,
        route = ME_ROUTE,
    ),
}