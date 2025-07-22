package com.software.mywechat.feature.loginhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.software.mywechat.R

@Composable
fun LoginHomeRoute(
    toLogin: () -> Unit ,
    finishAllLoginPages: () -> Unit ,
){
    LoginHomeScreen(
        toLogin = toLogin,
        finishAllLoginPages = finishAllLoginPages
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginHomeScreen(
    toLogin: () -> Unit = {},
    finishAllLoginPages: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter =  painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = toLogin,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(bottom = 12.dp)
            ) {
                Text(text = "登录")
            }
            Button(
                onClick = toLogin,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(bottom = 12.dp)
            ) {
                Text(text = "注册")
            }
        }

    }
}