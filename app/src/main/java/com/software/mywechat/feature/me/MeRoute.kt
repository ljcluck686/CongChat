package com.software.mywechat.feature.me

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.core.model.UserData
import com.software.mywechat.ui.MyAppUiState
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MeRoute(
    toLoginHome:()->Unit,
    viewModel: MeViewModel= hiltViewModel()
){
    val isLogin by viewModel.isLogin.collectAsState()

    MeScreen(
        loginOut=viewModel::loginOut,
    )
    if(isLogin){
        LaunchedEffect (true){
            toLoginHome()
        }
    }

}

@Composable
fun MeScreen(
    loginOut:()->Unit={},
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
                onClick = loginOut,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "退出登录")
            }
        }
    }
}