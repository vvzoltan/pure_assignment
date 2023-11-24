package com.vvz.pure.test.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.theme.Layout


internal fun LazyListScope.drinksList(drinks: List<DrinkPreview>,
                                      onSelect: (DrinkPreview) -> Unit,
                                      isPlaceholder: Boolean = false) {

    itemsIndexed(drinks) { index, drink ->
        DrinkListItem(drink = drink, showShimmer = isPlaceholder, onTap = { onSelect(drink) })
        if (index < drinks.lastIndex) Divider(modifier = Modifier.padding(Layout.Padding.ListDivider))
    }
}