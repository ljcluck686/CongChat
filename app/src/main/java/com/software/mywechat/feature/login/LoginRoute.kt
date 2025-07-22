package com.software.mywechat.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.mywechat.R
import com.software.mywechat.core.design.component.MyCenterTopAppBar
import com.software.mywechat.core.design.theme.SpaceExtraSmall2
import com.software.mywechat.core.design.theme.SpaceLarge
import com.software.mywechat.core.extension.clickableNoRipple

@Composable
fun LoginRoute(
    toBack: () -> Unit,
    toRegister: () -> Unit,
    toMain: () -> Unit,
    finishAllLoginPages:()->Unit,
    viewModel: LoginViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    LoginScreen(
        uiState = uiState,
        toBack = toBack,
        toRegister = toRegister,
        loginClick = viewModel::onLoginClick,
    )
    if(uiState == LoginUiState.Success){
        LaunchedEffect(true) {
            toMain()
//            finishAllLoginPages()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    uiState: LoginUiState = LoginUiState.None,
    toBack: () -> Unit = {},
    toRegister: () -> Unit = {},
    loginClick: (String, String) -> Unit = { _, _ -> },
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        val keyboardController = LocalSoftwareKeyboardController.current
        var username by rememberSaveable {
            mutableStateOf("1234567890")
        }
        var password by rememberSaveable { mutableStateOf("@Ljc13832598063") }
        Scaffold(
            topBar = {
                MyCenterTopAppBar(
                    toBack = toBack,
                    title = stringResource(id = R.string.login),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Column(
                verticalArrangement = Arrangement.spacedBy(SpaceLarge),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .clickableNoRipple {
                        keyboardController?.hide()
                    }
                    .padding(paddingValues)
                    .padding(start = SpaceLarge, end = SpaceLarge, top = SpaceLarge)
            ) {
                UserTextField(
                    value = username,
                    onValueChanged = { username = it },
                )
                PasswordTextField(
                    value = password,
                    onValueChanged = { password = it },
                    loginClick = {
                        keyboardController?.hide()
                        loginClick(username, password)
                    },
                )
                Button(
                    onClick = {
                        keyboardController?.hide()
                        loginClick(username, password)
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.login))
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    TextButton(
                        onClick = toRegister,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.register),
                        )
                    }

                    TextButton(
                        onClick = {},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.forgot_password),
                        )
                    }
//                    when(val uiState = uiState){
//                        is LoginUiState.Success->{
//                            Text(text = "太棒了")
//                        }
//                        else ->{
//
//                        }
//                    }
                }

            }
        }
    }
}

@Composable
fun UserTextField(
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
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.input_username),
                contentDescription = null
            )
        },
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
        label = { Text(stringResource(id = R.string.phone)) },
        placeholder = {
            Text(text = stringResource(id = R.string.enter_phone))
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

@Composable
fun PasswordTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    loginClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value,
        onValueChanged,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.input_password),
                contentDescription = null
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(SpaceExtraSmall2)
                    )
                }
            }
        },
        label = { Text(stringResource(id = R.string.password)) },
        placeholder = {
            Text(text = stringResource(id = R.string.enter_password))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                loginClick()
            }
        ),
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        modifier = modifier.fillMaxWidth()
    )
}