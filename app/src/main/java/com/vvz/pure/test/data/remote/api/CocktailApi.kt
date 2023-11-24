package com.vvz.pure.test.data.remote.api

import com.vvz.pure.test.data.remote.api.dto.CategoryListResponse
import com.vvz.pure.test.data.remote.api.dto.CocktailSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailApi {

    @GET("list.php")
    suspend fun getList(@Query("c") category: String? = null): CategoryListResponse

    @GET("search.php")
    suspend fun searchDrinks(@Query("s") name: String): CocktailSearchResponse

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): CocktailSearchResponse

    @GET("lookup.php")
    suspend fun getDrinkDetails(@Query("i") id: String): CocktailSearchResponse

}