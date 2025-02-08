package com.sntsb.dexgo.pokemon.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import com.sntsb.dexgo.pokemon.repository.PokemonRepository
import com.sntsb.dexgo.type.dto.TypeDTO
import com.sntsb.dexgo.type.repository.TypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery

    private val _queryString = MutableLiveData<String>()

    private val _pokemonsByType = MutableLiveData<ArrayList<PokemonDTO>>()

    val pagingDataByPokemon: LiveData<PagingData<PokemonDTO>> = _queryString.switchMap { query ->
        pokemonRepository.getPager(query).cachedIn(viewModelScope).asLiveData()
    }

    val pagingDataByType: LiveData<PagingData<PokemonDTO>> = _pokemonsByType.switchMap { pokemons ->

        pokemonRepository.getPokemonByTypePager(pokemons).flow.cachedIn(viewModelScope).asLiveData()
    }

    val types: LiveData<ArrayList<TypeDTO>> = liveData {
        val types = typeRepository.getTypes()
        emit(types)
    }

    fun setByType(type: String) {

        _loading.value = true
        viewModelScope.launch {

            if (type.isEmpty()) {
                setQueryString("")

            } else {

                val pokemons = typeRepository.getByType(type)
                _pokemonsByType.value = pokemons
            }

            _loading.value = false

        }

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