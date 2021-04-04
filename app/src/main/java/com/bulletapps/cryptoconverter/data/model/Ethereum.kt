package com.bulletapps.cryptoconverter.data.model


import com.google.gson.annotations.SerializedName

data class Ethereum(
    @SerializedName("ars")
    var ars: Int?,
    @SerializedName("brl")
    var brl: Double?,
    @SerializedName("eur")
    var eur: Double?,
    @SerializedName("last_updated_at")
    var lastUpdatedAt: Int?,
    @SerializedName("mxn")
    var mxn: Int?,
    @SerializedName("usd")
    var usd: Double?
)