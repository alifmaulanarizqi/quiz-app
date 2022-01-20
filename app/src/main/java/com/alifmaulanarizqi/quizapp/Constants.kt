package com.alifmaulanarizqi.quizapp

object Constants {
    const val USER_NAME_KEY: String = "user_name"
    const val TOTAL_QUESTION_KEY: String = "total_question"
    const val SCORE_KEY: String = "score"

    private val objectId = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    private val objectQuestion = arrayOf(
        "What planet is this?",
        "What planet is this?",
        "What planet is this?",
        "What planet is this?",
        "What planet is this?",
        "What planet is this?",
        "What planet is this?",
        "What planet is this?",
        "Satellite of earth?",
        "Characteristic of saturn?",
        "What is the name of the first planet from the sun?"
    )

    private val objectImage = arrayOf(
        R.drawable.earth,
        R.drawable.neptune,
        R.drawable.jupiter,
        R.drawable.venus,
        R.drawable.mercury,
        R.drawable.mars,
        R.drawable.saturn,
        R.drawable.uranus,
    )

    private val objectOptionOne = arrayOf(
        "Earth",
        "Venus",
        "Neptune",
        "Mercury",
        "Mercury",
        "Jupiter",
        "Mars",
        "Saturn",
        "Moon",
        "Square",
        "Mars"
    )

    private val objectOptionTwo = arrayOf(
        "Mercury",
        "Neptune",
        "Mars",
        "Saturn",
        "Venus",
        "Mars",
        "Earth",
        "Jupiter",
        "Titan",
        "Ring",
        "Moon"
    )

    private val objectOptionThree = arrayOf(
        "Jupiter",
        "Saturn",
        "Jupiter",
        "Uranus",
        "Mars",
        "Neptune",
        "Saturn",
        "Venus",
        "Ganymede",
        "Kind",
        "Mercury"
    )

    private val objectOptionFour = arrayOf(
        "Mars",
        "Uranus",
        "Saturn",
        "Venus",
        "Mercury",
        "Earth",
        "Neptune",
        "Uranus",
        "Europa",
        "Bad",
        "Pluto"
    )

    private val objectAnswer = arrayOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3)

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        for(position in objectId.indices) {
            val question = Question()

            question.id = objectId[position]
            question.question = objectQuestion[position]
            if(position <= 7)
                question.image = objectImage[position]
            question.optionOne = objectOptionOne[position]
            question.optionTwo = objectOptionTwo[position]
            question.optionThree = objectOptionThree[position]
            question.optionFour = objectOptionFour[position]
            question.answer = objectAnswer[position]

            questionList.add(question)
        }

        return questionList
    }

}