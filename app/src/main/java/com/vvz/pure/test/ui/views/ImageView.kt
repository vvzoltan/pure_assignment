package com.vvz.pure.test.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
internal fun ImageView(modifier: Modifier = Modifier,
                       model: String?,
                       description: String? = null,
                       scale: ContentScale = ContentScale.Fit,
                       isPlaceholder: Boolean = false) {

    var showShimmer by remember {
        mutableStateOf(true)
    }

    AsyncImage(model = model,
               contentDescription = description,
               contentScale = scale,
               modifier = modifier.shimmerEffect(isPlaceholder || showShimmer),
               onSuccess = { showShimmer = false },
               onError = { showShimmer = false }) // we could show a placeholder in this case

}