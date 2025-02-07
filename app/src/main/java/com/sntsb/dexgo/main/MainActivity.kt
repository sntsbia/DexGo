package com.sntsb.dexgo.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.sntsb.dexgo.R
import com.sntsb.dexgo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.setDisplayShowTitleEnabled(false)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        actionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.hide()

        binding.initObserver()

        binding.myViewModel = viewModel

        binding.initFields()

    }

    private fun ActivityMainBinding.initFields() {
        cgFiltro.setOnCheckedStateChangeListener { group, checkedIds ->
            Log.e(TAG, "onCreate: $checkedIds")
            if (checkedIds.isEmpty()) {
                viewModel.setSelected("")
            } else {
                findViewById<Chip>(checkedIds[0]).text?.let {
                    viewModel.setSelected(it.toString())
                }
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            progressbar.visibility = View.VISIBLE

            Snackbar.make(root, "Refresh", Snackbar.LENGTH_SHORT).show()
            viewModel.refresh()

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun ActivityMainBinding.initObserver() {
        viewModel.text.observe(this@MainActivity) { newText ->
            Log.d("MainActivity", "Novo texto: $newText")

        }
        viewModel.selected.observe(this@MainActivity) { selectedText ->
            Log.d("MainActivity", "selected: $selectedText")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}