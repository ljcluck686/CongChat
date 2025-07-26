package com.software.mywechat.feature.addFriend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.md_theme_dark_onBackground

@Composable
fun AddFriendRoute(
    toSearch:()->Unit,
    toBack:()->Unit,
){
    AddFriendScreen(
        toSearch = toSearch,
        toBack =toBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreen(
    toSearch: () -> Unit={},
    toBack: () -> Unit={},
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "添加好友"
            )
        }
    ){paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonColors(
                        containerColor = md_theme_dark_onBackground,
                        contentColor = Color.White,
                        disabledContainerColor = md_theme_dark_onBackground,
                        disabledContentColor = Color.White,
                    ),
                    onClick = toSearch
                ) {
                    Text("🔍 搜索 账号/手机号")
                }
            }
        }
    }
}
