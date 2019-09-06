package com.example.quizapplication.data

interface DataSourceCallBack<T> {
    fun onSuccess(data: T)
}