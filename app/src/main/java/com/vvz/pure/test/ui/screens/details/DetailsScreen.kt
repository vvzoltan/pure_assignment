package com.vvz.pure.test.ui.screens.details

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import com.vvz.pure.test.di.dagger.daggerViewModel
import com.vvz.pure.test.ui.screens.details.views.DrinkDetails
import com.vvz.pure.test.ui.screens.details.views.DrinkLoading
import com.vvz.pure.test.ui.screens.details.views.DrinkNotFound
import com.vvz.pure.test.ui.views.ErrorView

@Composable
internal fun DetailsScreen(viewModel: DetailsViewModel = daggerViewModel()) {

    DetailsContent(state = viewModel.screenState,
                   onRetry = { viewModel.intent(DetailsViewModel.Intent.LoadDrinkDetails) },
                   onAddToFavourite = { viewModel.intent(DetailsViewModel.Intent.AddFavourite) },
                   onRemoveFavourite = { viewModel.intent(DetailsViewModel.Intent.RemoveFavourite) })

}


@Composable
private fun DetailsContent(state: DetailsViewModel.State,
                           onRetry: () -> Unit,
                           onAddToFavourite: () -> Unit,
                           onRemoveFavourite: () -> Unit) {

    val scrollState = rememberScrollState()

    when (state) {
        is DetailsViewModel.State.Loading  -> DrinkLoading()

        is DetailsViewModel.State.NotFound -> DrinkNotFound(id = state.id)

        is DetailsViewModel.State.Loaded   -> DrinkDetails(drink = state.drink,
                                                           onUpdateFavorite = {
                                                               when (it) {
                                                                   true  -> onAddToFavourite()
                                                                   false -> onRemoveFavourite()
                                                               }
                                                           },
                                                           scrollState = scrollState)

        is DetailsViewModel.State.Error    -> ErrorView(error = state.error, onRetry = onRetry)
    }
}