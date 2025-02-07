package com.sntsb.dexgo.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sntsb.dexgo.R
import com.sntsb.dexgo.databinding.ActivityMainBinding
import com.sntsb.dexgo.pokemon.list.PokemonListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mPokemonListFragment: PokemonListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.hide()

        mPokemonListFragment = PokemonListFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_lista_pokemon, mPokemonListFragment).commitAllowingStateLoss()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}