package com.vvz.pure.test.ui.screens.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvz.pure.test.data.DrinksRepository
import com.vvz.pure.test.di.dagger.ViewModelAssistedFactory
import com.vvz.pure.test.domain.models.DrinkPreview
import com.vvz.pure.test.domain.models.PureError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch


class CategoriesViewModel @AssistedInject constructor(private val repository: DrinksRepository,
                                                      @Assisted private val handle: SavedStateHandle) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<CategoriesViewModel>

    var screenState by mutableStateOf(ScreenState())
        private set


    init {
        intent(Intent.LoadCategories)
    }


    fun intent(intent: Intent) {
        when (intent) {
            is Intent.LoadCategories -> loadCategories()
            is Intent.UpdateSearch   -> updateSearch(intent.query)
        }
    }


    private fun loadCategories() {
        screenState = screenState.copy(categories = null,
                                       error = null)
        viewModelScope.launch {
            screenState = try {
                val categories = repository.loadDrinkCategories().sorted()
                screenState.copy(categories = categories)
            } catch (e: PureError) {
                screenState.copy(categories = null,
                                 error = e)
            }
        }
    }


    private fun updateSearch(query: String) {

        screenState = screenState.copy(searchQuery = query,
                                       isSearching = query.isNotBlank(),
                                       searchResults = if (query.isBlank()) null else screenState.searchResults)

        if (query.isNotBlank()) {
            viewModelScope.launch {
                screenState = try {
                    val searchResults = repository.searchDrinks(query = query).sortedBy { it.name }
                    screenState.copy(isSearching = false,
                                     searchResults = searchResults)
                } catch (e: PureError) {
                    screenState.copy(isSearching = false,
                                     searchResults = null)
                }
            }
        }
    }


    sealed class Intent {
        object LoadCategories : Intent()
        class UpdateSearch(val query: String) : Intent()
    }

    data class ScreenState(val categories: List<String>? = null,
                           val searchQuery: String = "",
                           val isSearching: Boolean = false,
                           val searchResults: List<DrinkPreview>? = null,
                           val error: PureError? = null)
}