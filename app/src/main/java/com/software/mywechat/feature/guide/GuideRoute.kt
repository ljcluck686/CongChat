package com.software.mywechat.feature.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.software.mywechat.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale

@Composable
fun GuideRoute(){
    GuideScreen()
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