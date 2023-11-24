package com.vvz.pure.test.ui.screens.details.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vvz.pure.test.R
import com.vvz.pure.test.ui.theme.PureTestTheme

@Composable
internal fun DrinkNotFound(id: String) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.drink_details_not_found, id),
             modifier = Modifier.align(Alignment.Center))
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DrinkNotFoundPreview() {
    PureTestTheme {
        DrinkNotFound(id = "123")
    }
}