package com.vvz.pure.test.di

import com.vvz.pure.test.di.dagger.DaggerViewModelAssistedFactory
import com.vvz.pure.test.di.dagger.ViewModelAssistedFactory
import com.vvz.pure.test.di.dagger.ViewModelAssistedFactoryKey
import com.vvz.pure.test.di.dagger.ViewModelFactory
import com.vvz.pure.test.ui.screens.categories.CategoriesViewModel
import com.vvz.pure.test.ui.screens.details.DetailsViewModel
import com.vvz.pure.test.ui.screens.drinks.DrinksViewModel
import com.vvz.pure.test.ui.screens.favourites.FavouritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindsDaggerViewModelAssistedFactory(factory: DaggerViewModelAssistedFactory): ViewModelFactory

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(CategoriesViewModel::class)]
    fun bindsCategoriesViewModelFactory(factory: CategoriesViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(DrinksViewModel::class)]
    fun bindsDrinksViewModelFactory(factory: DrinksViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(DetailsViewModel::class)]
    fun bindsDetailsViewModelFactory(factory: DetailsViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(FavouritesViewModel::class)]
    fun bindsFavouritesViewModelFactory(factory: FavouritesViewModel.Factory): ViewModelAssistedFactory<*>
}