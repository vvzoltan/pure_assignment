package com.vvz.pure.test.data.local

import com.vvz.pure.test.data.local.db.DrinkDao
import com.vvz.pure.test.data.local.db.DrinkEntity
import com.vvz.pure.test.data.local.db.fromDomainModel
import com.vvz.pure.test.data.local.db.toDomainModel
import com.vvz.pure.test.data.local.db.toDomainPreview
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.domain.models.PureError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class LocalDatasourceImpl(private val context: CoroutineContext = Dispatchers.IO,
                          private val dao: DrinkDao) : LocalDatasource {

    override suspend fun saveDrink(drink: Drink) = withContext(context) {
        try {
            dao.add(DrinkEntity.fromDomainModel(model = drink))
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Storage(cause = e)
        }
    }

    override suspend fun getDrink(id: String): Drink? = withContext(context) {
        try {
            dao.get(id = id)?.toDomainModel()
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Storage(cause = e)
        }
    }

    override suspend fun deleteDrink(id: String) = withContext(context) {
        try {
            dao.delete(id = id)
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Storage(cause = e)
        }
    }


    override suspend fun addToFavourites(id: String) = withContext(context) {
        try {
            dao.addToFavourites(id = id)
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Storage(cause = e)
        }
    }


    override suspend fun removeFromFavourites(id: String) = withContext(context) {
        try {
            dao.removeFromFavourites(id = id)
        } catch (e: Throwable) {
            throw (e as? PureError) ?: PureError.Storage(cause = e)
        }
    }


    override fun observeFavourites(): Flow<List<DrinkPreview>> = dao.observeFavourites().map { list ->
        list.map {
            it.toDomainPreview()
        }
    }
}