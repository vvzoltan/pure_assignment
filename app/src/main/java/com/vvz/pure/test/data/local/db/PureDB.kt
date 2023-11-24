package com.vvz.pure.test.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import javax.inject.Singleton

@Singleton
@Database(entities = [DrinkEntity::class], version = 1)
@TypeConverters(IngredientsConverter::class)
abstract class PureDB : RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

}