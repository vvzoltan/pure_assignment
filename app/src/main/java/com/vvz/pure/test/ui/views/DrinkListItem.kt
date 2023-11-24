package com.vvz.pure.test.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.theme.PureTestTheme


@Composable
internal fun DrinkListItem(drink: DrinkPreview, showShimmer: Boolean, onTap: () -> Unit = {}) {

    val modifier = Modifier.placeholder(showShimmer)

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !showShimmer, onClick = onTap)
            .padding(horizontal = Layout.Padding.ListItemHorizontal,
                     vertical = Layout.Padding.ListItemVertical)) {

        ImageView(model = drink.thumbnail,
                  modifier = Modifier
                      .size(36.dp)
                      .clip(RoundedCornerShape(3.dp)),
                  isPlaceholder = showShimmer)

        Column(verticalArrangement = Arrangement.spacedBy(2.dp),
               modifier = Modifier.weight(1f)) {
            Text(text = drink.name,
                 maxLines = 1,
                 style = MaterialTheme.typography.titleMedium,
                 modifier = modifier)

            Text(text = drink.category,
                 maxLines = 1,
                 style = MaterialTheme.typography.titleSmall,
                 modifier = modifier.alpha(0.7f))
        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(imageVector = Icons.Filled.KeyboardArrowRight,
             contentDescription = drink.name,
             modifier = modifier.alpha(0.3f))

    }

}


@Preview(showBackground = true)
@Composable
private fun DrinkListItemPreview() {
    PureTestTheme {
        DrinkListItem(drink = DrinkPreview(id = "", name = "Mojito", category = "Cocktail", thumbnail = null), showShimmer = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun DrinkListItemShimmerPreview() {
    PureTestTheme {
        DrinkListItem(drink = DrinkPreview(id = "", name = "Mojito", category = "Cocktail", thumbnail = null), showShimmer = true)
    }
}