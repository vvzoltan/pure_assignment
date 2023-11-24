package com.vvz.pure.test.di

import com.vvz.pure.test.data.DrinksRepository
import com.vvz.pure.test.data.DrinksRepositoryImpl
import com.vvz.pure.test.data.local.LocalDatasource
import com.vvz.pure.test.data.local.LocalDatasourceImpl
import com.vvz.pure.test.data.local.db.DrinkDao
import com.vvz.pure.test.data.remote.RemoteDatasource
import com.vvz.pure.test.data.remote.RemoteDatasourceImpl
import com.vvz.pure.test.data.remote.api.CocktailApi
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun providesRemoteDataSource(api: CocktailApi): RemoteDatasource = RemoteDatasourceImpl(api = api)

    @Provides
    fun providesLocalDataSource(dao: DrinkDao): LocalDatasource = LocalDatasourceImpl(dao = dao)

    @Provides
    fun providesRepository(localDatasource: LocalDatasource,
                           remoteDatasource: RemoteDatasource): DrinksRepository = DrinksRepositoryImpl(localDatasource = localDatasource,
                                                                                                        remoteDatasource = remoteDatasource)

}