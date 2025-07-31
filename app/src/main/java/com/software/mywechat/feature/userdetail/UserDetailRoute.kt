package com.software.mywechat.feature.userdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.CQDivider
import com.software.mywechat.core.design.theme.SpaceExtraSmallHeight
import com.software.mywechat.core.design.theme.SpaceMediumHeight
import com.software.mywechat.core.design.theme.md_theme_dark_error
import com.software.mywechat.core.design.theme.md_theme_light_surface
import com.software.mywechat.core.model.User

@Composable
fun UserDetailRoute(
    toBack:()->Unit,
    toApplyFriend:(String)->Unit,
    viewModel:UserDetailViewModel = hiltViewModel()
){
    val data by viewModel.data.collectAsState()
    UserDetailScreen(
        data = data,
        toBack = toBack,
        toApplyFriend = toApplyFriend,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    data : User,
    toBack: () -> Unit={},
    toApplyFriend: (String) -> Unit={},
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Default.Dehaze,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues).background(md_theme_light_surface)
        ){
            Row(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .size(75.dp)
                ) {
                    AsyncImage(
                        model = data.avatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(6.dp))
                    )
                }
                Column(
                    modifier = Modifier.padding(top = 25.dp, start = 15.dp)
                ){
                    Text(
                        text = data.nickname,
                        fontSize = 35.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "地区：河北 唐山",
                        fontSize = 15.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            CQDivider()

            Row(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                    ){
                        Text(
                            text = "朋友资料",
                            fontSize = 17.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .weight(1f)
                        )
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp),
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                    ){
                        Text(
                            text = "电话",
                            fontSize = 17.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = data.phone,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 25.dp, top = 5.dp),
                            color = Color.Blue
                        )
                    }
                }
            }

            SpaceMediumHeight()

            Row(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .clickable(onClick = {
                        toApplyFriend(data.id)
                    }),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "添加到通讯录",
                    color = md_theme_dark_error,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
