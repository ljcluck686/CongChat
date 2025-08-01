package com.software.mywechat.feature.addressbook

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.software.mywechat.feature.applyfriend.APPLY_FRIEND_ROUTE
import com.software.mywechat.feature.guide.GUIDE_ROUTE
import com.software.mywechat.ui.myComposable

const val ADDRESS_BOOK_ROUTE = "address_book"

fun NavController.navigationToAddressBook():Unit{
    navigate(ADDRESS_BOOK_ROUTE)
}

fun NavGraphBuilder.addressBookScreen(
    toNewFriend:()->Unit,
): Unit {
    myComposable(ADDRESS_BOOK_ROUTE) {
        AddressBookRoute(
            toNewFriend = toNewFriend,
        )
    }
}