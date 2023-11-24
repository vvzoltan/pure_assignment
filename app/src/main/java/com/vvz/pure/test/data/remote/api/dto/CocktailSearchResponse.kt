package com.vvz.pure.test.data.remote.api.dto

import com.squareup.moshi.Json

data class CocktailSearchResponse(@Json(name = "drinks") val drinks: List<Map<String, String?>>?)
