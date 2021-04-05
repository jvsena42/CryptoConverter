package com.bulletapps.cryptoconverter.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*Classe para receber a mensagem de erro*/
data class ErrorModel(
        @SerializedName("message")
    val error: String? = ""
)