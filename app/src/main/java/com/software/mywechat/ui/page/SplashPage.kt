package com.software.mywechat.ui.page


import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import com.software.mywechat.R

@Composable
fun SplashPage(onSplashCompleted: () ->Unit){
    rememberSystemUiController().setStatusBarColor(Color.Black, darkIcons = true)

    Surface(
        Modifier
            .fillMaxSize()
    ) {
        Box( modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)) {
            LaunchedEffect(Unit) {
                delay(1000)
                onSplashCompleted()
            }
            Image(
                painter = painterResource(id =R.drawable.bg_splash),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}