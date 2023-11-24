package com.vvz.pure.test.ui.navigation

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.vvz.pure.test.R
import com.vvz.pure.test.ui.screens.categories.CategoriesScreen
import com.vvz.pure.test.ui.screens.details.DetailsScreen
import com.vvz.pure.test.ui.screens.drinks.DrinksScreen
import com.vvz.pure.test.ui.screens.favourites.FavouritesScreen
import java.net.URLDecoder
import java.net.URLEncoder


fun NavGraphBuilder.addCategoriesScreen(navController: NavController) {
    pushComposable(route = ScreenRoutes.Categories.route) {
        LocalSystemBarStateProvider.current.apply {
            showBottomBar = true
            title = stringResource(id = R.string.categories_title)
        }
        CategoriesScreen(onShowCategory = { navController.navigate(route = ScreenRoutes.List.routeForCategory(category = it)) },
                         onShowDrink = { drink ->
                             navController.navigate(route = ScreenRoutes.Details.routeForItem(id = drink.id, name = drink.name))
                         })
    }
}

fun NavGraphBuilder.addListScreen(navController: NavController) {
    pushComposable(route = ScreenRoutes.List.route) { backStackEntry ->
        val category = checkNotNull(backStackEntry.arguments?.getString("category"))
        LocalSystemBarStateProvider.current.apply {
            showBottomBar = true
            title = URLDecoder.decode(category, "UTF-8")
        }
        DrinksScreen(category = URLDecoder.decode(category, "UTF-8"),
                     onShowItems = { drink ->
                         navController.navigate(route = ScreenRoutes.Details.routeForItem(id = drink.id, name = drink.name))
                     })
    }
}

fun NavGraphBuilder.addDetailsScreen(navController: NavController) {
    pushComposable(route = ScreenRoutes.Details.route) { backStackEntry ->
        val name = checkNotNull(backStackEntry.arguments?.getString("name"))
        LocalSystemBarStateProvider.current.apply {
            showBottomBar = false
            title = URLDecoder.decode(name, "UTF-8")
        }
        DetailsScreen()

    }
}

fun NavGraphBuilder.addFavouritesScreen(navController: NavController) {
    pushComposable(route = ScreenRoutes.Favourites.route) {
        LocalSystemBarStateProvider.current.apply {
            showBottomBar = true
            title = stringResource(id = R.string.favourites_title)
        }
        FavouritesScreen(onShowItems = { drink ->
            navController.navigate(route = ScreenRoutes.Details.routeForItem(id = drink.id, name = drink.name))
        })
    }
}


sealed class ScreenRoutes(val route: String) {

    object Categories : ScreenRoutes(route = "screen_categories")

    object Favourites : ScreenRoutes(route = "screen_favourites")

    object List : ScreenRoutes(route = "screen_list/{category}") {
        fun routeForCategory(category: String) = route.replace("{category}", URLEncoder.encode(category, "UTF-8"))
    }

    object Details : ScreenRoutes(route = "screen_details/{id}/{name}") {
        fun routeForItem(id: String, name: String) = route.replace("{id}", id).replace("{name}", URLEncoder.encode(name, "UTF-8"))
    }
}
