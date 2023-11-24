package com.vvz.pure.test.data.remote

import com.vvz.pure.test.data.remote.api.CocktailApi
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.domain.models.PureError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class RemoteDatasourceImpl constructor(private val context: CoroutineContext = Dispatchers.IO,
                                       private val api: CocktailApi) : RemoteDatasource {

    override suspend fun getCategories(): List<String> = withContext(context) {
        try {
            api
                .getList(category = "list")
                .categories
                .map { it.name }
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Network.Connection
        }
    }


    override suspend fun searchDrinks(query: String): List<DrinkPreview> = withContext(context) {
        try {
            api.searchDrinks(name = query).drinks?.map { mapDrinkPreviewToDomain(it) } ?: listOf()
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Network.Connection
        }
    }


    override suspend fun getDrinksForCategory(category: String): List<DrinkPreview> = withContext(context) {
        try {
            api
                .filterByCategory(category = category)
                .drinks?.map {
                    mapDrinkPreviewToDomain(it, category)
                } ?: listOf()
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Network.Connection
        }
    }


    override suspend fun getDrinkDetails(id: String): Drink? = withContext(context) {
        try {
            api
                .getDrinkDetails(id = id)
                .drinks?.map { mapDrinkDetailsToDomain(it) }?.firstOrNull()
        } catch (e: Throwable) {
            throw PureError.Network.Connection
        }
    }


    private fun mapDrinkPreviewToDomain(map: Map<String, String?>, category: String = ""): DrinkPreview {
        return try {
            DrinkPreview(id = checkNotNull(map["idDrink"]),
                         name = checkNotNull(map["strDrink"]),
                         category = map["strCategory"] ?: category,
                         thumbnail = map["strDrinkThumb"])
        } catch (e: Throwable) {
            throw PureError.Network.Coding(cause = e)
        }
    }


    private fun mapDrinkDetailsToDomain(map: Map<String, String?>): Drink {
        return try {

            val ingredients = (1..15).mapNotNull { order ->
                map["strIngredient$order"]?.let { it to map["strMeasure$order"] }
            }

            Drink(id = checkNotNull(map["idDrink"]),
                  name = checkNotNull(map["strDrink"]),
                  category = checkNotNull(map["strCategory"]),
                  photo = map["strDrinkThumb"],
                  type = checkNotNull(map["strAlcoholic"]),
                  glassType = checkNotNull(map["strGlass"]),
                  ingredients = ingredients)

        } catch (e: Throwable) {
            throw PureError.Network.Coding(cause = e)
        }
    }
}