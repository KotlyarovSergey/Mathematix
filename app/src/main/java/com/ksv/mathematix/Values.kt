package com.ksv.mathematix

import java.io.Serializable

object Values {
    const val EXERCISE_TYPE = "EXERCISE_TYPE";
    const val SETTINGS_RESULT = "SETTINGS_RESULT"

}

enum class ExerciseType(val value: Int) {
    SUMMATION(0), SUBTRACTION(1), MULTIPLICATION(2), DIVISION(3)
}

