package com.example.bookapp.presentation.components.bottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bookapp.presentation.ui.theme.GrayUnselected
import com.example.bookapp.presentation.ui.theme.Orange

@Composable
fun BottomBar(
    navController: NavController,
    currentRoute : String?
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        BottomList.bottomItemsList.forEach { bottomItem ->
            NavigationBarItem(
                modifier = Modifier.testTag(bottomItem.title.toString()),
                selected = currentRoute == bottomItem.route,
                onClick = {
                    navController.navigate(bottomItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = bottomItem.icon,
                        contentDescription = stringResource(id = bottomItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = bottomItem.title)
                    )
                },
                colors = NavigationBarItemColors(
                    selectedTextColor = Orange,
                    selectedIconColor = Orange,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedTextColor = GrayUnselected,
                    unselectedIconColor = GrayUnselected,
                    disabledIconColor = GrayUnselected,
                    disabledTextColor = GrayUnselected
                )
            )
        }
    }
}