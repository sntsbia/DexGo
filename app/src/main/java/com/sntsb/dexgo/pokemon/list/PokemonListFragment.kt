package com.sntsb.dexgo.pokemon.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.sntsb.dexgo.databinding.FragmentPokemonListBinding
import com.sntsb.dexgo.pokemon.adapter.ItemPokemonAdapter
import com.sntsb.dexgo.type.adapter.TypeDropdownAdapter
import com.sntsb.dexgo.type.enums.TypeEnum
import com.sntsb.dexgo.utils.StringUtils
import com.sntsb.dexgo.utils.UiUtils
import com.sntsb.dexgo.utils.adapter.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private var _binding: FragmentPokemonListBinding? = null
    private val binding: FragmentPokemonListBinding get() = _binding!!

    private lateinit var mPokemonListViewModel: PokemonListViewModel

    private lateinit var pokemonAdapter: ItemPokemonAdapter
    private lateinit var typeAdapter: TypeDropdownAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPokemonListViewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]

        pokemonAdapter = ItemPokemonAdapter(requireContext())

        initObservers()

        initViews()

        mPokemonListViewModel.setQueryString("")

        binding.progressbar.visibility = View.GONE

    }

    private fun initObservers() {
        lifecycleScope.launch {

            mPokemonListViewModel.loading.observe(viewLifecycleOwner) { loading ->
                setLoading(loading)
            }

            mPokemonListViewModel.pagingData.observe(viewLifecycleOwner) { pagingData ->
                pokemonAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }

            pokemonAdapter.loadStateFlow.collectLatest { loadStates ->
                mPokemonListViewModel.setLoading(loadStates.refresh is LoadState.Loading)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = loading
    }

    private fun initViews() {

        initDropdown()

        initAdapter()

        initListener()

    }

    private fun initAdapter() {

        binding.rvPokemons.adapter =
            pokemonAdapter.withLoadStateFooter(footer = LoadStateAdapter(object :
                LoadStateAdapter.OnAction {
                override fun isLoading(loading: Boolean) {
                    setLoading(loading)
                }
            }))
        binding.rvPokemons.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    private fun initDropdown() {

        val tipos = TypeEnum.entries.map {
            UiUtils(requireContext()).getTipoLabel(it)
        }.toList().let {
            ArrayList(it)
        }

        tipos.sort()

        typeAdapter = TypeDropdownAdapter(
            requireContext(),
            tipos,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        )
        binding.ddSearch.setAdapter(typeAdapter)
        typeAdapter.notifyDataSetChanged()

        binding.ddSearch.setOnItemClickListener { parent, view, position, id ->
            val tipo = typeAdapter.getItem(position)

            Log.e(TAG, "initDropdown: $tipo")


        }

    }

    private fun initListener() {

        pokemonAdapter.addLoadStateListener { loadStates ->
            val isEmpty =
                pokemonAdapter.itemCount == 0 && loadStates.refresh is LoadState.NotLoading
            if (isEmpty) {
                binding.tvEmptyList.visibility = View.VISIBLE
                binding.rvPokemons.visibility = View.GONE
            } else {
                binding.tvEmptyList.visibility = View.GONE
                binding.rvPokemons.visibility = View.VISIBLE
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {

            mPokemonListViewModel.setQueryString("")
            binding.rvPokemons.layoutManager?.scrollToPosition(0)

            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.radioPokemon.id -> {
                    binding.llSearch.visibility = View.VISIBLE
                    binding.tilDdSearch.visibility = View.GONE
                }

                binding.radioTipo.id -> {
                    binding.llSearch.visibility = View.GONE
                    binding.tilDdSearch.visibility = View.VISIBLE
                }
            }
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.txtSearch.text.toString()
            if (mPokemonListViewModel.searchQuery.value != query) {
                mPokemonListViewModel.setQueryString(StringUtils.allLowercase(query))
            }
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