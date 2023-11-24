package com.vvz.pure.test.ui.screens.drinks

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vvz.pure.test.di.dagger.daggerViewModel
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.screens.drinks.views.LoadedDrinkList
import com.vvz.pure.test.ui.screens.drinks.views.LoadingDrinksList
import com.vvz.pure.test.ui.views.ErrorView


@Composable
internal fun DrinksScreen(viewModel: DrinksViewModel = daggerViewModel(),
                          category: String,
                          onShowItems: (drink: DrinkPreview) -> Unit) {

    CategoriesContent(state = viewModel.screenState,
                      onSelect = onShowItems,
                      onRetry = { viewModel.intent(DrinksViewModel.Intent.LoadDrinks(category = category)) })

}


@Composable
private fun CategoriesContent(state: DrinksViewModel.State,
                              onSelect: (DrinkPreview) -> Unit,
                              onRetry: () -> Unit) {


    Crossfade(targetState = state, modifier = Modifier.fillMaxSize(), label = "drinks_content") { currentState ->
        when (currentState) {
            is DrinksViewModel.State.Loading -> LoadingDrinksList()
            is DrinksViewModel.State.Loaded  -> LoadedDrinkList(list = currentState.drinks, onSelect = onSelect)
            is DrinksViewModel.State.Error   -> ErrorView(error = currentState.error, onRetry = onRetry)
        }
    }
}