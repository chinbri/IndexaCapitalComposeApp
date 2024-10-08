package com.chinbri.indexacapitalapp.domain.model

import com.google.gson.annotations.SerializedName

data class AccountInfoResponse(
    @SerializedName("instrument_accounts")
    val instrumentAccounts: List<InstrumentAccount> = emptyList(),
)

data class InstrumentAccount(
    val amount: Double = 0.0,
    val date: String = "",
    val positions: List<Position> = emptyList()
)

data class Position(
    val amount: Double = 0.0,
    @SerializedName("cost_amount")
    val costAmount: Double = 0.0,
    val date: String = "",
    val instrument: Instrument? = null,
)

data class Instrument(
    val assetClassDescription: String = "",
    val description: String = "",
    @SerializedName("isin_code")
    val isinCode: String = "",
    val name: String = "",
)

