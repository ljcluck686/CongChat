package com.software.mywechat.feature.newfriend

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.CQDivider
import com.software.mywechat.core.design.theme.md_theme_dark_onBackground
import com.software.mywechat.core.design.theme.md_theme_light_divider
import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyResp

@Composable
fun NewFriendRoute(
    toBack: () -> Unit,
    toAddFriend: () -> Unit,
    toSearch: () -> Unit,
    viewModel: NewFriendViewModel = hiltViewModel()
) {
    val datum by viewModel.datum.collectAsState()
    NewFriendScreen(
        datum = datum,
        toBack = toBack,
        toAddFriend = toAddFriend,
        toSearch =toSearch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFriendScreen(
    datum: DataListWrapper,
    toBack: () -> Unit = {},
    toAddFriend: () -> Unit = {},
    toSearch: () -> Unit = {},
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

            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = md_theme_light_divider)
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "好友申请列表",
                    fontSize = 18.sp,
                    color = Color.Black,
                )
            }

            LazyColumn (
                modifier = Modifier.fillMaxWidth()
            ){
                items(datum.list){it->
                    ApplyContent(it)
                }
            }


        }
    }
}

@Composable
fun ApplyContent(friendApplyResp: FriendApplyResp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
        ) {
            AsyncImage(
                model = friendApplyResp.avatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(6.dp))
            )
        }
        Column (

        ){
            Text(
                text =  friendApplyResp.nickname,
                fontSize = 17.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 15.dp,top = 5.dp)
                    .weight(1f)
            )
            Text(
                text =  friendApplyResp.greetMsg,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(1f)
            )
        }
    }
    CQDivider()
}
