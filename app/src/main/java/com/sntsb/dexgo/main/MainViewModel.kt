package com.sntsb.dexgo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sntsb.dexgo.pokemon.repository.PokemonRepository
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {
class MainViewModel(private val repository: PokemonRepository = PokemonRepository()) :
    ViewModel() {

    private val _text = MutableLiveData("Olá, mundo!")

    val text: LiveData<String> = _text

    fun setText() {
        _text.value = repository.getPokemonList()
    }

}