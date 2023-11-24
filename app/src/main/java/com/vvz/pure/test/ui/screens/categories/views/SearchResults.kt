package com.vvz.pure.test.ui.screens.categories.views

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vvz.pure.test.R
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.views.EmptyView
import com.vvz.pure.test.ui.views.drinksList


internal fun LazyListScope.searchResults(results: List<DrinkPreview>,
                                         onSelectDrink: (DrinkPreview) -> Unit, query: String) {
    when (results.isEmpty()) {
        true  -> item {
            EmptyView(string = stringResource(id = R.string.search_no_results, query),
                      modifier = Modifier.fillParentMaxSize())
        }

        false -> drinksList(drinks = results, onSelect = onSelectDrink)
    }
}