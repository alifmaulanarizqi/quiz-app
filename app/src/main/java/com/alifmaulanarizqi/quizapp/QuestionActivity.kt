package com.alifmaulanarizqi.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvQuestion: TextView
    private lateinit var ivQuestion: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView
    private lateinit var cvImage: CardView
    private lateinit var btnSubmit: Button

    private var mCurrentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    private var canSelectOption = true
    private var mCorrectAnswers: Int = 0
    private lateinit var mUserName: String
    private lateinit var mQuestionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME_KEY).toString()

        tvQuestion = findViewById(R.id.tvQuestion)
        ivQuestion = findViewById(R.id.ivQuestion)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)
        cvImage = findViewById(R.id.cvImage)
        btnSubmit = findViewById(R.id.btnSubmit)

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionView()
        btnSubmit.visibility = View.GONE
        canSelectOption = true

        val questionPackage: Question = mQuestionList[mCurrentPosition - 1]
        tvQuestion.text = questionPackage.question
        if (questionPackage.image == 0) {
            // cvImage.layoutParams = LinearLayout.LayoutParams(0, 0) ---> set width and height of card view to 0
            cvImage.background.setTint(ContextCompat.getColor(this, R.color.primary_color))
        }
        ivQuestion.setImageResource(questionPackage.image)
        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition/${progressBar.max}"
        tvOptionOne.text = questionPackage.optionOne
        tvOptionTwo.text = questionPackage.optionTwo
        tvOptionThree.text = questionPackage.optionThree
        tvOptionFour.text = questionPackage.optionFour

        if(mCurrentPosition == mQuestionList.size)
            btnSubmit.text = "Finish"
        else
            btnSubmit.text = "Submit"
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for(option in options){
            option.setTextColor(ContextCompat.getColor(this, R.color.white3))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOption: Int) {
        defaultOptionView()
        mSelectedOptionPosition = selectedOption
        tv.setTextColor(ContextCompat.getColor(this, R.color.light_color))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer){
            1 -> { tvOptionOne.background = ContextCompat.getDrawable(this, drawableView) }
            2 -> { tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView) }
            3 -> { tvOptionThree.background = ContextCompat.getDrawable(this, drawableView) }
            4 -> { tvOptionFour.background = ContextCompat.getDrawable(this, drawableView) }
        }
    }

    private fun buttonVisible() {
        btnSubmit.visibility = View.VISIBLE
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvOptionOne -> {
                if(canSelectOption) {
                    selectedOptionView(tvOptionOne, 1)
                    buttonVisible()
                }
            }
            R.id.tvOptionTwo -> {
                if(canSelectOption) {
                    selectedOptionView(tvOptionTwo, 2)
                    buttonVisible()
                }
            }
            R.id.tvOptionThree -> {
                if(canSelectOption) {
                    selectedOptionView(tvOptionThree, 3)
                    buttonVisible()
                }
            }
            R.id.tvOptionFour -> {
                if(canSelectOption) {
                    selectedOptionView(tvOptionFour, 4)
                    buttonVisible()
                }
            }
            R.id.btnSubmit -> {

                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    if(mCurrentPosition <= mQuestionList.size)
                        setQuestion()
                    else {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME_KEY, mUserName)
                        intent.putExtra(Constants.SCORE_KEY, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTION_KEY, mQuestionList.size)
                        startActivity(intent)
                        finish()
                    }
                }
                else {
                    val question = mQuestionList[mCurrentPosition-1]
                    if(question.answer != mSelectedOptionPosition)
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_bg)
                    else
                        mCorrectAnswers++

                    answerView(question.answer, R.drawable.correct_option_bg)

                    if(mCurrentPosition == mQuestionList.size)
                        btnSubmit.text = "Finish"
                    else{
                        btnSubmit.text = "Go To Next Question"
                    }

                    mSelectedOptionPosition = 0
                    canSelectOption = false
                }

            }
        }
    }

}