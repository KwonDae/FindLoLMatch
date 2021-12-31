package com.example.testriotapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.testriotapi.R
import com.example.testriotapi.databinding.FragmentSearchSummonerBinding
import com.example.testriotapi.ui.viewModels.SummonerViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchSummonerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchSummonerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: SummonerViewModel by viewModels()
    private lateinit var binding: FragmentSearchSummonerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_search_summoner,
            container,
            false
        )

        initBinding()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchSummonerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchSummonerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initBinding() {
        binding.apply {
            lifecycleOwner = this@SearchSummonerFragment
            viewModel = this@SearchSummonerFragment.viewModel
        }

        binding.button.setOnClickListener {
            if (binding.editText.toString().isNotEmpty()) {
//                val id = URLEncoder.encode(binding.editText.text.toString())
                viewModel.getSummonerData(binding.editText.text.toString())
            }
        }
        viewModel.accountRankModel.observe(viewLifecycleOwner) {
            it?.let {
                when (it[0].tier) {
                    "GOLD" -> {
                        binding.imageView.setImageResource(R.drawable.emblem_gold)
                    }
                    "SILVER" -> {
                        binding.imageView.setImageResource(R.drawable.emblem_silver)
                    }
                    "BRONZE" -> {
                        binding.imageView.setImageResource(R.drawable.emblem_bronze)
                    }

                    "CHALLENGER" -> {
                        binding.imageView.setImageResource(R.drawable.emblem_challenger)
                    }

                    else -> {
                        binding.imageView.setImageResource(R.drawable.emblem_grandmaster)
                    }
                }

            }
        }
    }


}