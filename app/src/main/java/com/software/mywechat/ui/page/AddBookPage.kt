package com.software.mywechat.ui.page

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.software.mywechat.R
import com.software.mywechat.data.AddBookItem
import com.software.mywechat.data.headList
import com.software.mywechat.ui.book.StickyHeaderState
import com.software.mywechat.utils.AddBookUtils
import com.software.mywechat.view.ActionBook
import com.software.mywechat.view.CQDivider
import kotlinx.coroutines.launch


@Composable
fun AddBookPage(innerPadding : PaddingValues){
    val context = LocalContext.current
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    val data = AddBookUtils.getContactMap()
    val stickyHeaderState = rememberStickyHeaderState()
    stickyHeaderState.setData(data)
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(innerPadding)
    ){
        LazyColumLeftUI(data, context, stickyHeaderState)
        LazyColumRightUI(data, context, stickyHeaderState)
    }
}

@Composable
private fun LazyColumRightUI(
    data: Map<String, MutableList<AddBookItem>>,
    context: Context,
    stickyState: StickyHeaderState
) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier.padding(end = 10.dp),
    ) {
        data.forEach { (initial, _) ->
            item {
                Text(
                    text = initial,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .size(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            scope.launch {
                                scope.launch {
                                    stickyState.scrollToItem(initial)
                                }
                            }
                        },
                    color = Color(ContextCompat.getColor(context, R.color.black_10)),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumLeftUI(
    data: Map<String, MutableList<AddBookItem>>,
    context: Context,
    stickyHeaderState: StickyHeaderState,
) {
    LazyColumn (
        state = stickyHeaderState.state
    ){
        item{
            for(i in headList.indices){
                ActionBook(headList[i], context, i < headList.size -1)
            }
        }
        data.forEach{(initial,contactsForInitial)->
            stickyHeader {
                StickyHeaderTop(initial, context)
            }

            items(contactsForInitial.size) { contact ->
                StickyHeaderItem(contactsForInitial, contact, context)
            }
        }
    }
}

@Composable
fun StickyHeaderTop(initial: String, context: Context) {
    CQDivider()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color(ContextCompat.getColor(context, R.color.nav_bg)))
            .padding(start = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            initial,
            color = Color(ContextCompat.getColor(context, R.color.black_10)),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun StickyHeaderItem(
    its: MutableList<AddBookItem>,
    contact: Int,
    context: Context
) {
    Column {
        for(i in 0 until its.size) {
            ContactItem(its[i], context, its.size > 1)
        }
    }
}

@Composable
fun ContactItem(it: AddBookItem, context: Context, showLine: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(55.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(45.dp)) {
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
                }
            }
        }
    }
    if (showLine) {
        HorizontalDivider(
            modifier = Modifier.padding(start = 70.dp),
            thickness = 0.2.dp,
            color = Color(ContextCompat.getColor(context, R.color.gray_10))
        )
    }
}
@Composable
fun rememberStickyHeaderState(
    state: LazyListState = LazyListState(),
    hashMap: HashMap<String, MutableList<AddBookItem>> = HashMap()
): StickyHeaderState {
    return remember(state) {
        StickyHeaderState(
            state,
            hashMap
        )
    }
}