package com.vvz.pure.test.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvz.pure.test.ui.theme.PureTestTheme

@Composable
internal fun TableView(modifier: Modifier = Modifier,
                       title: String,
                       data: List<Pair<String, String?>>,
                       config: TableViewConfig,
                       isPlaceholder: Boolean = false) {

    Column(modifier = modifier.fillMaxWidth(),
           verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(text = title,
             style = MaterialTheme.typography.titleLarge,
             modifier = Modifier.placeholder(isPlaceholder))

        Spacer(modifier = Modifier.height(8.dp))

        data.forEach { pair ->
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                Text(text = pair.first,
                     modifier = Modifier
                         .weight(0.5f)
                         .placeholder(isPlaceholder),
                     style = config.left.style,
                     color = config.left.color,
                     textAlign = config.left.align)

                Text(text = pair.second ?: "",
                     modifier = Modifier
                         .weight(0.5f)
                         .placeholder(isPlaceholder),
                     style = config.right.style,
                     color = config.right.color,
                     textAlign = config.right.align)
            }
        }
    }

}


internal data class TableViewConfig(val left: ColumnConfig,
                                    val right: ColumnConfig) {

    data class ColumnConfig(val style: TextStyle,
                            val color: Color,
                            val align: TextAlign)

}


@Preview(showBackground = true)
@Composable
private fun TableViewPreview() {
    PureTestTheme {
        val data = listOf("Item 1" to "Value 1",
                          "Item 2" to "Value 2",
                          "Item 3" to null,
                          "Item 4" to "Value 4",
                          "Item 5" to null)

        val config = TableViewConfig(left = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                         color = Color.Gray,
                                                                         align = TextAlign.End),

                                     right = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                          color = Color.Black,
                                                                          align = TextAlign.Start))
        TableView(title = "Table title", data = data, config = config)
    }
}


@Preview(showBackground = true)
@Composable
private fun TableViewPlaceholderPreview() {
    PureTestTheme {
        val data = listOf("Item 1" to "Value 1",
                          "Item 2" to "Value 2",
                          "Item 3" to null,
                          "Item 4" to "Value 4",
                          "Item 5" to null)

        val config = TableViewConfig(left = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                         color = Color.Gray,
                                                                         align = TextAlign.End),

                                     right = TableViewConfig.ColumnConfig(style = MaterialTheme.typography.labelMedium,
                                                                          color = Color.Black,
                                                                          align = TextAlign.Start))
        TableView(title = "Table title", data = data, config = config, isPlaceholder = true)
    }
}