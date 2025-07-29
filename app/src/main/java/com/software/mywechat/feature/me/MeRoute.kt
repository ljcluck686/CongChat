package com.software.mywechat.feature.me

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.core.design.theme.md_theme_light_arrow
import com.software.mywechat.core.extension.clickableNoRipple
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.util.ImageUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun MeRoute(
    toLoginHome:()->Unit,
    toProfile:()->Unit,
    viewModel: MeViewModel= hiltViewModel()
){
    val isLogin by viewModel.isLogin.collectAsState()
    val data by viewModel.data.collectAsState()
    val avatarPath by viewModel.avatarPathFlow.collectAsState(initial = null)
    MeScreen(
        data = data,
        avatarPath = avatarPath,
        loginOut=viewModel::loginOut,
        toProfile = toProfile,
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
    avatarPath: String?,
    loginOut: () -> Unit = {},
    toProfile: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(md_theme_light_arrow),
        ) {
            TopProfileSection(
                data = data,
                avatarPath = avatarPath,
                toProfile = toProfile,
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
    avatarPath: String?,
    toProfile:()->Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickableNoRipple(onClick = toProfile)
            .padding(top = 50.dp,start=10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AvatarImage(avatarPath = avatarPath)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "用户名: ${MyAppState.userName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "微信号: ${MyAppState.userId}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.QrCode,
                contentDescription = "QR Code",
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun AvatarImage(avatarPath: String?) {
    val context = LocalContext.current
    // 存储加载后的bitmap，使用remember确保重组时不重复加载
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(avatarPath) {
        if (avatarPath.isNullOrEmpty()) {
            bitmap = null
            return@LaunchedEffect
        }
        isLoading = true
        val result = withContext(Dispatchers.IO) {
            try {
                val fileName = File(avatarPath).name
                ImageUtils.loadLocalImage(context, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        bitmap = result
        isLoading = false
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "加载中", fontSize = 12.sp)
            }
        }
        bitmap != null -> {
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = "用户头像",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        else -> {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "默认", fontSize = 14.sp)
            }
        }
    }
}