package com.vvz.pure.test.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

object Layout {

    object Padding {
        val ListItemHorizontal = 20.dp
        val ListItemVertical = 12.dp
        val ListDivider = PaddingValues(horizontal = 20.dp)
        val SectionPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)

        object Screen {
            val Horizontal = 20.dp
            val Vertical = 20.dp
            val top = Vertical
            val bottom = Vertical
            val start = Horizontal
            val end = Horizontal
            val DefaultContentPadding = PaddingValues(start = 0.dp, top = top, end = 0.dp, bottom = bottom)
        }

    }

    object Size {
        val listItemHeight = 56.dp
    }

    object Spacing {
        object Screen {
            val sectionVertical = 20.dp
        }
    }

}