package com.example.testriotapi.network

import com.example.testriotapi.model.AccountRankModel
import com.example.testriotapi.model.ReturnModel
import com.example.testriotapi.model.SummonerModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Daewon
 * @package com.example.testriotapi.network
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

interface ApiSummonerService {

    @GET("summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummoner(
        @Path("summonerName") summonerName : String
    ) : ApiResult<SummonerModel>

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    suspend fun getRankInfo(
        @Path("encryptedSummonerId") encryptedSummonerId: String
    ) : ApiResult<ReturnModel<AccountRankModel>>
}