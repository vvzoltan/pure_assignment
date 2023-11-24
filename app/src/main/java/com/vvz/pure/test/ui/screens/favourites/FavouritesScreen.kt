package com.vvz.pure.test.ui.screens.favourites

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.vvz.pure.test.R
import com.vvz.pure.test.di.dagger.daggerViewModel
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.views.EmptyView
import com.vvz.pure.test.ui.views.SearchBox
import com.vvz.pure.test.ui.views.drinksList


@Composable
internal fun FavouritesScreen(viewModel: FavouritesViewModel = daggerViewModel(),
                              onShowItems: (drink: DrinkPreview) -> Unit) {

    val drinks by viewModel.drinks.collectAsState(initial = listOf())
    val filtered by remember {
        derivedStateOf {
            drinks.filter { it.name.contains(viewModel.screenState.query, true) }
        }
    }

    DrinkList(list = filtered,
              onSelect = onShowItems,
              query = viewModel.screenState.query,
              onUpdateQuery = { viewModel.intent(FavouritesViewModel.Intent.UpdateQuery(query = it)) })

}


@Composable
private fun DrinkList(list: List<DrinkPreview>,
                      query: String,
                      onUpdateQuery: (String) -> Unit,
                      onSelect: (DrinkPreview) -> Unit) {

    Column {
        SearchBox(query = query,
                  onUpdateQuery = onUpdateQuery,
                  isLoading = false)

        Crossfade(targetState = list, label = "favourites_list") { currentList ->
            when (currentList.isEmpty()) {
                true  -> when (query.isBlank()) {
                    true  -> EmptyView(string = stringResource(id = R.string.favourites_empty))
                    false -> EmptyView(string = stringResource(id = R.string.search_no_results, query))
                }

                false -> LazyColumn(contentPadding = Layout.Padding.Screen.DefaultContentPadding) {
                    drinksList(drinks = list, onSelect = onSelect)
                }
            }
        }

    }
}
