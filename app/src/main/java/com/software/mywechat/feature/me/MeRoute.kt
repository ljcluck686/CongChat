package com.software.mywechat.feature.me

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MeRoute(
    toLogin:()->Unit,
    toRegister:()->Unit,
){
    MeScreen(
        toLogin = toLogin,
        toRegister = toRegister,
    )
}

@Composable
fun MeScreen(
    toLogin:()->Unit={},
    toRegister:()->Unit={},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = toLogin,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "登录")
            }

            Button(
                onClick = toRegister,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "注册")
            }
        }
    }
}