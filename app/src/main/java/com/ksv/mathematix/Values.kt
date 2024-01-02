package com.ksv.mathematix

object Values {
    const val EXERCISE_TYPE = "EXERCISE_TYPE";


}

enum class ExerciseType(val value: Int) {
    SUMMATION(0), SUBTRACTION(1), MULTIPLICATION(2), DIVISION(3)
}
