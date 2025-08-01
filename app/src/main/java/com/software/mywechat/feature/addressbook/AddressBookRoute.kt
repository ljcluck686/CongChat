package com.software.mywechat.feature.addressbook

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.R
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer
import com.software.mywechat.core.model.FriendList
import com.software.mywechat.core.model.User
import com.software.mywechat.util.PinyinUtils
import kotlinx.coroutines.launch
import net.sourceforge.pinyin4j.PinyinHelper

@Composable
fun AddressBookRoute(
    toNewFriend: () -> Unit,
    viewModel: AddresBookViewModel = hiltViewModel()
) {
    val datum by viewModel.datum.collectAsState()
    AddressBookScreen(
        datum = datum,
        toNewFriend = toNewFriend,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBookScreen(
    datum: FriendList,
    toNewFriend: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // 对联系人列表按首字母排序
    val sortedFriends = remember(datum) {
        datum.friendList.sortedBy { friend ->
            PinyinUtils.getFirstLetter(friend.nickname)
        }
    }

    // 按首字母分组
    val groupedFriends = remember(sortedFriends) {
        sortedFriends.groupBy { friend ->
            val firstLetter = PinyinUtils.getFirstLetter(friend.nickname)
            if (firstLetter.isNotEmpty() && firstLetter[0].isLetter()) {
                firstLetter[0].uppercase()
            } else {
                "#"
            }
        }
    }

    // 定义固定的侧边栏 sections 列表
    val fullSidebarSections = listOf(
        "↑", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("通讯录", color = Color.Black) },
                actions = {
                    IconButton(onClick = { /* 搜索功能 */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "搜索",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { /* 添加联系人 */ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "添加",
                            tint = Color.Black
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // 联系人列表占据主要空间
                    ContactList(
                        modifier = Modifier.weight(1f),
                        listState = listState,
                        groupedFriends = groupedFriends,
                        // 这里传入所有可能的 section 用于构建列表头部，方便计算滚动位置
                        sectionHeaders = fullSidebarSections.filter { it != "↑" },
                        toNewFriend = toNewFriend,
                    )

                    // 侧边栏放在右侧，使用固定的 fullSidebarSections
                    SideBar(
                        sections = fullSidebarSections,
                        onSectionSelected = { section ->
                            coroutineScope.launch {
                                if (section == "↑") {
                                    listState.scrollToItem(0)
                                    return@launch
                                }

                                // 计算目标位置，重新实现：先算特殊联系人区域（5 项） + 分隔线（1 项）
                                var index = 5 + 1
                                for (header in fullSidebarSections.filter { it != "↑" }) {
                                    if (header == section) {
                                        // 如果当前分组有联系人，加上分组标题（1 项）和联系人数量
                                        if (groupedFriends.containsKey(header)) {
                                            index += 1 + (groupedFriends[header]?.size ?: 0)
                                        }
                                        break
                                    } else {
                                        // 如果是其他分组，不管有没有联系人，都加上分组标题（1 项）
                                        index += 1
                                    }
                                }

                                // 滚动到目标位置，注意索引不能越界，可适当处理，这里简单处理直接滚动
                                listState.scrollToItem(index.coerceAtMost(listState.layoutInfo.totalItemsCount - 1))
                            }
                        }
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    groupedFriends: Map<String, List<User>>,
    sectionHeaders: List<String>,
    toNewFriend: () -> Unit = {},
) {
    LazyColumn(modifier = modifier, state = listState) {
        // 特殊联系人区域
        item { SpecialContactItem(R.mipmap.icon_new_friend, "新的朋友", onClick = toNewFriend) }
        item { SpecialContactItem(R.mipmap.icon_new_friend, "仅聊天的朋友", onClick = {}) }
        item { SpecialContactItem(R.mipmap.icon_new_friend, "群聊", onClick = {}) }
        item { SpecialContactItem(R.mipmap.icon_new_friend, "标签", onClick = {}) }
        item { SpecialContactItem(R.mipmap.icon_new_friend, "服务号", onClick = {}) }

        // 分隔线
        item {
            HorizontalDivider(
                modifier = Modifier
                    .height(8.dp)
                    .background(Color(0xFFF5F5F5))
            )
        }

        // 按字母分组的联系人列表，遍历完整字母列表
        sectionHeaders.forEach { section ->
            // 只有分组存在联系人时，才渲染分组头部和联系人列表
            if (groupedFriends.containsKey(section)) {
                stickyHeader { SectionHeader(section) }
                items(groupedFriends[section] ?: emptyList()) { friend ->
                    ContactItem(
                        avatarRes = R.drawable.input_username,
                        name = friend.nickname,
                        isLastItem = friend == groupedFriends[section]?.last(),
                        onClick = {  }
                    )
                }
            }
        }
    }
}

@Composable
fun SpecialContactItem(iconRes: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(start = 52.dp),
        thickness = 0.5.dp,
        color = Color(0xFFF0F0F0)
    )
}

@Composable
fun SectionHeader(section: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color(0xFFF5F5F5))
            .padding(start = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = section,
            fontSize = 14.sp,
            color = Color(0xFF888888)
        )
    }
}

@Composable
fun ContactItem(avatarRes: Int, name: String, isLastItem: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = name,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
    if (!isLastItem) {
        HorizontalDivider(
            modifier = Modifier.padding(start = 52.dp),
            thickness = 0.5.dp,
            color = Color(0xFFF0F0F0)
        )
    }
}

@Composable
fun SideBar(sections: List<String>, onSectionSelected: (String) -> Unit) {
    val density = LocalDensity.current
    var sideBarHeight by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(24.dp)
            .background(Color.White)
            .onGloballyPositioned { coordinates ->
                sideBarHeight = with(density) { coordinates.size.height.toDp() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        sections.forEach { section ->
            Text(
                text = section,
                fontSize = 12.sp,
                color = Color(0xFF666666),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { onSectionSelected(section) }
                    .padding(vertical = 1.dp)
            )
        }
    }
}

