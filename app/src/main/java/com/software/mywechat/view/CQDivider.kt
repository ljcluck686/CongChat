package com.software.mywechat.view

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.software.mywechat.R

@Composable
fun CQDivider(thickness: Dp? = 0.2.dp, colorId: Int? = R.color.gray_10) {
    val height = thickness ?: 0.2.dp
    val context = LocalContext.current
    val color = colorId ?: R.color.gray_10
    HorizontalDivider(
        color = Color(ContextCompat.getColor(context, color)),
        thickness = height,
    )
}