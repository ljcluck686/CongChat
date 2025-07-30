package com.software.mywechat.feature.setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.CQDivider
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer

@Composable
fun SettingRoute(
    toBack:()->Unit,
    toLoginHome:()->Unit,
    toProfile:()->Unit,
    viewModel: SettingViewModel = hiltViewModel()
){
    val isLogin by viewModel.isLogin.collectAsState()
    SettingScreen(
        toBack = toBack,
        toLoginHome = {
            viewModel.loginOut()
            toLoginHome()
        },
        toProfile = toProfile
    )
    if(isLogin){
        LaunchedEffect (true){
            toLoginHome()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    toBack: () -> Unit,
    toLoginHome: () -> Unit,
    toProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "设置"
            )
        }
    ) { paddingValues ->
        val state = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(md_theme_light_errorContainer)
                .verticalScroll(state),

        ) {
            LinerLot(R.string.gerenziliao,toProfile)
            CQDivider()
            LinerLot(R.string.zhanghaoanquan,)
            Spacer(modifier = Modifier.height(8.dp))
            LinerLot(R.string.wiechengnianmoshi)
            CQDivider()
            LinerLot(R.string.guanhuai)
            Spacer(modifier = Modifier.height(8.dp))
            LinerLot(R.string.tongzhi)
            CQDivider()
            LinerLot(R.string.chat)
            CQDivider()
            LinerLot(R.string.tongyong)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "隐私",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f)
                )
            }
            LinerLot(R.string.pengyuoquanxian)
            CQDivider()
            LinerLot(R.string.gerenxinxiquanxian)
            CQDivider()
            LinerLot(R.string.gerenxinxiqingdan)
            CQDivider()
            LinerLot(R.string.disanfang)
            Spacer(modifier = Modifier.height(8.dp))
            LinerLot(R.string.plugin)
            Spacer(modifier = Modifier.height(8.dp))
            LinerLot(R.string.guanyu)
            CQDivider()
            LinerLot(R.string.help)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = toLoginHome)
                    .background(Color.White)
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "退出",
                    fontSize = 17.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
private fun LinerLot(
    title:Int,
    onClick: () -> Unit={}

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
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
