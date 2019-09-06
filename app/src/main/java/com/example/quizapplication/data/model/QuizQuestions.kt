package com.example.quizapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuizQuestions {

    @SerializedName("Questions")
    @Expose
    var questions: List<Question>? = null
}