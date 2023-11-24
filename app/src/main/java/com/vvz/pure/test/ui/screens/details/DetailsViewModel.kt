package com.vvz.pure.test.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvz.pure.test.data.DrinksRepository
import com.vvz.pure.test.di.dagger.ViewModelAssistedFactory
import com.vvz.pure.test.domain.models.Drink
import com.vvz.pure.test.domain.models.PureError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.URLDecoder


class DetailsViewModel @AssistedInject constructor(private val repository: DrinksRepository,
                                                   @Assisted private val handle: SavedStateHandle) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<DetailsViewModel>

    private val id = URLDecoder.decode(checkNotNull(handle["id"]), "UTF-8")

    var screenState by mutableStateOf<State>(State.Loading)
        private set

    private var favouritesJob: Job? = null


    init {
        intent(Intent.LoadDrinkDetails)
    }


    fun intent(intent: Intent) {
        when (intent) {
            is Intent.LoadDrinkDetails -> loadDrink()
            is Intent.AddFavourite     -> addFavourite()
            is Intent.RemoveFavourite  -> removeFavourite()
        }
    }


    private fun loadDrink() {
        screenState = State.Loading
        viewModelScope.launch {
            screenState = try {
                val drink = repository.loadDrinkDetails(id = id)
                drink?.let { State.Loaded(drink = it) } ?: State.NotFound(id = id)
            } catch (e: PureError) {
                State.Error(error = e, id = id)
            }
        }

        favouritesJob?.cancel()
        favouritesJob = viewModelScope.launch {
            repository.observeFavourites().collectLatest { favourites ->
                (screenState as? State.Loaded)?.drink?.let { drink ->
                    screenState = State.Loaded(drink = drink.copy(isFavourite = favourites.any { it.id == drink.id }))
                }
            }
        }
        favouritesJob?.start()
    }


    private fun addFavourite() {
        viewModelScope.launch {
            repository.addToFavourites(id = id)
        }
    }


    private fun removeFavourite() {
        viewModelScope.launch {
            repository.removeFromFavourites(id = id)
        }
    }


    sealed class Intent {
        object LoadDrinkDetails : Intent()
        object AddFavourite : Intent()
        object RemoveFavourite : Intent()
    }


    sealed class State {
        object Loading : State()
        class NotFound(val id: String) : State()
        class Loaded(val drink: Drink) : State()
        class Error(val error: PureError, val id: String) : State()
    }

}