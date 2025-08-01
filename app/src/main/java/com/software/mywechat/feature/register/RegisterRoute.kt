package com.software.mywechat.feature.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.SpaceLarge
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer
import com.software.mywechat.core.extension.clickableNoRipple
import com.software.mywechat.core.model.User

const val WECHAT_AGREEMENT_TEXT = "《微信软件许可及服务协议》"

@Composable
fun RegisterRoute(
    toBack:()->Unit,
    viewModel: RegisterViewModel = hiltViewModel()
){
    val data by viewModel.data.collectAsState()
    RegisterScreen(
        data = data,
        toBack = toBack,
        onValueChange = viewModel::onValueChange,
        onRegisterClick = viewModel::onRegisterClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    data: User = User(),
    toBack:()->Unit={},
    onValueChange:(User)->Unit={},
    onRegisterClick:()->Unit={},
    onImageUpload: (String) -> Unit = {}
){
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "立即注册",
                colors = TopAppBarDefaults.topAppBarColors(
                    md_theme_light_errorContainer,
                ),
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        var imageUrl by remember { mutableStateOf(data.avatar) }
        val context = LocalContext.current
        Column(
            verticalArrangement = Arrangement.spacedBy(SpaceLarge),
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickableNoRipple {
                    keyboardController?.hide()
                }
                .padding(paddingValues)
                .padding(start = SpaceLarge, end = SpaceLarge, top = SpaceLarge)
        ) {
            // 居中的圆形头像区域
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable {
                            val sampleUrl = "https://chan-xin.oss-cn-beijing.aliyuncs.com/chan_xin/image/1752555249209.jpg"
                            imageUrl = sampleUrl
                            onImageUpload(sampleUrl)
                        },
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = CircleShape
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (imageUrl.isNotEmpty()) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "用户头像",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            // 没有图片时显示默认图标
                            Icon(
                                imageVector = Icons.Default.AddAPhoto,
                                contentDescription = "添加头像",
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.Center),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        // 右上角添加相机图标
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = 8.dp, y = 8.dp)
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddAPhoto,
                                contentDescription = "拍照",
                                modifier = Modifier.fillMaxSize(),
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // 表单字段
            TextField(
                value = data.nickname,
                onValueChange = { onValueChange(data.copy(nickname = it)) },
                label = { Text(text = "昵称") },
                placeholder = { Text(text = "请输入昵称") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = data.phone,
                onValueChange = { onValueChange(data.copy(phone = it)) },
                label = { Text(text = "电话") },
                placeholder = { Text(text = "请输入电话") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = data.password,
                onValueChange = { onValueChange(data.copy(password = it)) },
                label = { Text(text = "密码") },
                placeholder = { Text(text = "请输入密码") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "注册")
            }

        }
    }
}