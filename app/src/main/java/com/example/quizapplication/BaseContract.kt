package com.example.quizapplication

interface BaseContract {

    interface View<in T : Presenter> {
        fun setPresenter(presenter: T)
    }

    interface Presenter {
        fun onStart()
    }
}