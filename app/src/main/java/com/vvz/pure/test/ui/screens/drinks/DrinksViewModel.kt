package com.vvz.pure.test.ui.screens.drinks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvz.pure.test.data.remote.RemoteDatasource
import com.vvz.pure.test.di.dagger.ViewModelAssistedFactory
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.domain.models.PureError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.net.URLDecoder


class DrinksViewModel @AssistedInject constructor(private val remoteDatasource: RemoteDatasource,
                                                  @Assisted private val handle: SavedStateHandle) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<DrinksViewModel>

    var screenState by mutableStateOf<State>(State.Loading)
        private set


    init {
        val category = URLDecoder.decode(checkNotNull(handle["category"]), "UTF-8")
        intent(Intent.LoadDrinks(category = category))
    }

    fun intent(intent: Intent) {
        when (intent) {
            is Intent.LoadDrinks -> loadDrinks(category = intent.category)
        }
    }


    private fun loadDrinks(category: String) {
        screenState = State.Loading
        viewModelScope.launch {
            screenState = try {
                val drinks = remoteDatasource
                    .getDrinksForCategory(category = category)
                    .sortedBy { it.name }
                State.Loaded(drinks = drinks)
            } catch (e: PureError) {
                State.Error(error = e)
            }
        }
    }


    sealed class Intent {
        class LoadDrinks(val category: String) : Intent()
    }


    sealed class State {
        object Loading : State()
        class Loaded(val drinks: List<DrinkPreview>) : State()
        class Error(val error: PureError) : State()
    }

}