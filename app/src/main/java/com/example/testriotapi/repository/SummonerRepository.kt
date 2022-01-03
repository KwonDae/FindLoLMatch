package com.example.testriotapi.repository

import android.content.SharedPreferences
import com.example.testriotapi.db.User
import com.example.testriotapi.db.UserDAO
import com.example.testriotapi.model.AccountRankModel
import com.example.testriotapi.model.SummonerModel
import com.example.testriotapi.network.ApiResult
import com.example.testriotapi.network.ApiSummonerService
import com.example.testriotapi.network.onError
import com.example.testriotapi.network.onSuccess
import javax.inject.Inject

/**
 * @author Daewon
 * @package com.example.testriotapi.repository
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

class SummonerRepository @Inject constructor(
    private val apiService: ApiSummonerService,
    private val userDao: UserDAO,
    private val pref: SharedPreferences
) {

    suspend fun getSummoner(
        userId: String,
        onSuccess: (SummonerModel) -> Unit,
        onError: (ApiResult.Error<*>?) -> Unit
    ) {
        val result = apiService.getSummoner(userId)
        result.onSuccess {
            data?.let {
                onSuccess(it)
            }
        }.onError {

            onError(this)
        }
    }

    suspend fun getRankInfo(
        encryptedSummonerId: String,
        onSuccess: (MutableList<AccountRankModel>) -> Unit,
        onError: (ApiResult.Error<*>?) -> Unit
    ) {
        val result = apiService.getRankInfo(encryptedSummonerId = encryptedSummonerId)
        result.onSuccess {
            data?.let {
                onSuccess(it)
            }
        }.onError {
            onError(this)
        }

    }

    suspend fun getActiveGames(
        encryptedSummonerId: String,
    ): Boolean {
        var value = false
        val result = apiService.getActiveGames(encryptedSummonerId = encryptedSummonerId)
        result.onSuccess {
            data?.let {
                value = true
            }
        }.onError {
            value = false
        }

        return value
    }

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    suspend fun getUser() = userDao.getAllUserSortedByDate()

}