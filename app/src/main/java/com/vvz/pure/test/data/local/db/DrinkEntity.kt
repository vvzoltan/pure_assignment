package com.vvz.pure.test.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview

@Entity(tableName = "drinks")
data class DrinkEntity(

    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("is_favourite")
    val isFavourite: Boolean,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("category")
    val category: String,

    @ColumnInfo("type")
    val type: String,

    @ColumnInfo("glass")
    val glass: String,

    @ColumnInfo("thumbnail")
    val thumbnail: String?,

    @ColumnInfo("ingredient1")
    val ingredients: IngredientList

) {
    companion object
}

fun DrinkEntity.toDomainPreview(): DrinkPreview {
    return DrinkPreview(id = id,
                        name = name,
                        category = category,
                        thumbnail = thumbnail)
}


fun DrinkEntity.toDomainModel(): Drink {
    return Drink(id = id,
                 name = name,
                 category = category,
                 photo = thumbnail,
                 type = type,
                 glassType = glass,
                 ingredients = ingredients,
                 isFavourite = isFavourite)
}


fun DrinkEntity.Companion.fromDomainModel(model: Drink): DrinkEntity {
    return DrinkEntity(id = model.id,
                       name = model.name,
                       category = model.category,
                       thumbnail = model.photo,
                       type = model.type,
                       glass = model.glassType,
                       ingredients = model.ingredients,
                       isFavourite = model.isFavourite)
}
