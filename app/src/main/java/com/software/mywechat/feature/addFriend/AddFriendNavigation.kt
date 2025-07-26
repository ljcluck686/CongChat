package com.software.mywechat.feature.addFriend

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.addressbook.AddressBookRoute
import com.software.mywechat.ui.myComposable

const val ADD_FRIEND_ROUTE = "add_friend"

fun NavController.navigationToAddFriend():Unit{
    navigate(ADD_FRIEND_ROUTE)
}

fun NavGraphBuilder.addFriendScreen(
    toSearch:()->Unit,
    toBack:()->Unit,
): Unit {
    myComposable(ADD_FRIEND_ROUTE) {
        AddFriendRoute(
            toSearch = toSearch,
            toBack = toBack
        )
    }
}