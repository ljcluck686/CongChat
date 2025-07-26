package com.software.mywechat.feature.discovery

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.CQDivider
import com.software.mywechat.core.design.theme.SpaceExtraMediumHeight
import com.software.mywechat.core.design.theme.SpaceExtraSmallHeight
import com.software.mywechat.core.design.theme.SpaceMediumHeight
import com.software.mywechat.core.design.theme.SpaceSmall
import com.software.mywechat.core.design.theme.SpaceSmallHeight
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer
import com.software.mywechat.core.design.theme.md_theme_light_outlineVariant


@Composable
fun DiscoveryRoute(
    toLogin:()->Unit,
){
    DiscoveryScreen(
        toLogin = toLogin
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryScreen(
    toLogin:()->Unit={},
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                title = "发现",
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(md_theme_light_outlineVariant),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            LinerContent(R.mipmap.pengyouquan,R.string.pengyouquan)
            SpaceExtraMediumHeight()
            LinerContent(R.mipmap.shipinhao,R.string.shipinhao)
            CQDivider()
            LinerContent(R.mipmap.saoyisao,R.string.saoyisao,toLogin)

        }

    }
}

@Composable
fun LinerContent(
    iconLeft: Int,
    title: Int,
    toLogin:()->Unit={},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = toLogin)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
        ) {
            Image(
                painter = painterResource(id = iconLeft),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(6.dp))
            )
        }
        Text(
            text = stringResource(id = title),
            fontSize = 17.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 15.dp)
                .weight(1f)
        )
        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(35.dp),
            tint = Color.Gray
        )
    }
}
