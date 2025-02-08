package com.sntsb.dexgo.pokemon.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sntsb.dexgo.R
import com.sntsb.dexgo.databinding.ActivityPokemonDetailBinding
import com.sntsb.dexgo.pokemon.adapter.StatisticAdapter
import com.sntsb.dexgo.pokemon.dto.PokemonStatisticDTO
import com.sntsb.dexgo.type.adapter.TypeAdapter
import com.sntsb.dexgo.type.enums.TypeEnum
import com.sntsb.dexgo.utils.MathUtils
import com.sntsb.dexgo.utils.StringUtils
import com.sntsb.dexgo.utils.UiUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        const val POKEMON_ID = "pokemon_id"

        private const val TAG = "PokemonDetailActivity"
    }

    private lateinit var binding: ActivityPokemonDetailBinding

    private val viewModel: PokemonDetailViewModel by viewModels()

    private var typeAdapter: TypeAdapter? = null
    private var statusAdapter: StatisticAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_detail)
        supportActionBar?.hide()

        binding.toolbar.setNavigationOnClickListener {

            finish()

        }

        enableEdgeToEdge()

        lifecycleScope.launch {

            viewModel.indexVisiblePhoto.observe(this@PokemonDetailActivity) { index ->
                if (index != null) {
                    viewModel.pokemon.value?.image?.get(index)?.let {
                        Glide.with(binding.root).load(it.url).into(binding.ivPokemon)
                    }
                }
            }

            viewModel.pokemon.observe(this@PokemonDetailActivity) { pokemon ->
                Log.e(TAG, "onCreate: $pokemon")
                if (pokemon != null) {

                    initData(pokemon)
                }
            }

            viewModel.isLoading.observe(this@PokemonDetailActivity) { isLoading ->
                isLoading(isLoading)
            }

            viewModel.id.observe(this@PokemonDetailActivity) { id ->
                if (id != null) {
                    viewModel.getPokemon(id)
                }
            }

        }

        val pokemonId = intent.getIntExtra(POKEMON_ID, -1)

        viewModel.setId(pokemonId.toString())

    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressbarDetails.visibility = View.VISIBLE
        } else {
            binding.progressbarDetails.visibility = View.GONE
        }
    }

    private fun initData(pokemon: PokemonStatisticDTO) {
        with(binding) {
            Log.e(TAG, "initDados: $pokemon")
            tvDescription.text = StringUtils.capitalizeFirstLetter(pokemon.name)

            llBackground.setBackgroundResource(
                UiUtils(this@PokemonDetailActivity).getTypeColor(
                    TypeEnum.from(pokemon.typeList[0].description)
                )
            )

            tvWeight.text = buildString {
                append(MathUtils.convertHgToKg(pokemon.weight.toDouble()).let { "%.2f".format(it) })
                append(" kg")
            }
            tvHeight.text = buildString {
                append(MathUtils.convertDmToM(pokemon.height.toDouble()).let { "%.2f".format(it) })
                append(" m")
            }

            typeAdapter = TypeAdapter(pokemon.typeList, this@PokemonDetailActivity)
            rvTypes.adapter = typeAdapter
            rvTypes.layoutManager = GridLayoutManager(this@PokemonDetailActivity, 3)

            statusAdapter = StatisticAdapter(pokemon.status, this@PokemonDetailActivity)
            rvDetails.adapter = statusAdapter
            rvDetails.layoutManager = LinearLayoutManager(this@PokemonDetailActivity)

            viewModel.setIndexVisiblePhoto(0)

            ibBack.setOnClickListener {
                viewModel.indexVisiblePhoto.value?.let { index ->
                    if (index > 0) {
                        viewModel.setIndexVisiblePhoto(index - 1)
                    } else {
                        viewModel.setIndexVisiblePhoto(pokemon.image.size - 1)
                    }
                }
            }

            ibNext.setOnClickListener {
                viewModel.indexVisiblePhoto.value?.let { index ->
                    if (index < pokemon.image.size - 1) {
                        viewModel.setIndexVisiblePhoto(index + 1)
                    } else {
                        viewModel.setIndexVisiblePhoto(0)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}