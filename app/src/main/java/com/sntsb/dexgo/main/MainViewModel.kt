package com.sntsb.dexgo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sntsb.dexgo.pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _selected = MutableLiveData<String>()
    val selected: LiveData<String> = _selected

    fun setSelected(novoTexto: String) {
        _selected.value = novoTexto
    }

    fun refresh() {
        _text.value = ""
        _selected.value = ""
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}