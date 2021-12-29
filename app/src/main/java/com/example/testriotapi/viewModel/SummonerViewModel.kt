package com.example.testriotapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testriotapi.Common.RESPONSE_STATUS
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
class SummonerViewModel @Inject constructor(private val repository: SummonerRepository) :
    ViewModel() {

    private var _result = MutableLiveData<String>("결과테스트")
    val result: LiveData<String>
        get() = _result

    fun getSummonerData(userId: String) {
        viewModelScope.launch {
            repository.getSummoner(userId = userId, completion = { responseStatus, id ->
                when (responseStatus) {
                    RESPONSE_STATUS.OKAY -> {
//                        _result.postValue(id)
                        getRankInfo(id)
                    }

                    RESPONSE_STATUS.FAIL -> {
                        _result.postValue(id)
                    }

                    RESPONSE_STATUS.NO_CONTENT -> {

                    }
                }
            })
        }
    }

    private fun getRankInfo(summonerId: String) {
        viewModelScope.launch {
            repository.getRankInfo(encryptedSummonerId = summonerId, completion = { responseStatus, result ->
                _result.postValue(result)
            })
        }
    }
}