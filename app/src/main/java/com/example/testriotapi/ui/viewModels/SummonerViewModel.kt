package com.example.testriotapi.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testriotapi.db.User
import com.example.testriotapi.model.AccountRankModel
import com.example.testriotapi.model.SummonerModel
import com.example.testriotapi.repository.SummonerRepository
import com.example.testriotapi.ui.searchList.SearchListAdapter
import com.example.testriotapi.util.CommonUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
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

    private var _accountInfo = MutableLiveData<SummonerModel>()
    val accountInfo: LiveData<SummonerModel>
        get() = _accountInfo

    private var _accountRankModel = MutableLiveData<MutableList<AccountRankModel>>()
    val accountRankModel: LiveData<MutableList<AccountRankModel>>
        get() = _accountRankModel

    private var dataList = mutableListOf<User>()
    private val adapter = SearchListAdapter(this, dataList)

    fun getAdapter() = adapter

    fun getSummonerData(userId: String) {
        viewModelScope.launch {
            summonerRepository.getSummoner(
                userId = userId,
                onSuccess = {
                    it.let {
                        _accountInfo.postValue(it)
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


                        viewModelScope.launch {
                            val isGaming = summonerRepository.getActiveGames(summonerId)
                            for (model in it) {
                                if (model.queueType == "RANKED_SOLO_5x5") {
                                    summonerRepository.insertUser(
                                        User(
                                            summonerId = model.summonerId!!,
                                            summonerName = model.summonerName,
                                            profileIconId = accountInfo.value?.profileIconId,
                                            summonerLevel = accountInfo.value?.summonerLevel,
                                            rank = model.rank,
                                            tier = model.tier,
                                            leaguePoints = model.leaguePoints,
                                            timestamp = System.currentTimeMillis(),
                                            wins = model.wins,
                                            losses = model.losses,
                                            searchDate = CommonUtil.getDateFormat(),
                                            isGaming = isGaming
                                        )
                                    )
                                }
                            }
                        }
                    }
                    ""
                },
                onError = {

                }
            )
        }
    }

    private fun getDataList() {
        viewModelScope.launch {
            summonerRepository.getUser().let {
                dataList.addAll(it)
            }
            adapter.notifyDataSetChanged()
        }
    }

    fun setDataList() {
        dataList.clear()
        getDataList()
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            summonerRepository.deleteUser(user)
            setDataList()
        }
    }


}