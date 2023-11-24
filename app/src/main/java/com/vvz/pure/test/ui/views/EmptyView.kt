package com.vvz.pure.test.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
internal fun EmptyView(modifier: Modifier = Modifier,
                       string: String) {

    Box(modifier = modifier.fillMaxSize()) {
        Text(text = string,
             modifier = Modifier.align(Alignment.Center))
    }

}