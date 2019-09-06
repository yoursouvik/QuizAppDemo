package com.example.quizapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Answer {

    @SerializedName("Answer")
    @Expose
    var option: String? = null
}