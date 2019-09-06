package com.example.quizapplication.data

import com.example.quizapplication.data.model.QuizQuestions

interface QuizDataSource {
    fun performGetQuestionsSet(questionsCallBack: DataSourceCallBack<QuizQuestions>)
}