package com.example.testriotapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testriotapi.Common.RESPONSE_STATUS
import com.example.testriotapi.model.SummonerModel
import com.example.testriotapi.repository.SummonerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Daewon
 * @package com.example.testriotapi.viewModel
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@HiltViewModel
class SummonerViewModel @Inject constructor(private val summonerRepository: SummonerRepository) :
    ViewModel() {

    private var _result = MutableLiveData<SummonerModel>()
    val result: LiveData<SummonerModel>
        get() = _result

    var summonerId = MutableLiveData<String>("")

    fun getSummonerData(userId: String) {
        viewModelScope.launch {
            summonerRepository.getSummoner(
                userId = userId,
                onSuccess = {
                    it.let {
                        _result.postValue(it)
                    }
                },
                onError = {

                }
            )
        }
    }

    private fun getRankInfo(summonerId: String) {
        viewModelScope.launch {

        }
    }
}