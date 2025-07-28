package com.software.mywechat.feature.newfriend

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.ui.myComposable

const val NEW_FRIEND_ROUTE = "new_friend"

fun NavController.navigationToNewFriend():Unit{
    navigate(NEW_FRIEND_ROUTE)
}

fun NavGraphBuilder.newFriendScreen(
    toBack:()->Unit,
    toAddFriend:()->Unit,
    toSearch:()->Unit,
): Unit {
    myComposable(NEW_FRIEND_ROUTE) {
        NewFriendRoute(
            toBack = toBack,
            toAddFriend = toAddFriend,
            toSearch = toSearch,
        )
    }
}