package com.example.testriotapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.testriotapi.databinding.ActivityMainBinding
import com.example.testriotapi.util.PreferenceManager
import com.example.testriotapi.viewModel.SummonerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel by viewModels<SummonerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initBinding()
        binding.button.setOnClickListener {
            if(binding.editText.toString().isNotEmpty()) {
//                val id = URLEncoder.encode(binding.editText.text.toString())
                viewModel.getSummonerData(binding.editText.text.toString())
            }
        }

        viewModel.result.observe(this) {
            it?.let {
                when {

                }
//                when(it.rankInfo?.get(0)?.tier) {
//                    "GOLD" -> {
//                        binding.imageView.setImageResource(R.drawable.emblem_gold)
//                    }
//                    "SILVER" -> {
//                        binding.imageView.setImageResource(R.drawable.emblem_silver)
//                    }
//                    else -> {
//                        binding.imageView.setImageResource(R.drawable.emblem_challenger)
//                    }
//                }
            }
        }
    }


    private fun initBinding() {
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel

        }


    }
}