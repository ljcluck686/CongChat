package com.software.mywechat.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.software.mywechat.core.design.theme.CQDivider
import com.software.mywechat.core.design.theme.SpaceExtraSmall2
import com.software.mywechat.core.design.theme.SpaceOuter
import com.software.mywechat.core.design.theme.md_theme_light_outlineVariant
import com.software.mywechat.core.model.Infos
import com.software.mywechat.core.model.User

@Composable
fun SearchRoute(
    toBack: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){
    val datum by viewModel.datum.collectAsState()
    val query by viewModel.query.collectAsState()
    val placeholder by viewModel.placeholder.collectAsState()
    SearchScreen(
        toBack = toBack,
        query = query,
        datum = datum,
        placeholder = placeholder,
        onQueryChange = viewModel::onQueryChange,
        onSearchClick = viewModel::onSearchClick,
    )
}

@Composable
fun SearchScreen(
    query: String = "",
    placeholder: String = "",
    datum: Infos,
    onQueryChange: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
    toBack: () -> Unit = {},
){
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            FriendAppBar(
                toBack = toBack,
                query = query,
                placeholder = placeholder,
                onQueryChange = onQueryChange,
                onSearchClick = {
                    onSearchClick()
                    keyboardController?.hide()
                },
            )
        }
    ){paddingValues->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).background(md_theme_light_outlineVariant)
        ) {
            items(datum.infos) { info ->
                LinerContent(info = info)
            }
        }

    }

}

@Composable
fun LinerContent(
    info: User,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable(onClick = {}),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
        ) {
            AsyncImage(
                model = info.avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(6.dp))
            )
        }
        Text(
            text = info.nickname,
            fontSize = 25.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 40.dp)
                .weight(1f)
        )
    }
    CQDivider()

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendAppBar(
    toBack: () -> Unit,
    query: String,
    placeholder: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,

    ) {
    TopAppBar(
        title = {
            FriendTopAppBar(
                query,
                placeholder,
                onQueryChange,
                onSearchClick,
            )
        },
        navigationIcon = {
            IconButton(onClick = toBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            TextButton(
                onClick = onSearchClick,
                modifier = Modifier
            ) {
                Text(
                    text = "搜索"
                )
            }
        }
    )
}

@Composable
fun FriendTopAppBar(
    query: String,
    placeholder: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .clip(MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClick()
                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = SpaceOuter)
            )

            IconButton(onClick = {
                onQueryChange("")
            }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(SpaceExtraSmall2)
                )
            }

        }

        if (query.isEmpty()) {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = SpaceOuter)
            )
        }
    }
}