package com.vvz.pure.test.ui.screens.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vvz.pure.test.di.dagger.daggerViewModel
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.domain.models.PureError
import com.vvz.pure.test.ui.screens.categories.views.categoryResults
import com.vvz.pure.test.ui.screens.categories.views.loadingCategories
import com.vvz.pure.test.ui.screens.categories.views.searchResults
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.theme.PureTestTheme
import com.vvz.pure.test.ui.views.ErrorView
import com.vvz.pure.test.ui.views.SearchBox


@Composable
internal fun CategoriesScreen(viewModel: CategoriesViewModel = daggerViewModel(),
                              onShowCategory: (category: String) -> Unit,
                              onShowDrink: (DrinkPreview) -> Unit) {

    CategoriesContent(state = viewModel.screenState,
                      onSelectCategory = onShowCategory,
                      onSelectDrink = onShowDrink,
                      onIntent = viewModel::intent)

}


@Composable
private fun CategoriesContent(state: CategoriesViewModel.ScreenState,
                              onSelectCategory: (String) -> Unit,
                              onSelectDrink: (DrinkPreview) -> Unit,
                              onIntent: (CategoriesViewModel.Intent) -> Unit) {

    Column {
        SearchBox(query = state.searchQuery,
                  onUpdateQuery = { onIntent(CategoriesViewModel.Intent.UpdateSearch(it)) },
                  isLoading = state.isSearching)

        LazyColumn(contentPadding = Layout.Padding.Screen.DefaultContentPadding) {

            when (state.searchResults == null) {
                true  -> when (state.error) {
                    null -> when (state.categories) {
                        null -> loadingCategories()
                        else -> categoryResults(state.categories,
                                                onSelectCategory = onSelectCategory)
                    }

                    else -> item {
                        ErrorView(error = state.error,
                                  onRetry = { onIntent(CategoriesViewModel.Intent.LoadCategories) },
                                  modifier = Modifier.fillParentMaxSize())
                    }
                }

                false -> searchResults(results = state.searchResults, onSelectDrink = onSelectDrink, query = state.searchQuery)
            }


        }
    }
}


// region previews

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesListPreview_Loaded() {
    PureTestTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CategoriesContent(state = CategoriesViewModel.ScreenState(categories = listOf("Coffee", "Mocktail", "Shot")),
                              onIntent = {},
                              onSelectCategory = {},
                              onSelectDrink = {})
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesListPreview_Loading() {
    PureTestTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CategoriesContent(state = CategoriesViewModel.ScreenState(categories = null),
                              onIntent = {},
                              onSelectCategory = {},
                              onSelectDrink = {})
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesListPreview_Empty() {
    PureTestTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CategoriesContent(state = CategoriesViewModel.ScreenState(categories = listOf()),
                              onIntent = {},
                              onSelectCategory = {},
                              onSelectDrink = {})
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesListPreview_Error() {
    PureTestTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CategoriesContent(state = CategoriesViewModel.ScreenState(error = PureError.Unknown(cause = Error("preview error"))),
                              onIntent = {},
                              onSelectCategory = {},
                              onSelectDrink = {})
        }
    }
}

// endregion