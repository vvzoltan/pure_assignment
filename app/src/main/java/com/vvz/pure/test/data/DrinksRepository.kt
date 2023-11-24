package com.vvz.pure.test.data

import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview
import kotlinx.coroutines.flow.Flow


interface DrinksRepository {

    suspend fun loadDrinkCategories(): List<String>

    suspend fun loadDrinksForCategory(category: String): List<DrinkPreview>

    suspend fun searchDrinks(query: String): List<DrinkPreview>

    suspend fun loadDrinkDetails(id: String): Drink?

    suspend fun addToFavourites(id: String)

    suspend fun removeFromFavourites(id: String)

    fun observeFavourites(): Flow<List<DrinkPreview>>

}