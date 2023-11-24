package com.vvz.pure.test.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(drink: DrinkEntity)

    @Query("SELECT * FROM drinks where id = :id")
    suspend fun get(id: String): DrinkEntity?

    @Query("DELETE FROM drinks WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM drinks")
    fun observeDrinks(): Flow<List<DrinkEntity>>

    @Query("UPDATE drinks SET is_favourite = 1 WHERE id = :id")
    fun addToFavourites(id: String)

    @Query("UPDATE drinks SET is_favourite = 0 WHERE id = :id")
    fun removeFromFavourites(id: String)

    @Query("SELECT * FROM drinks WHERE is_favourite = 1 ORDER BY name ASC ")
    fun observeFavourites(): Flow<List<DrinkEntity>>

}