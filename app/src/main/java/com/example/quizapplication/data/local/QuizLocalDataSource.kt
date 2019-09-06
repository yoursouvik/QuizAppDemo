package com.example.quizapplication.data.local

import android.content.Context
import com.example.quizapplication.R
import com.example.quizapplication.data.DataSourceCallBack
import com.example.quizapplication.data.QuizDataSource
import com.example.quizapplication.data.model.QuizQuestions
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class QuizLocalDataSource(context: Context) : QuizDataSource {

    lateinit var mQuizQuestions: QuizQuestions
    private var mContext: Context = context

    /**
     * performGetQuestionsSet method is used for parsing the json and setting it into the model class
     */
    override fun performGetQuestionsSet(questionsCallBack: DataSourceCallBack<QuizQuestions>) {

        //fetching the json file from raw package and initializing in json variable
        val json = inputStreamToString(mContext.resources.openRawResource(R.raw.question))
        //Gson provide fromJson() methods to convert Kotlin objects from JSON.
        mQuizQuestions = Gson().fromJson(json, QuizQuestions::class.java)
        questionsCallBack.onSuccess(mQuizQuestions)
    }

    private fun inputStreamToString(inputStream: InputStream): String {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            e.toString()
        }
    }
}