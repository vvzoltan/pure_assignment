package com.vvz.pure.test.data.remote.api.dto

import com.squareup.moshi.Json


data class CategoryListResponse(@Json(name = "drinks") val categories: List<CategoryDto>)


data class CategoryDto(@Json(name = "strCategory") val name: String)
