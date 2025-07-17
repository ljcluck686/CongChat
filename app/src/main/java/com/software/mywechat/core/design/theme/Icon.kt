package com.software.mywechat.core.design.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun HomeIcon() {
    Icon(Icons.Default.Home, contentDescription = null)
}


@Composable
fun ShoppingCartIcon() {
    Icon(Icons.Default.ShoppingCart, contentDescription = null)
}

@Composable
fun ArrowIcon(tint: Color = LocalArrowColor.current) {
    Icon(
        imageVector = Icons.Default.ChevronRight,
        contentDescription = null,
        tint = tint,
    )
}