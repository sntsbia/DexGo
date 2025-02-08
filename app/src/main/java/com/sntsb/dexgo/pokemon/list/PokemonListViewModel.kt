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
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setLoading(loading: Boolean) {
        _loading.value = loading
    }

    var pokemonPager = repository.pagingSource("").flow.cachedIn(viewModelScope)

    fun getPokemonPager(query: String) {
        setLoading(true)
        pokemonPager = repository.pagingSource(query).flow.cachedIn(viewModelScope)
        setLoading(false)
    }

    fun refreshPokemonList() {
        setLoading(true)
        pokemonPager = repository.pagingSource("").flow.cachedIn(viewModelScope)

        setLoading(false)

    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}