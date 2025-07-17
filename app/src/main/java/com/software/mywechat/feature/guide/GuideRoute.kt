package com.software.mywechat.feature.guide

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

@Composable
fun GuideRoute(
    toMain:()->Unit,
){
    val viewModel:GuideViewModel = viewModel()
    val navigateToMain by viewModel.navigateToMain.collectAsState()
    val timeLeft by viewModel.timeLeft.collectAsState()
    GuideScreen()
    if(navigateToMain){
        LaunchedEffect(true) {
            toMain()
        }
    }

}

@Composable
fun GuideScreen(
    toMain:()->Unit={},
){
    Image(
        painter =  painterResource(id = R.drawable.bg_splash),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}