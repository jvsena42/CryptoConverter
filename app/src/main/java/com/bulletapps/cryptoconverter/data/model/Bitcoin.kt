package com.bulletapps.cryptoconverter.data.model


import com.google.gson.annotations.SerializedName

data class Bitcoin(
    @SerializedName("brl")
    var brl: Int?,
    @SerializedName("last_updated_at")
    var lastUpdatedAt: Int?,
    @SerializedName("usd")
    var usd: Int?
)