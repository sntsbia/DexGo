package com.sntsb.dexgo.pokemon.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
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

    private val _queryString = MutableLiveData<String>()

    val pagingData: LiveData<PagingData<PokemonDTO>> = _queryString.switchMap { param1 ->
        repository.getPager(param1).cachedIn(viewModelScope).asLiveData()
    }

    fun setQueryString(queryString: String) {
        _queryString.value = queryString
    }

    fun setLoading(loading: Boolean) {
        _loading.value = loading
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}