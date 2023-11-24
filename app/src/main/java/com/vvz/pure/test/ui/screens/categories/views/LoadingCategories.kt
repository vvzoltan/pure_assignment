package com.vvz.pure.test.ui.screens.categories.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.views.CategoryListItem

internal fun LazyListScope.loadingCategories() {

    val previewNames = listOf("Lorem ipsum",
                              "Lorem ipsum dolor sit amet",
                              "Lorem ipsum dolor")

    val items = (1 until 20).map { previewNames.random() }

    itemsIndexed(items) { index, item ->
        CategoryListItem(name = item, showShimmer = true)
        if (index < items.lastIndex) Divider(modifier = Modifier.padding(Layout.Padding.ListDivider))
    }

}