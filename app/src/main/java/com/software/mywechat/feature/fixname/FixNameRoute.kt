package com.software.mywechat.feature.fixname

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar

@Composable
fun FixNameRoute(
    toBack:()->Unit,
    ToProfile:()->Unit,
    finishAllLoginPages:()->Unit,
    viewModel: FixNameViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val name by viewModel.name.collectAsState()
    val msg by viewModel.msg.collectAsState()
    val isToProfile by viewModel.isToProfile.collectAsState()
    FixNameScreen(
        name = name,
        toBack = toBack,
        onValueChange = viewModel::onValueChange,
        onFixName = viewModel::onFixName,
    )
    LaunchedEffect(msg) {
        if (msg.isNotBlank()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(isToProfile) {
        if(isToProfile){
            finishAllLoginPages()
            ToProfile()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixNameScreen(
    name:String,
    toBack: () -> Unit,
    onValueChange: (String) -> Unit,
    onFixName: () -> Unit,
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "更改名字",
                actions = {
                    Button(
                        onClick = onFixName,
                        colors = ButtonDefaults.buttonColors(
                            Color.Green
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("保存")
                    }
                }
            )
        }
    ) {paddingValues ->
        var nickname by rememberSaveable {
            mutableStateOf(name)
        }

        TextField(
            value = nickname,
            onValueChange = {
                nickname = it
                onValueChange(it)
            },
            label = { Text("新昵称") },
            placeholder = {
                Text(text = "请输入新昵称")
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        )
    }
}
