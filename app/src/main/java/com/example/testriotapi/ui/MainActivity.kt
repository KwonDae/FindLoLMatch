package com.example.testriotapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testriotapi.R
import com.example.testriotapi.ui.viewModels.SummonerViewModel
import com.example.testriotapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel by viewModels<SummonerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initBinding()
        setSupportActionBar(binding.toolbar)
        binding.bottomNavigationView.setupWithNavController(binding.navHostFragment.findNavController())
    }


    private fun initBinding() {
        binding.apply {
            lifecycleOwner = this@MainActivity

        }
//        binding.bottomNavigationView.setupWithNavController()
//        binding.bottomNavigationView.setupWithNavController(binding.nav.findNavController())



    }
}