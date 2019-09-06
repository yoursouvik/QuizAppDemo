package com.example.quizapplication

import com.example.quizapplication.data.model.QuizQuestions

interface QuizContract {

    interface QuizView : BaseContract.View<QuizPresenter> {
        fun onQuestionSuccess(quizQuestions: QuizQuestions)
    }

    interface QuizPresenter : BaseContract.Presenter{
        fun processGetQuestionsSet()
    }
}