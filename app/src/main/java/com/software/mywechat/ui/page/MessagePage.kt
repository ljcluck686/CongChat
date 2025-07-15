package com.software.mywechat.ui.page

import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.PersonalVideo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.software.mywechat.R
import com.software.mywechat.data.CQMessage
import com.software.mywechat.data.messageList
import com.software.mywechat.view.CQDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagePage(){
    val context = LocalContext.current
    rememberSystemUiController()?.setStatusBarColor(Color.White, darkIcons = true)
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "微信",
                        maxLines = 1,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        /* doSomething() */
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                        )
                    }
                    IconButton(onClick = {
                        /* doSomething() */
                    }) {
                        Icon(
                            imageVector = Icons.Filled.AddCircleOutline,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                        )
                    }
                }
            )
        },
        content = { innerPadding  ->
            LazyColumn (
                contentPadding = innerPadding,
                //verticalArrangement = Arrangement.spacedBy(8.dp),
                state = scrollState
            ){
                item {
                    CQDivider()
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(40.dp, 0.dp, 35.dp, 0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PersonalVideo,
                                contentDescription = null,
                                modifier = Modifier.size(23.dp),
                                tint = Color(ContextCompat.getColor(context, R.color.gray))
                            )
                            Text(
                                text = "mac 微信已登陆",
                                color = Color(ContextCompat.getColor(context, R.color.gray)),
                                fontSize = 14.sp,
                                modifier = Modifier.padding(25.dp, 0.dp, 0.dp,0.dp)
                            )
                        }
                    }
                }
                item {
                    CQDivider()
                }
                items(messageList) {
                    it.let {
                        Column {
                            MessageItem(it = it, context)
                            HorizontalDivider(
                                modifier = Modifier.padding(60.dp, 0.dp,0.dp,0.dp),
                                thickness = 0.2.dp,
                                color = Color(ContextCompat.getColor(context, R.color.gray_10))
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    )

}

@Composable
fun MessageItem(it: CQMessage, context: Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(70.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(50.dp)) {
                Image(
                    painter = rememberCoilPainter(request = it.avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                )
            }
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxHeight()
                        .weight(3f)
                ) {
                    Text(
                        text = it.name,
                        fontSize = 17.sp,
                        color = Color(ContextCompat.getColor(context, R.color.black)),
                    )
                    Text(
                        text = it.message,
                        fontSize = 12.sp,
                        color = Color(ContextCompat.getColor(context, R.color.gray_10)),
                    )
                }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)) {
                    Text(
                        text = it.lastTime,
                        fontSize = 12.sp,
                        color = Color(ContextCompat.getColor(context, R.color.gray_10)),
                    )
                }
            }
        }
    }
}