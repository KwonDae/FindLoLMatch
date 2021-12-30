package com.example.testriotapi.repository

import android.util.Log
import com.example.testriotapi.Common.Constants.TAG
import com.example.testriotapi.Common.RESPONSE_STATUS
import com.example.testriotapi.model.SummonerModel
import com.example.testriotapi.model.AccountRankModel
import com.example.testriotapi.network.ApiResult
import com.example.testriotapi.network.ApiSummonerService
import com.example.testriotapi.network.onError
import com.example.testriotapi.network.onSuccess
import com.example.testriotapi.util.PreferenceManager
import com.google.gson.Gson
import javax.inject.Inject

/**
 * @author Daewon
 * @package com.example.testriotapi.repository
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

class SummonerRepository @Inject constructor(
    private val apiService: ApiSummonerService,
    private val pref: PreferenceManager
) {

    suspend fun getRankInfo(
        encryptedSummonerId: String,
        onSuccess: (AccountRankModel) -> Unit,
        onError: (ApiResult.Error<*>?) -> Unit
    ) {
        val result = apiService.getRankInfo(encryptedSummonerId = encryptedSummonerId)


    }

    suspend fun getSummoner(
        userId: String,
        onSuccess: (SummonerModel) -> Unit,
        onError: (ApiResult.Error<*>?) -> Unit
    ) {
        val result = apiService.getSummoner(userId)
        Log.d(TAG, "SummonerRepository - getSummoner called / 1")
        result.onSuccess {
            Log.d(TAG, "SummonerRepository - getSummoner called / 2")
            data?.let {
                Log.d(TAG, "SummonerRepository - getSummoner called / 3")
                onSuccess(it)
            }
        }.onError {
            onError(this)
        }


    }
}