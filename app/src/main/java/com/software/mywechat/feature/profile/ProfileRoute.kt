package com.software.mywechat.feature.profile

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cn.qhplus.emo.photo.activity.PhotoPickerActivity
import cn.qhplus.emo.photo.activity.getPhotoPickResult
import cn.qhplus.emo.photo.coil.CoilMediaPhotoProviderFactory
import com.software.mywechat.MyAppState
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.CQDivider
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.feature.me.AvatarImage
import com.software.mywechat.ui.MyApp

@Composable
fun ProfileRoute(
    toBack:()->Unit,
    toFixName:()->Unit,
    viewModel: ProfileViewModel = hiltViewModel()
){
    val avatarPathFlow by viewModel.avatarPathFlow.collectAsStateWithLifecycle("")
    val context = LocalContext.current
    val pickLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    val data by viewModel.data.collectAsState()
    ProfileScreen(
        data = data,
        avatarPathFlow = avatarPathFlow,
        toBack = toBack,
        toFixName = toFixName,
        onSelectImageClick = {
            pickLauncher.launch(
                PhotoPickerActivity.intentOf(
                    context,
                    enableOrigin = false,
                    pickLimitCount = 1,
                    factoryCls = CoilMediaPhotoProviderFactory::class.java // 可自定义图片加载器
                )
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    data:UserInfo,
    avatarPathFlow:String?,
    toBack: () -> Unit={},
    toFixName: () -> Unit = {},
    onSelectImageClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "个人资料",
                colors = TopAppBarColors(
                    containerColor = md_theme_light_errorContainer,
                    scrolledContainerColor = md_theme_light_errorContainer,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor =  Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable(onClick = onSelectImageClick)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.photo),
                    fontSize = 17.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f)
                )
                AvatarImage(avatarPath = avatarPathFlow)
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.Gray
                )
            }
            CQDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable(onClick = toFixName)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.nickname),
                    fontSize = 17.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f)
                )
                Text(
                    text = MyAppState.userName,
                    fontSize = 17.sp,
                    color = Color.Black,
                )
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.Gray
                )
            }
            CQDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable(onClick = {})
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.phone),
                    fontSize = 17.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f)
                )
                Text(
                    text = MyAppState.phone,
                    fontSize = 17.sp,
                    color = Color.Black,
                )
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.Gray
                )
            }
            CQDivider()
        }
    }
}


