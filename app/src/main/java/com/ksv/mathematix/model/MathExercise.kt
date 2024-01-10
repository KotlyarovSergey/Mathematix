package com.ksv.mathematix.model

import com.ksv.mathematix.ExerciseType

data class MathExercise(
    val num1: Int,
    val num2: Int,
    val exType: ExerciseType,
    val result: Int
) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        other as MathExercise
        if (num1 != other.num1) return false
        if (num2 != other.num2) return false
        if (exType != other.exType) return false
//        if (result != other.result) return false
//        return true

        return  result == other.result
    }

    override fun hashCode(): Int {
        var result = num1
        result = 31 * result + num2
        result = 31 * result + exType.hashCode()
//        result = 31 * result + result
//        return result
        return 31 * result + result
    }

    override fun toString(): String {
        return when(exType){
            ExerciseType.DIVISION -> "$num1 / $num2 = $result"
            ExerciseType.MULTIPLICATION -> "$num1 * $num2 = $result"
            ExerciseType.SUBTRACTION -> "$num1 - $num2 = $result"
            ExerciseType.SUMMATION -> "$num1 + $num2 = $result"
        }
    }
}