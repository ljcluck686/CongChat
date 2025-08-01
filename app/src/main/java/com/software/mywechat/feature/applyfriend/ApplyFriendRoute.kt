package com.software.mywechat.feature.applyfriend

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.SpaceExtraSmall2

@Composable
fun ApplyFriendRoute(
    toBack:()->Unit,
    toAddressBook:()->Unit,
    finsh:()->Unit,
    viewModel: ApplyFriendViewModel = hiltViewModel(),
){
    val tx by viewModel.tx.collectAsState()
    val msg by viewModel.msg.collectAsState()
    ApplyFriendScreen(
        tx = tx,
        toBack = toBack,
        onApplyFriend = viewModel::onApplyFriend,
    )
    val context = LocalContext.current
    LaunchedEffect(msg){
        if(msg.isNotBlank()){
            finsh()
            toAddressBook()
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.cle(msg)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyFriendScreen(
    tx:String="",
    toBack:()->Unit,
    onApplyFriend:(String)->Unit,
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                toBack = toBack,
                title = "申请添加好友"
            )
        }
    ) { paddingValues ->
        var ts by rememberSaveable {
            mutableStateOf(tx)
        }
        Column(
            modifier = Modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            TextField(
                value = ts,
                onValueChanged = {ts = it}
            )
            Button(onClick = {
                onApplyFriend(ts)
            }) {
                Text("发送")
            }
        }

    }
}

@Composable
fun TextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var focused by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value,
        onValueChanged,
        trailingIcon = {
            if (focused && value.isNotEmpty()) {
                IconButton(onClick = {
                    onValueChanged("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(SpaceExtraSmall2)
                    )
                }
            }
        },
        label = { Text("打招呼内容") },
        placeholder = {
            Text(text = "请输入打招呼内容")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                focused = it.isFocused
            }
    )
}
