package com.sntsb.dexgo.main

import android.util.Log
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

    fun setText(novoTexto: String = repository.getPokemonList()) {

        _text.value = novoTexto
        Log.e(TAG, "setTexto: ${text.value}")
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}