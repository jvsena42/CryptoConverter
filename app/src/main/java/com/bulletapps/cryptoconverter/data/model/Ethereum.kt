package com.bulletapps.cryptoconverter.data.model


import com.google.gson.annotations.SerializedName

data class Ethereum(
    @SerializedName("brl")
    var brl: Double?,
    @SerializedName("last_updated_at")
    var lastUpdatedAt: Int?,
    @SerializedName("usd")
    var usd: Double?
)