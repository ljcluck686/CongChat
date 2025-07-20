package com.software.mywechat.core.design.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.software.mywechat.R

val IndicatorWidth = 8.dp

val SpaceTip = 50.dp

//region 分割线尺寸
val Space4XLarge = 40.dp
val Space3XLarge = 30.dp
val SpaceLarge = 20.dp
val SpaceOuter = 16.dp
val SpaceExtraOuter = 14.dp
val SpaceMedium = 10.dp
val SpaceExtraMedium = 7.dp
val SpaceSmall = 5.dp
val SpaceExtraSmall2 = 2.dp
val SpaceExtraSmall = 0.7.dp
//endregion

//region 分割线组件
@Composable
fun Space3XLargeWidth(): Unit {
    Spacer(modifier = Modifier.width(Space3XLarge))
}

@Composable
fun Space3XLargeHeight(): Unit {
    Spacer(modifier = Modifier.height(Space3XLarge))
}


@Composable
fun SpaceLargeHeight(): Unit {
    Spacer(modifier = Modifier.height(SpaceLarge))
}

@Composable
fun SpacerOuterWidth(): Unit {
    Spacer(modifier = Modifier.width(SpaceOuter))
}

@Composable
fun SpacerOuterHeight(): Unit {
    Spacer(modifier = Modifier.height(SpaceOuter))
}

@Composable
fun SpaceMediumWidth(): Unit {
    Spacer(modifier = Modifier.width(SpaceMedium))
}

@Composable
fun SpaceMediumHeight() {
    Spacer(modifier = Modifier.height(SpaceMedium).background(color = md_theme_light_background))
}

@Composable
fun SpaceExtraMediumHeight(): Unit {
    Spacer(modifier = Modifier.height(SpaceExtraMedium))
}

@Composable
fun SpaceExtraMediumWidth(): Unit {
    Spacer(modifier = Modifier.width(SpaceExtraMedium))
}

@Composable
fun SpaceSmallWidth(): Unit {
    Spacer(modifier = Modifier.width(SpaceSmall))
}

@Composable
fun SpaceSmallHeight(): Unit {
    Spacer(modifier = Modifier.height(SpaceSmall))
}

@Composable
fun SpaceExtraSmallHeight(): Unit {
    Spacer(modifier = Modifier.height(SpaceExtraSmall))
}

@Composable
fun CQDivider(thickness: Dp? = 0.2.dp, colorId: Int? = R.color.gray_10) {
    val height = thickness ?: 0.2.dp
    val context = LocalContext.current
    val color = colorId ?: R.color.gray_10
    HorizontalDivider(
        thickness = height,
        color = Color(ContextCompat.getColor(context, color))
    )
}

//@Composable
//fun SpaceWeight(): Unit {
//    Spacer(modifier = Modifier.weight(1f))
//}

//endregion