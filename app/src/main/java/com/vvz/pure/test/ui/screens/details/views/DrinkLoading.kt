package com.vvz.pure.test.ui.screens.details.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.ui.theme.PureTestTheme

@Composable
internal fun DrinkLoading() {

    val drinkSample = Drink(id = "",
                            name = "Lorem ipsum dolor sit ames",
                            category = "Lorem ipsum",
                            photo = null,
                            glassType = "Lorem ipsum",
                            type = "Lorem",
                            ingredients = listOf("Lorem" to "ipsum",
                                                 "dolor" to "sit ames",
                                                 "consectetur" to null,
                                                 "adipiscing" to "sed do eiusmod tempor"))


    DrinkDetails(drink = drinkSample,
                 onUpdateFavorite = {},
                 isPlaceholder = true)
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun DrinkLoadingPreview() {
    PureTestTheme {
        DrinkLoading()
    }
}
