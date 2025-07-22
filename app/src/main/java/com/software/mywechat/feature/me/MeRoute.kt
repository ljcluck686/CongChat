package com.software.mywechat.feature.me

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.software.mywechat.R
import com.software.mywechat.core.design.theme.md_theme_light_arrow
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo

@Composable
fun MeRoute(
    toLoginHome:()->Unit,
    viewModel: MeViewModel= hiltViewModel()
){
    val isLogin by viewModel.isLogin.collectAsState()
    val data by viewModel.data.collectAsState()
    MeScreen(
        data = data,
        loginOut=viewModel::loginOut
    )
    if(isLogin){
        LaunchedEffect (true){
            toLoginHome()
        }
    }

}

@Composable
fun MeScreen(
    data: UserInfo,
    loginOut: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(md_theme_light_arrow),
        ) {
            TopProfileSection(
                data = data
            )
            Button(
                onClick = loginOut,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "退出登录")
            }
        }
    }
}

@Composable
fun TopProfileSection(
    data: UserInfo,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 50.dp,start=10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://chan-xin.oss-cn-beijing.aliyuncs.com/chan_xin/image/1752555249209.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = data.nickname!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "微信号: codeforces888",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            // 右侧二维码
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.QrCode,
                contentDescription = "QR Code",
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.padding(start = 60.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = {  },
                modifier = Modifier.height(32.dp)
            ) {
                Text(text = "+ 状态", fontSize = 12.sp)
            }
            IconButton(
                onClick = {  },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}