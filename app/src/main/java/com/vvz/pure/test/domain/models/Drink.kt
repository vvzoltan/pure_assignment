package com.vvz.pure.test.domain.models

data class Drink(val id: String,
                 val name: String,
                 val category: String,
                 val photo: String?,
                 val type: String,
                 val glassType: String,
                 val ingredients: List<Pair<String, String?>>,
                 val isFavourite: Boolean = false)
