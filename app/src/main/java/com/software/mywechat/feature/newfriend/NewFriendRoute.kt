package com.software.mywechat.feature.newfriend

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.feature.addressbook.AddresBookViewModel
import com.software.mywechat.feature.addressbook.AddressBookScreen

@Composable
fun NewFriendRoute(
    toBack: () -> Unit,
    toAddFriend: () -> Unit,
    viewModel: NewFriendViewModel = hiltViewModel()
) {
    val datum by viewModel.datum.collectAsState()
    NewFriendScreen(
        toBack = toBack,
        toAddFriend = toAddFriend,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFriendScreen(
    toBack: () -> Unit = {},
    toAddFriend: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "新的朋友",
                actions = {
                    Text(
                        text = "添加朋友",
                        Modifier.clickable(onClick = toAddFriend)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}
