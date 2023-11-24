package com.vvz.pure.test.data.local.db

import androidx.room.TypeConverter


typealias IngredientList = List<Pair<String, String?>>

class IngredientsConverter {

    @TypeConverter
    fun fromString(string: String): IngredientList {
        return when (string.isBlank()) {
            true  -> listOf()
            false -> {
                val pairs = string.split("$$")
                pairs.map { pair ->
                    val (a, b) = pair.split("##")
                    if (b.isBlank()) a to null else a to b
                }
            }
        }
    }

    @TypeConverter
    fun toString(list: IngredientList): String {
        return when (list.isEmpty()) {
            true  -> ""
            false -> {
                list.joinToString("$$") { pair ->
                    "${pair.first}##${pair.second}"
                }
            }
        }
    }

}