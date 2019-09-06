package com.example.quizapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.example.quizapplication.QuizContract
import com.example.quizapplication.R
import com.example.quizapplication.data.QuizRepository
import com.example.quizapplication.data.local.QuizLocalDataSource
import com.example.quizapplication.data.model.QuizQuestions
import com.example.quizapplication.presenter.QuizPresenter
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.ArrayList

/**
 * QuizActivity is used to show the question of the quiz in the app
 */
class QuizActivity : AppCompatActivity(), QuizContract.QuizView {

    lateinit var mQuizQuestions: QuizQuestions
    var mCount: Int = 0
    private var mResultArrayList = ArrayList<Int>()
    private var mRadioButtonNumber = 0
    private var mTotalScore: Int = 0
    private lateinit var mQuizPresenter: QuizPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //initializing the presenter
        mQuizPresenter = QuizPresenter(QuizRepository(QuizLocalDataSource(this)), this)
        //calling the function present in presenter
        mQuizPresenter.processGetQuestionsSet()

        setEventListener()
    }

    /**
     * setEventListeners function
     * used to set the event listener for quiz page elements
     */
    private fun setEventListener() {

        button_next.setOnClickListener {
            mCount++
            radioGroup.clearCheck()
            loadQuestions(mCount)
            if (mCount == 15) {
                button_next.visibility = View.GONE
                button_finish.visibility = View.VISIBLE
            }
        }

        button_finish.setOnClickListener {

            getScore()
            alertDialog()
        }

        radioGroup.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int ->

            when (checkedId) {
                R.id.option0 -> mRadioButtonNumber = 0
                R.id.option1 -> mRadioButtonNumber = 1
                R.id.option2 -> mRadioButtonNumber = 2
                R.id.option3 -> mRadioButtonNumber = 3
            }

            if (option0?.isChecked!! || option1?.isChecked!! || option2?.isChecked!! || option3?.isChecked!!) {
                mResultArrayList.add(mRadioButtonNumber)
            }
        }
    }

    /**
     * showing the alert dialog for showing the total score or Retake the test
     */
    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Score")
        builder.setMessage(mTotalScore.toString() + " out of " + mQuizQuestions.questions?.size)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Retake") { dialogInterface, which ->

            mTotalScore = 0
            mCount = 0
            button_next.visibility = View.VISIBLE
            button_finish.visibility = View.GONE
            loadQuestions(mCount)
        }
        builder.setNegativeButton("Cancel") { dialogInterface, which ->

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    /**
     * populating the questions in the respective elements from model class
     */
    private fun loadQuestions(i: Int) {
        text_question_number.text = (i+1).toString()+ "."
        text_question.text = mQuizQuestions.questions?.get(i)?.question
        option0.text = mQuizQuestions.questions!![i].answers?.get(0)?.option
        option1.text = mQuizQuestions.questions!![i].answers?.get(1)?.option
        option2.text = mQuizQuestions.questions!![i].answers?.get(2)?.option
        option3.text = mQuizQuestions.questions!![i].answers?.get(3)?.option
    }

    /**
     * Getting the total number of correct answers
     */
    private fun getScore() {
        mTotalScore = 0
        for (i in 0 until mResultArrayList.size) {
            if (mResultArrayList[i] == mQuizQuestions.questions!![i].correctAnswer) {
                mTotalScore += 1
            }
        }
    }

    /**
     * onQuestionSuccess method is used to show the success quiz response
     */
    override fun onQuestionSuccess(quizQuestions: QuizQuestions) {
        mQuizQuestions = quizQuestions
        loadQuestions(mCount)
    }

    /**
     * setPresenter method is used to set the presenter
     */
    override fun setPresenter(presenter: QuizContract.QuizPresenter) {
        mQuizPresenter = presenter as QuizPresenter
    }
}
