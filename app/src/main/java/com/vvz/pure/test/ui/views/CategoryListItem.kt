package com.vvz.pure.test.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.theme.PureTestTheme


@Composable
internal fun CategoryListItem(name: String, showShimmer: Boolean = false, onTap: (() -> Unit) = {}) {

    val modifier = Modifier.placeholder(showShimmer)

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(Layout.Size.listItemHeight)
            .clickable(enabled = !showShimmer, onClick = onTap)
            .padding(horizontal = Layout.Padding.ListItemHorizontal,
                     vertical = Layout.Padding.ListItemVertical)) {

        Text(text = name,
             style = MaterialTheme.typography.titleMedium,
             maxLines = 1,
             modifier = modifier)

        Icon(imageVector = Icons.Filled.KeyboardArrowRight,
             contentDescription = name,
             modifier = modifier.alpha(0.3f))

    }

}


@Preview(showBackground = true)
@Composable
private fun CategoryListItemPreview() {
    PureTestTheme {
        CategoryListItem(name = "Lorem ipsum", onTap = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryListItemShimmerPreview() {
    PureTestTheme {
        CategoryListItem(name = "Lorem ipsum", showShimmer = true)
    }
}