package com.example.quizapplication.presenter

import com.example.quizapplication.QuizContract
import com.example.quizapplication.data.DataSourceCallBack
import com.example.quizapplication.data.QuizDataSource
import com.example.quizapplication.data.model.QuizQuestions

class QuizPresenter(quizRepository: QuizDataSource, quizView: QuizContract.QuizView) : QuizContract.QuizPresenter{

    private val mQuizView: QuizContract.QuizView = quizView
    private val mQuizRepository: QuizDataSource = quizRepository

    //setting the presenter with the view
    init {
        mQuizView.setPresenter(this)
    }

    /**
     * processGetQuestionsSet method is used to process the questions of the quiz
     */
    override fun processGetQuestionsSet() {

        mQuizRepository.performGetQuestionsSet(object : DataSourceCallBack<QuizQuestions> {
            override fun onSuccess(data: QuizQuestions) {
                mQuizView.onQuestionSuccess(data)
            }
        })
    }

    override fun onStart() {

    }
}