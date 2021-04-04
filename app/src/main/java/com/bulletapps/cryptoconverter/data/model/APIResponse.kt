package com.bulletapps.cryptoconverter.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("bitcoin")
    var bitcoin: Bitcoin?,
    @SerializedName("ethereum")
    var ethereum: Ethereum?,
    @SerializedName("litecoin")
    var litecoin: Litecoin?,
    @SerializedName("nano")
    var nano: Nano?
)