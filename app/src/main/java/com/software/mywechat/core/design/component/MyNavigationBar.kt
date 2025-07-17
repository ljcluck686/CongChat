package com.software.mywechat.core.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.software.mywechat.core.design.theme.md_theme_light_errorContainer
import com.software.mywechat.core.design.theme.md_theme_light_secondary
import com.software.mywechat.feature.main.TopLevelDestination

@Composable
fun MyNavigationBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (Int) -> Unit,
    currentDestination:String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(md_theme_light_errorContainer)
            .navigationBarsPadding()
    ) {
        destinations.forEachIndexed { index, destination ->
            val selected = destination.route == currentDestination
            val color = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(7.dp)
                    .clickable {
                        onNavigateToDestination(index)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id =
                        if (selected)
                            destination.selectedIcon
                        else
                            destination.unselectedIcon
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = stringResource(id = destination.titleTextId),
                    style = MaterialTheme.typography.bodySmall,
                    color = color,
                )
            }
        }
    }
}