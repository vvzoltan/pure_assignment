package com.vvz.pure.test.data.remote

import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview

interface RemoteDatasource {

    suspend fun getCategories(): List<String>

    suspend fun searchDrinks(query: String): List<DrinkPreview>

    suspend fun getDrinksForCategory(category: String): List<DrinkPreview>

    suspend fun getDrinkDetails(id: String): Drink?

}