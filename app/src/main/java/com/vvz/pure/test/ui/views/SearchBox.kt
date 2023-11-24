package com.vvz.pure.test.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvz.pure.test.R
import com.vvz.pure.test.ui.theme.PureTestTheme


@Composable
internal fun SearchBox(modifier: Modifier = Modifier,
                       query: String,
                       onUpdateQuery: (String) -> Unit,
                       isLoading: Boolean) {

    TextField(value = query,
              placeholder = { Text(text = stringResource(id = R.string.search_cocktails_placeholder)) },
              onValueChange = onUpdateQuery,
              leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
              trailingIcon = {
                  when (query.isBlank()) {
                      true -> {}
                      else -> Row(verticalAlignment = Alignment.CenterVertically) {

                          if (isLoading) CircularProgressIndicator(modifier = Modifier
                              .size(20.dp))

                          IconButton(onClick = { onUpdateQuery("") }) {
                              Icon(imageVector = Icons.Filled.Clear,
                                   contentDescription = null)
                          }
                      }
                  }
              },
              keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
              modifier = modifier
                  .fillMaxWidth()
                  .height(56.dp)
                  .clip(RoundedCornerShape(5.dp)),
              colors = TextFieldDefaults.colors(
                  focusedContainerColor = Color.Transparent,
                  unfocusedContainerColor = Color.Transparent
              ))


}


@Preview(showBackground = true)
@Composable
private fun AutocompleteSearchBoxPreview_Empty() {
    PureTestTheme {
        SearchBox(query = "",
                  onUpdateQuery = {},
                  isLoading = false)
    }
}


@Preview(showBackground = true)
@Composable
private fun AutocompleteSearchBoxPreview_Lading() {
    PureTestTheme {
        SearchBox(query = "Lorem ipsum",
                  onUpdateQuery = {},
                  isLoading = true)
    }
}