package com.vvz.pure.test.data.local

import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview
import kotlinx.coroutines.flow.Flow

interface LocalDatasource {

    suspend fun saveDrink(drink: Drink)

    suspend fun getDrink(id: String): Drink?

    suspend fun deleteDrink(id: String)

    suspend fun addToFavourites(id: String)

    suspend fun removeFromFavourites(id: String)

    fun observeFavourites(): Flow<List<DrinkPreview>>

}