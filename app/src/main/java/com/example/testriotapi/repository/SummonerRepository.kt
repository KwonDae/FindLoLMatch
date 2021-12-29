package com.example.testriotapi.repository

import com.example.testriotapi.Common.RESPONSE_STATUS
import com.example.testriotapi.model.AccountInfoModel
import com.example.testriotapi.model.AccountRankModel
import com.example.testriotapi.network.ApiSummonerService
import com.example.testriotapi.util.PreferenceManager
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
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
        completion: (RESPONSE_STATUS, AccountInfoModel) -> Unit
    ) {
        val result = apiService.getRankInfo(encryptedSummonerId = encryptedSummonerId).let {
            it
        }

        if (result.code() == 200) {

            var accountInfoModel: AccountInfoModel =
                AccountInfoModel(null, null, null, null, null)
            result.body()?.let {
                accountInfoModel.apply {
                    val array = it.asJsonArray
                    var inputArray = ArrayList<AccountRankModel>()
                    for (i in 0 until array.size()) {
                        inputArray.add(
                            Gson().fromJson(
                                array[i].asJsonObject,
                                AccountRankModel::class.java
                            )
                        )
                    }
                    rankInfo?.addAll(inputArray)
                }
            } ?: return
            completion(RESPONSE_STATUS.OKAY, accountInfoModel)
        } else {
            return
        }

//        val array = result.body()?.asJsonArray
//        val obj = array?.get(0)?.asJsonObject?.toString()
//        completion(RESPONSE_STATUS.OKAY, result.body()?.toString()!!)

//        if(result.code() == 200) {
//            result.body()?.let {
//                val body = it.asJsonArray
//
//            }
//        }
    }

    suspend fun getSummoner(
        userId: String,
        completion: (RESPONSE_STATUS, String) -> Unit
    ) {
        val result = apiService.getSummoner(userId).let {
            it
        } ?: return

        if (result.code() == 200) {
            result.body()?.let {
                val body = it.asJsonObject
                val id = body.get("id").asString
                completion(RESPONSE_STATUS.OKAY, id)
            } ?: return

        } else {
            result.body()?.let {
                val body = it.asJsonObject
                val id = body.get("id").asString
                completion(RESPONSE_STATUS.FAIL, id)
            } ?: return
        }

//        result.enqueue(object: retrofit2.Callback<JsonElement> {
//            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//                if(response.code() == 200) {
//                    Log.d(TAG, "SummonerRepository - onResponse called / response ${response.body()}")
//                    when(response.code()) {
//                        200 -> {
//                            response.body()?.let {
//                                val body = it.asJsonObject
//
//                                val id = body.get("id").asString
//                                completion(RESPONSE_STATUS.OKAY, id)
//                            }
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//                completion(RESPONSE_STATUS.FAIL, "fail")
//            }
//
//        })

    }
}