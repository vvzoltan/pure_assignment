package com.vvz.pure.test.ui.screens.categories.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vvz.pure.test.R
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.views.CategoryListItem
import com.vvz.pure.test.ui.views.EmptyView

internal fun LazyListScope.categoryResults(results: List<String>,
                                           onSelectCategory: (String) -> Unit) {
    when (results.isEmpty()) {
        true  -> item {
            EmptyView(string = stringResource(id = R.string.categories_not_found),
                      modifier = Modifier.fillParentMaxSize())
        }

        false -> itemsIndexed(results) { index, category ->
            CategoryListItem(name = category, onTap = { onSelectCategory(category) })
            if (index < results.lastIndex) Divider(modifier = Modifier.padding(Layout.Padding.ListDivider))
        }
    }
}