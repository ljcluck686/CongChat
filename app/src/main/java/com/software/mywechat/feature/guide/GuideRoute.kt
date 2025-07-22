package com.software.mywechat.feature.guide

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import com.software.mywechat.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.ui.MyAppUiState

@Composable
fun GuideRoute(
    appUiState : MyAppUiState,
    toMain:()->Unit,
    toLoginHome:()->Unit,
){
    val viewModel:GuideViewModel = viewModel()
    val navigateToNext by viewModel.navigateToNext.collectAsState()
    val isLogin by appUiState.isLogin.collectAsState()
    GuideScreen()
    if(navigateToNext){
        LaunchedEffect(true) {
            Log.d("congcong", "GuideRoute: appUiState.isLogin.value")
            if(isLogin){
                toMain()
            }
            else{
                toLoginHome()
            }
        }
    }

}

@Composable
fun GuideScreen(
){
    Image(
        painter =  painterResource(id = R.drawable.bg_splash),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}