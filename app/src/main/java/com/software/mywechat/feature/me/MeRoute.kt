package com.software.mywechat.feature.me

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.software.mywechat.core.model.UserData
import com.software.mywechat.ui.MyAppUiState

@Composable
fun MeRoute(
    appUiState: MyAppUiState,
    toLogin:()->Unit,
    toRegister:()->Unit,
){
    val isLogin by appUiState.isLogin.collectAsState()
    val userData by appUiState.userData.collectAsState()
    MeScreen(
        isLogin = isLogin,
        userData = userData,
        toLogin = toLogin,
        toRegister = toRegister,
    )
}

@Composable
fun MeScreen(
    isLogin: Boolean = false,
    userData: UserData = UserData(),
    toLogin:()->Unit={},
    toRegister:()->Unit={},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = toLogin,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "登录")
            }

            Button(
                onClick = toRegister,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "注册")
            }

        }
    }
}