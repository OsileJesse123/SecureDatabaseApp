package com.jesse.securedatabaseapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jesse.securedatabaseapp.R
import com.jesse.securedatabaseapp.databinding.ActivityMainBinding
import com.jesse.securedatabaseapp.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            viewModel = mainViewModel
            executePendingBindings()
        }
        setContentView(binding.root)
    }
}