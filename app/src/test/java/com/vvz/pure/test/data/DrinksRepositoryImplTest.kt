package com.vvz.pure.test.data

import com.github.javafaker.Faker
import com.vvz.pure.test.data.local.LocalDatasource
import com.vvz.pure.test.data.remote.RemoteDatasource
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.DrinkPreview
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


@DisplayName("Drinks repository")
class DrinksRepositoryImplTest {

    private lateinit var sut: DrinksRepository
    private lateinit var localDS: LocalDatasource
    private lateinit var remoteDS: RemoteDatasource
    private val faker = Faker()

    @BeforeEach
    fun setup() {
        localDS = mockk(relaxed = true)
        remoteDS = mockk(relaxed = true)
        sut = DrinksRepositoryImpl(localDatasource = localDS,
                                   remoteDatasource = remoteDS)
    }


    @Nested
    @DisplayName("Load drink details")
    inner class LoadDrinkDetails {

        private lateinit var drink: Drink

        @BeforeEach
        fun setup() {
            drink = buildDrink()
        }

        @Test
        @DisplayName("When not in database, fetched from remote source")
        fun whenNotInDBNetworkIsCalled() = runTest {
            // given
            coEvery { localDS.getDrink(any()) } returns null

            // when
            sut.loadDrinkDetails(id = drink.id)

            // then
            coVerify(exactly = 1) { remoteDS.getDrinkDetails(id = drink.id) }
        }


        @Test
        @DisplayName("When present in database, no remote call is made")
        fun whenInDBNetworkIsNotCalled() = runTest {
            // given
            coEvery { localDS.getDrink(any()) } returns drink

            // when
            sut.loadDrinkDetails(id = drink.id)

            // then
            coVerify(exactly = 0) { remoteDS.getDrinkDetails(id = drink.id) }
        }


    }


    @Nested
    @DisplayName("Observe favourites")
    inner class ObserveFavourites {

        @Test
        @DisplayName("When local datasource updates, new values are emitted")
        fun whenDBUpdateValuesAreEmitted() = runTest {
            // given
            val drink1 = buildDrinkPreview()
            val drink2 = buildDrinkPreview()
            coEvery { localDS.observeFavourites() } returns flow {
                emit(listOf())
                emit(listOf(drink1, drink2))
            }

            // when
            val result = sut.observeFavourites()

            // then
            assertEquals(2, result.count(), "Incorrect number of items returned")
            result.collectIndexed { index, value ->
                when (index) {
                    0 -> {
                        assertTrue(value.isEmpty())
                    }

                    1 -> {
                        assertEquals(2, value.count())
                        assertEquals(drink1.id, value[0].id)
                        assertEquals(drink2.id, value[1].id)
                    }
                }
            }
        }


    }


    private fun buildDrink(): Drink {
        return Drink(id = faker.internet().uuid(),
                     name = faker.beer().name(),
                     category = faker.beer().style(),
                     type = "Alcoholic",
                     glassType = "Jug/Whatever",
                     isFavourite = faker.bool().bool(),
                     photo = faker.internet().url(),
                     ingredients = listOf("Water" to "optional",
                                          "Rum" to "the more the better",
                                          "Mint" to null))
    }


    private fun buildDrinkPreview(): DrinkPreview {
        return DrinkPreview(id = faker.internet().uuid(),
                            name = faker.beer().name(),
                            category = faker.beer().style(),
                            thumbnail = faker.internet().url())
    }


}