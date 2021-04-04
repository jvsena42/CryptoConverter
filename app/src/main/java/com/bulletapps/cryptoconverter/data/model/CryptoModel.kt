package com.bulletapps.cryptoconverter.data.model

data class CryptoModel(
        var abbreviation:String? = "",
        var ars:Double? = 0.0,
        var brl:Double? = 0.0,
        var eur:Double? = 0.0,
        var mxn:Double? = 0.0,
        var usd:Double? = 0.0,
        var lastUpdatedAt:Int? = 0,
)
