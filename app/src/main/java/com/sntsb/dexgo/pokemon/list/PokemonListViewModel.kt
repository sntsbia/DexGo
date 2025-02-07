package com.sntsb.dexgo.pokemon.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sntsb.dexgo.pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    var pokemonPager =
        repository.getPokemonPager(searchQuery.value ?: "").flow.cachedIn(viewModelScope)

    fun getPokemonPager(query: String) {
        pokemonPager = repository.getPokemonPager(query).flow.cachedIn(viewModelScope)
    }

    fun refreshPokemonList() {
        pokemonPager = repository.restart().flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}