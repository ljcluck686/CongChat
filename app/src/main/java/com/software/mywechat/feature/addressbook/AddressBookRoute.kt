package com.software.mywechat.feature.addressbook


import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.*


@Composable
fun AddressBookRoute(
    toNewFriend:()->Unit,
    viewModel: AddresBookViewModel = hiltViewModel()

){
    val datum by viewModel.datum.collectAsState()
    AddressBookScreen(
        toNewFriend = toNewFriend,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBookScreen(
    toNewFriend:()->Unit={},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MyCenterTopAppBar(
                title = "通讯录",
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.AddCircleOutline,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = md_theme_light_errorContainer,
                    scrolledContainerColor = md_theme_light_errorContainer,
                    navigationIconContentColor = md_theme_light_errorContainer,
                    titleContentColor = Color.Black,
                    actionIconContentColor =  Color.Black
                )

            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ContactList(
                    listState = listState,
                    toNewFriend = toNewFriend,
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactList(
    listState: LazyListState,
    toNewFriend:()->Unit={},
) {
    LazyColumn(state = listState) {
        // 特殊联系人区域
        item {
            SpecialContactItem(
                iconRes = R.mipmap.icon_new_friend,
                text = "新的朋友",
                onClick = toNewFriend,
            )
        }
        item {
            SpecialContactItem(
                iconRes = R.mipmap.icon_new_friend,
                text = "仅聊天的朋友",
                onClick = {}
            )
        }
        item {
            SpecialContactItem(
                iconRes = R.mipmap.icon_new_friend,
                text = "群聊",
                onClick = {}
            )
        }
        item {
            SpecialContactItem(
                iconRes = R.mipmap.icon_new_friend,
                text = "标签",
                onClick = {}
            )
        }
        item {
            SpecialContactItem(
                iconRes = R.mipmap.icon_new_friend,
                text = "服务号",
                onClick = {}
            )
        }

        // 分隔线
        item {
            HorizontalDivider(
                modifier = Modifier
                    .height(8.dp)
                    .background(Color(0xFFF5F5F5))
            )
        }

    }
}

@Composable
fun SpecialContactItem(iconRes: Int, text: String,onClick:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp)
            .clickable(onClick =onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(36.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(start = 52.dp),
        thickness = 0.5.dp,
        color = Color(0xFFF0F0F0)
    )
}

