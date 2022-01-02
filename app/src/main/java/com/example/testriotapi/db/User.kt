package com.example.testriotapi.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Daewon
 * @package com.example.testriotapi.db
 * @email green201402317@gmail.com
 * @created 2022/01/01
 */

@Entity(tableName = "user_table")
data class User(
    val summonerId: String,
    val summonerName: String? = null,
    val profileIconId: Int? = 0,
    val summonerLevel: Long? = 0L,
    val rank: String? = null,
    val tier: String? = null,
    val leaguePoints: Int?,
    val wins: Int? = 0,
    val losses: Int? = 0,
    var timestamp: Long = 0L,
    val searchDate: String?,
    val isGaming: Boolean?
) {
    @PrimaryKey(autoGenerate = false)
    var id: String = summonerId
}
