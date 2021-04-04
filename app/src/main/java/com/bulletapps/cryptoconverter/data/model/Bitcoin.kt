package com.bulletapps.cryptoconverter.data.model


import com.google.gson.annotations.SerializedName

data class Bitcoin(
    @SerializedName("ars")
    var ars: Int?,
    @SerializedName("brl")
    var brl: Int?,
    @SerializedName("eur")
    var eur: Int?,
    @SerializedName("last_updated_at")
    var lastUpdatedAt: Int?,
    @SerializedName("mxn")
    var mxn: Int?,
    @SerializedName("usd")
    var usd: Int?
)