package com.example.quizapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Question {

    @SerializedName("Question")
    @Expose
    var question: String? = null
    @SerializedName("CorrectAnswer")
    @Expose
    var correctAnswer: Int? = null
    @SerializedName("Answers")
    @Expose
    var answers: List<Answer>? = null
}