package com.vvz.pure.test.ui.screens.drinks.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.views.drinksList


@Composable
internal fun LoadedDrinkList(list: List<DrinkPreview>, onSelect: (DrinkPreview) -> Unit) {
    LazyColumn(contentPadding = Layout.Padding.Screen.DefaultContentPadding) {
        drinksList(drinks = list, onSelect = onSelect)
    }
}