package com.example.testriotapi.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testriotapi.model.AccountRankModel
import com.example.testriotapi.model.SummonerModel
import com.example.testriotapi.repository.SummonerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Daewon
 * @package com.example.testriotapi.ui.viewModel
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@HiltViewModel
class SummonerViewModel @Inject constructor(private val summonerRepository: SummonerRepository) :
    ViewModel() {

    private var _result = MutableLiveData<SummonerModel>()
    val result: LiveData<SummonerModel>
        get() = _result

    private var _accountRankModel = MutableLiveData<MutableList<AccountRankModel>>()
    val accountRankModel : LiveData<MutableList<AccountRankModel>>
        get() = _accountRankModel

    fun getSummonerData(userId: String) {
        viewModelScope.launch {
            summonerRepository.getSummoner(
                userId = userId,
                onSuccess = {
                    it.let {
                        _result.postValue(it)
                        getRankInfo(it.id.toString())
                    }
                },
                onError = {

                }
            )
        }
    }

    private fun getRankInfo(summonerId: String) {
        viewModelScope.launch {
            summonerRepository.getRankInfo(
                encryptedSummonerId = summonerId,
                onSuccess = {
                    it.let {
                        _accountRankModel.postValue(it)
                    }
                },
                onError = {

                }
            )
        }
    }
}