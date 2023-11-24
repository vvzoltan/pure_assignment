package com.vvz.pure.test.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vvz.pure.test.ui.views.MainAppBar


@Composable
internal fun BottomNavigationGraph() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemBarState = rememberSystemBarState()

    Scaffold(
        topBar = {
            MainAppBar(state = systemBarState)
        },
        bottomBar = {
            if (systemBarState.showBottomBar) {
                NavigationBar {
                    BottomNavigationScreen.list.forEach { tab ->
                        NavigationBarItem(selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == tab.route } == true,
                                          onClick = { navController.navigateAsBottomBar(route = tab.route) },
                                          icon = { Icon(tab.icon, contentDescription = null) })
                    }
                }
            }
        },
        content = { scaffoldPadding ->

            CompositionLocalProvider(LocalSystemBarStateProvider provides systemBarState) {

                NavHost(navController = navController,
                        startDestination = BottomNavigationScreen.Categories.route,
                        modifier = Modifier.padding(scaffoldPadding)) {

                    composable(route = BottomNavigationScreen.Categories.route) {
                        ScreenNavigationGraph(startDestination = ScreenRoutes.Categories.route) { controller ->
                            addCategoriesScreen(controller)
                            addListScreen(controller)
                            addDetailsScreen(controller)
                        }
                    }

                    composable(route = BottomNavigationScreen.Favourites.route) {
                        ScreenNavigationGraph(startDestination = ScreenRoutes.Favourites.route) { controller ->
                            addFavouritesScreen(controller)
                            addDetailsScreen(controller)
                        }
                    }

                }

            }
        }
    )

}


sealed class BottomNavigationScreen(val route: String, val icon: ImageVector) {

    object Categories : BottomNavigationScreen(route = "tab_categories", icon = Icons.Filled.AccountCircle)
    object Favourites : BottomNavigationScreen(route = "tab_favourites", icon = Icons.Filled.Favorite)

    companion object {
        val list = listOf(Categories, Favourites)
    }
}


fun NavHostController.navigateAsBottomBar(route: String) = navigate(route) {

    // Pop up to the start destination of the graph to
    // avoid building up a large stack of destinations
    // on the back stack as users select items
    popUpTo(graph.findStartDestination().id) {
        saveState = true
    }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    launchSingleTop = true
    // Restore state when reselecting a previously selected item
    restoreState = true
}