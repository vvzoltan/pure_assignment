package com.vvz.pure.test.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.vvz.pure.test.PureApplication
import com.vvz.pure.test.data.local.db.DrinkDao
import com.vvz.pure.test.data.local.db.PureDB
import dagger.Module
import dagger.Provides

@Module
open class RoomModule {

    @Provides
    fun provideDatabase(): RoomDatabase {
        return Room.databaseBuilder(PureApplication.application.applicationContext, PureDB::class.java, "Pure_Database").build()
    }


    @Provides
    fun providesDrinkDao(database: RoomDatabase): DrinkDao {
        return (database as PureDB).drinkDao()
    }

}