package com.sntsb.dexgo.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

        binding.initObserver()

        binding.myViewModel = viewModel

        viewModel.setText("Olá Mundo")

        binding.btnText.setOnClickListener {
            viewModel.setText()
        }

    }

    private fun ActivityMainBinding.initObserver() {
        viewModel.text.observe(this@MainActivity) { newText ->
            Log.d("MainActivity", "Novo texto: $newText")
            tvSplash.invalidate()
        }
    }
}