package com.software.mywechat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


/**
 * 配置路由
 *
 * 方便以后统一设置
 */
public fun NavGraphBuilder.myComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content,
    )
}