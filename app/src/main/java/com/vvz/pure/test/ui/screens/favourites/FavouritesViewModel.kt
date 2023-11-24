package com.vvz.pure.test.ui.screens.favourites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vvz.pure.test.data.DrinksRepository
import com.vvz.pure.test.di.dagger.ViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map

class FavouritesViewModel @AssistedInject constructor(repository: DrinksRepository,
                                                      @Assisted private val handle: SavedStateHandle) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<FavouritesViewModel>

    var screenState by mutableStateOf(State())
        private set

    val drinks = repository
        .observeFavourites()
        .map { list ->
            list.filter { it.name.contains(screenState.query, true) }
        }


    fun intent(intent: Intent) {
        when (intent) {
            is Intent.UpdateQuery -> updateQuery(query = intent.query)
        }
    }


    private fun updateQuery(query: String) {
        screenState = screenState.copy(query = query)
    }


    sealed class Intent {
        class UpdateQuery(val query: String) : Intent()
    }


    data class State(val query: String = "")

}