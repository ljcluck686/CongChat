package com.software.mywechat.feature.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer

@Composable
fun SplashRoute(

){
    SplashScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(

) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                title = "葱信",
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.AddCircleOutline,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = md_theme_light_errorContainer,
                    scrolledContainerColor = md_theme_light_errorContainer,
                    navigationIconContentColor = md_theme_light_errorContainer,
                    titleContentColor = Color.Black,
                    actionIconContentColor =  Color.Black
                )

            )
        }
    ){paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = "主页面")
        }
    }

}