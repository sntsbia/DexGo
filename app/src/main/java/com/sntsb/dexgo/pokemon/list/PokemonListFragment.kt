package com.sntsb.dexgo.pokemon.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sntsb.dexgo.databinding.FragmentPokemonListBinding
import com.sntsb.dexgo.pokemon.adapter.ItemPokemonAdapter
import com.sntsb.dexgo.utils.StringUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private var _binding: FragmentPokemonListBinding? = null
    private val binding: FragmentPokemonListBinding get() = _binding!!

    private lateinit var mPokemonListViewModel: PokemonListViewModel

    private lateinit var pokemonAdapter: ItemPokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonAdapter = ItemPokemonAdapter(requireContext())

        mPokemonListViewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]

        initView()

        binding.progressbar.visibility = View.GONE

        lifecycleScope.launch {
            mPokemonListViewModel.searchQuery.observe(viewLifecycleOwner) { query ->
                Log.e(TAG, "onViewCreated: $query")
                mPokemonListViewModel.getPokemonPager(StringUtils.allLowercase(query))
            }

            mPokemonListViewModel.pokemonPager.collectLatest { pagingData ->
                Log.e(TAG, "onViewCreated: ################# ${pagingData}")
                binding.progressbar.visibility = View.VISIBLE

                pokemonAdapter.submitData(pagingData)

                binding.progressbar.visibility = View.GONE
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.radioPokemon.id -> {
                    binding.tilSearch.visibility = View.VISIBLE
                    binding.tilDdSearch.visibility = View.GONE
                }

                binding.radioTipo.id -> {
                    binding.tilSearch.visibility = View.GONE
                    binding.tilDdSearch.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initView() {

        binding.rvPokemons.adapter = pokemonAdapter
        binding.rvPokemons.layoutManager = LinearLayoutManager(requireContext())

        initTextWatcher()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.progressbar.visibility = View.VISIBLE

            mPokemonListViewModel.refreshPokemonList()

            binding.swipeRefreshLayout.isRefreshing = false
            binding.progressbar.visibility = View.GONE
        }

    }

    private fun initTextWatcher() {

        binding.txtSearch.addTextChangedListener {
            mPokemonListViewModel.setSearchQuery(
                StringUtils.allLowercase(
                    it?.trim()?.toString() ?: ""
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {

        private const val TAG = "PokemonListFragment"

        @JvmStatic
        fun newInstance() = PokemonListFragment()
    }
}