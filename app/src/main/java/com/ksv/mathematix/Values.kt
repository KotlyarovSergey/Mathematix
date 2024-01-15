package com.ksv.mathematix

import com.ksv.mathematix.util.RangeOfInt
import com.ksv.mathematix.util.SettingsSet
import java.io.Serializable

object Values {
    const val EXERCISE_TYPE = "EXERCISE_TYPE";
    const val SETTINGS_RESULT = "SETTINGS_RESULT"

    val DEFAULT_SETTING_SET_SUMMATION = SettingsSet(ExerciseType.SUMMATION, RangeOfInt(50, 99), RangeOfInt(10, 50))
    val DEFAULT_SETTING_SET_SUBTRACTION = SettingsSet(ExerciseType.SUBTRACTION, RangeOfInt(11, 99), RangeOfInt(10, 99))
    val DEFAULT_SETTING_SET_MULTIPLICATION = SettingsSet(ExerciseType.MULTIPLICATION, RangeOfInt(10, 99), RangeOfInt(10, 50))
    val DEFAULT_SETTING_SET_DIVISION = SettingsSet(ExerciseType.DIVISION, RangeOfInt(10, 99), RangeOfInt(2, 50))

    const val SIGN_PLUS = "+"
    const val SIGN_MINUS = "-"
    const val SIGN_MULT = "·"
    const val SIGN_DIVIDE = ":"

    const val exercisesCount = 10
}

enum class ExerciseType(val value: Int) {
    SUMMATION(0), SUBTRACTION(1), MULTIPLICATION(2), DIVISION(3)
}

