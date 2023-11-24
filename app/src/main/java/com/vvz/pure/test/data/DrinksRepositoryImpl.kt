package com.vvz.pure.test.data

import com.vvz.pure.test.data.local.LocalDatasource
import com.vvz.pure.test.data.remote.RemoteDatasource
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class DrinksRepositoryImpl constructor(private val context: CoroutineContext = Dispatchers.IO,
                                       private val localDatasource: LocalDatasource,
                                       private val remoteDatasource: RemoteDatasource) : DrinksRepository {

    override suspend fun loadDrinkCategories(): List<String> = withContext(context) {
        remoteDatasource.getCategories()
    }

    override suspend fun loadDrinksForCategory(category: String): List<DrinkPreview> = withContext(context) {
        remoteDatasource.getDrinksForCategory(category = category)
    }

    override suspend fun searchDrinks(query: String): List<DrinkPreview> = withContext(context) {
        remoteDatasource.searchDrinks(query = query)
    }

    override suspend fun loadDrinkDetails(id: String): Drink? = withContext(context) {
        val local = localDatasource.getDrink(id = id)
        if (local == null) {
            val remote = remoteDatasource.getDrinkDetails(id = id)
            if (remote != null) localDatasource.saveDrink(remote)
            remote
        } else {
            local
        }
    }

    override suspend fun addToFavourites(id: String) = withContext(context) {
        localDatasource.addToFavourites(id = id)
    }

    override suspend fun removeFromFavourites(id: String) = withContext(context) {
        localDatasource.removeFromFavourites(id = id)
    }

    override fun observeFavourites(): Flow<List<DrinkPreview>> = localDatasource.observeFavourites()

}