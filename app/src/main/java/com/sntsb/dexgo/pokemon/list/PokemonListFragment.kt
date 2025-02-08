package com.sntsb.dexgo.pokemon.list

import android.R
import android.os.Bundle
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
import com.sntsb.dexgo.utils.StringUtils
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

        initViews()

        initObservers()

        mPokemonListViewModel.setQueryString("")

        binding.progressbar.visibility = View.GONE

    }

    private fun initObservers() {
        mPokemonListViewModel.loading.observe(viewLifecycleOwner) { loading ->
            setLoading(loading)
        }

        mPokemonListViewModel.pagingDataByPokemon.observe(viewLifecycleOwner) { pagingData ->
            if (binding.radioGroup.checkedRadioButtonId == binding.radioPokemon.id || binding.ddSearch.text.isNullOrEmpty()) {
                pokemonAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }
        mPokemonListViewModel.pagingDataByType.observe(viewLifecycleOwner) { pagingData ->
            if (binding.radioGroup.checkedRadioButtonId == binding.radioType.id && !binding.ddSearch.text.isNullOrEmpty()) {
                pokemonAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }

        mPokemonListViewModel.types.observe(viewLifecycleOwner) { types ->
            typeAdapter = TypeDropdownAdapter(
                requireContext(), types, R.layout.simple_spinner_dropdown_item
            )
            binding.ddSearch.setAdapter(typeAdapter)

            binding.ddSearch.setOnItemClickListener { parent, view, position, id ->
                val type = typeAdapter.getItem(position)
                mPokemonListViewModel.setByType(type.id.toString())
                binding.btnClear.visibility = View.VISIBLE
            }
        }

        lifecycleScope.launch {

            pokemonAdapter.loadStateFlow.collectLatest { loadStates ->
                mPokemonListViewModel.setLoading(loadStates.refresh is LoadState.Loading)
            }

        }
    }

    private fun setLoading(loading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = loading
    }

    private fun initViews() {

        initAdapter()

        initListener()

    }

    private fun initAdapter() {

        pokemonAdapter = ItemPokemonAdapter(requireContext())

        binding.rvPokemons.adapter =
            pokemonAdapter.withLoadStateFooter(footer = LoadStateAdapter(object :
                LoadStateAdapter.OnAction {
                override fun isLoading(loading: Boolean) {
                    setLoading(loading)
                }
            }))
        binding.rvPokemons.layoutManager = GridLayoutManager(requireContext(), 2)

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
        }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.radioPokemon.id -> {
                    binding.llSearch.visibility = View.VISIBLE
                    binding.llDdSearch.visibility = View.GONE
                }

                binding.radioType.id -> {
                    binding.llSearch.visibility = View.GONE
                    binding.llDdSearch.visibility = View.VISIBLE
                }
            }
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.txtSearch.text.toString()
            if (mPokemonListViewModel.searchQuery.value != query) {
                mPokemonListViewModel.setQueryString(StringUtils.allLowercase(query))
            }
        }

        binding.tilSearch.setEndIconOnClickListener {
            binding.txtSearch.setText("")
            mPokemonListViewModel.setQueryString("")
        }

        binding.btnClear.setOnClickListener {
            binding.ddSearch.setText("")
            binding.btnClear.visibility = View.GONE
            mPokemonListViewModel.setByType("")
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