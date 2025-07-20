package com.software.mywechat.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.software.mywechat.R
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer
import com.software.mywechat.core.design.theme.md_theme_light_onBackground
import com.software.mywechat.feature.main.TopLevelDestination
import kotlin.enums.EnumEntries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentDestination: TopLevelDestination,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MyCenterTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = md_theme_light_errorContainer,
            actionIconContentColor =md_theme_light_onBackground ,
        ),
        title = stringResource(id = currentDestination.titleTextId),
        actions ={
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(id = currentDestination.searchIcon),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.Black,
                )
            }
            IconButton(onClick = onAddClick) {
                Icon(
                    painter = painterResource(id = currentDestination.addIcon),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.Black,
                )
            }
        },
    )
}