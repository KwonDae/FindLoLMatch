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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private var _result = MutableLiveData<SummonerModel>()
    val result: LiveData<SummonerModel>
        get() = _result

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
//        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.KOREA)
//        val time = dateFormat.format(Date())

        viewModelScope.launch {
            summonerRepository.getRankInfo(
                encryptedSummonerId = summonerId,
                onSuccess = {
                    it.let {
                        _accountRankModel.postValue(it)

                        viewModelScope.launch {
                            summonerRepository.insertUser(
                                User(
                                    summonerId = it[0].summonerId!!,
                                    summonerName = it[0].summonerName,
                                    profileIconId = result.value?.profileIconId,
                                    summonerLevel = result.value?.summonerLevel,
                                    rank = it[0].rank,
                                    tier = it[0].tier,
                                    leaguePoints = it[0].leaguePoints,
                                    timestamp = System.currentTimeMillis(),
                                    wins = it[0].wins,
                                    losses = it[0].losses
                                )
                            )
                        }

                    }
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