package com.example.testriotapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Daewon
 * @package com.example.testriotapi.model
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@Parcelize
data class AccountInfoModel(
    var id: String?,
    var accountId: String?,
    var puuid: String?,
    var name: String?,
    var rankInfo: MutableList<AccountRankModel>?

) : Parcelable

