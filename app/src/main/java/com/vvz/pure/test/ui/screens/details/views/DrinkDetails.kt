package com.vvz.pure.test.ui.screens.details.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vvz.pure.test.R
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.ui.theme.Layout
import com.vvz.pure.test.ui.views.ImageView
import com.vvz.pure.test.ui.views.TableView
import com.vvz.pure.test.ui.views.TableViewConfig


@Composable
internal fun DrinkDetails(drink: Drink,
                          onUpdateFavorite: (Boolean) -> Unit,
                          isPlaceholder: Boolean = false,
                          scrollState: ScrollState = rememberScrollState()) {


    Column(verticalArrangement = Arrangement.spacedBy(Layout.Spacing.Screen.sectionVertical),
           modifier = Modifier
               .fillMaxWidth()
               .verticalScroll(scrollState)
               .padding(PaddingValues(top = 0.dp,
                                      bottom = Layout.Padding.Screen.bottom,
                                      start = 0.dp,
                                      end = 0.dp))) {

        ImageView(model = drink.photo,
                  description = drink.name,
                  isPlaceholder = isPlaceholder,
                  modifier = Modifier
                      .fillMaxWidth()
                      .aspectRatio(1f))

        TableView(title = stringResource(id = R.string.drink_details_details_title),
                  data = listOf(
                      stringResource(id = R.string.drink_details_name) to drink.name,
                      stringResource(id = R.string.drink_details_id) to drink.id,
                      stringResource(id = R.string.drink_details_category) to drink.category,
                      stringResource(id = R.string.drink_details_type) to drink.type,
                      stringResource(id = R.string.drink_details_glass) to drink.glassType
                  ),
                  config = TableViewConfig(left = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                               color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                                                               align = TextAlign.End),

                                           right = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                                color = MaterialTheme.colorScheme.onSurface,
                                                                                align = TextAlign.Start)),
                  modifier = Modifier.padding(Layout.Padding.SectionPadding),
                  isPlaceholder = isPlaceholder)

        TableView(title = stringResource(id = R.string.drink_details_ingredient_title),
                  data = drink.ingredients,
                  config = TableViewConfig(left = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                               color = MaterialTheme.colorScheme.onSurface,
                                                                               align = TextAlign.Start),

                                           right = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                                color = MaterialTheme.colorScheme.onSurface,
                                                                                align = TextAlign.Start)),
                  modifier = Modifier.padding(Layout.Padding.SectionPadding),
                  isPlaceholder = isPlaceholder)


        if (!isPlaceholder) {
            Crossfade(targetState = drink.isFavourite,
                      label = "favourite_button",
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(Layout.Padding.Screen.Horizontal)) { isFavourite ->

                when (isFavourite) {
                    true  -> OutlinedButton(onClick = { onUpdateFavorite(false) },
                                            content = { Text(text = stringResource(id = R.string.button_remove_favourite)) },
                                            modifier = Modifier.fillMaxWidth())

                    false -> Button(onClick = { onUpdateFavorite(true) },
                                    content = { Text(text = stringResource(id = R.string.button_add_favourite)) },
                                    modifier = Modifier.fillMaxWidth())
                }
            }
        }

    }

}