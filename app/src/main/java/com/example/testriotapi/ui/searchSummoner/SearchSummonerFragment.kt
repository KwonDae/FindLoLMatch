package com.example.testriotapi.ui.searchSummoner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testriotapi.Common.Constants.TAG
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

    private val viewModel: SummonerViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchSummonerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SearchSummonerFragment - onCreate called / ")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "SearchSummonerFragment - onCreateView called / ")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchSummonerFragment.viewModel
        }

        binding.searchButton.setOnClickListener {
            if (binding.editText.toString().isNotEmpty()) {
//                val id = URLEncoder.encode(binding.editText.text.toString())
                viewModel.getSummonerData(binding.editText.text.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SearchSummonerFragment - onStart called / ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SearchSummonerFragment - onResume called / ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SearchSummonerFragment - onPause called / ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SearchSummonerFragment - onStop called / ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "SearchSummonerFragment - onDestroyView called / ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SearchSummonerFragment - onDestroy called / ")
    }

}