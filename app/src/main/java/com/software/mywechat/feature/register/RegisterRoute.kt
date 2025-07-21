package com.software.mywechat.feature.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    data: User = User("","","","","",0),
    toBack:()->Unit={},
    onValueChange:(User)->Unit={},
    onRegisterClick:()->Unit={},
){
    val keyboardController = LocalSoftwareKeyboardController.current
    data.copy(avatar="https://chan-xin.oss-cn-beijing.aliyuncs.com/chan_xin/image/1752318169872")
    data.copy(sex = 0)
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
            data.nickname?.let {
                TextField(
                    value = it,
                    onValueChange = {
                        onValueChange(data.copy(nickname = it))
                    },
                    label = { Text(text = "昵称") },
                    placeholder = { Text(text = "请输入昵称") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            TextField(
                value = data.phone,
                onValueChange = {
                    onValueChange(data.copy(phone = it))
                },
                label = { Text(text = "电话") },
                placeholder = { Text(text = "请输入电话") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            TextField(
                value = data.password,
                onValueChange = {

                    onValueChange(data.copy(password = it))
                },
                label = { Text(text = "密码") },
                placeholder = { Text(text = "请输入密码") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "注册")
            }
        }

    }
}