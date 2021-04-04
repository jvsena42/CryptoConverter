package com.bulletapps.cryptoconverter.data.model


import com.google.gson.annotations.SerializedName

data class Litecoin(
    @SerializedName("ars")
    var ars: Double?,
    @SerializedName("brl")
    var brl: Double?,
    @SerializedName("eur")
    var eur: Double?,
    @SerializedName("last_updated_at")
    var lastUpdatedAt: Int?,
    @SerializedName("mxn")
    var mxn: Double?,
    @SerializedName("usd")
    var usd: Double?
)