package com.vvz.pure.test.ui.screens.drinks.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.views.drinksList


@Composable
internal fun LoadingDrinksList() {

    val titleRange = 0 until 20
    val categoryRange = 0 until 50

    val drinks = listOf(DrinkPreview(id = "",
                                     name = getRandomString(titleRange.random() + 10),
                                     category = getRandomString(categoryRange.random() + 10),
                                     thumbnail = null),
                        DrinkPreview(id = "",
                                     name = getRandomString(titleRange.random() + 10),
                                     category = getRandomString(categoryRange.random() + 10),
                                     thumbnail = null),
                        DrinkPreview(id = "",
                                     name = getRandomString(titleRange.random() + 10),
                                     category = getRandomString(categoryRange.random() + 10),
                                     thumbnail = null))

    val items = (1 until 20).map { drinks.random() }

    LazyColumn(contentPadding = Layout.Padding.Screen.DefaultContentPadding) {
        drinksList(items, onSelect = {}, isPlaceholder = true)
    }

}


fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}