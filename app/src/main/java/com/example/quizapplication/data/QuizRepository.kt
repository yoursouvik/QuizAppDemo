package com.example.quizapplication.data

import com.example.quizapplication.data.model.QuizQuestions

class QuizRepository(quizLocalDataSource: QuizDataSource): QuizDataSource {

    private var mQuizLocalDataSource: QuizDataSource = quizLocalDataSource

    override fun performGetQuestionsSet(questionsCallBack: DataSourceCallBack<QuizQuestions>) {

        mQuizLocalDataSource.performGetQuestionsSet(object : DataSourceCallBack<QuizQuestions>{
            override fun onSuccess(data: QuizQuestions) {
                questionsCallBack.onSuccess(data)
            }
        })
    }
}