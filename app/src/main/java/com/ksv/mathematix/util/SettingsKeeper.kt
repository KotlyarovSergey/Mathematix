package com.ksv.mathematix.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.ksv.mathematix.ExerciseType

class SettingsKeeper(private val context: Context) {
    companion object{
        const val SUMMATION_SET = "summation_set"
        const val SUBTRACTION_SET = "subtraction_set"
        const val MULTIPLICATION_SET = "multiplication_set"
        const val DIVISION_SET = "division_set"
        const val FIRST_RANGE_FIRST = "first_range_first"
        const val FIRST_RANGE_LAST = "first_range_last"
        const val SECOND_RANGE_FIRST = "second_range_first"
        const val SECOND_RANGE_LAST = "second_range_last"
    }
    fun save(set: SettingsSet){

        val setType = when(set.exerciseType){
            ExerciseType.SUBTRACTION -> SUBTRACTION_SET
            ExerciseType.MULTIPLICATION -> MULTIPLICATION_SET
            ExerciseType.DIVISION -> DIVISION_SET
            else -> SUMMATION_SET
        }

        val sharedPreferences = context.getSharedPreferences(setType, MODE_PRIVATE)
        val prefEditor = sharedPreferences.edit()

        val firstRangeFirst = set.firstRange.first
        val firstRangeLast = set.firstRange.last
        val secondRangeFirst = set.secondRange.first
        val secondRangeLast = set.secondRange.last

        prefEditor.putInt(FIRST_RANGE_FIRST, firstRangeFirst)
        prefEditor.putInt(FIRST_RANGE_LAST, firstRangeLast)
        prefEditor.putInt(SECOND_RANGE_FIRST, secondRangeFirst)
        prefEditor.putInt(SECOND_RANGE_LAST, secondRangeLast)

        prefEditor.apply()
        prefEditor.commit()
    }

    fun load(defaultSet: SettingsSet): SettingsSet {
        val setType = when (defaultSet.exerciseType) {
            ExerciseType.SUBTRACTION -> SUBTRACTION_SET
            ExerciseType.MULTIPLICATION -> MULTIPLICATION_SET
            ExerciseType.DIVISION -> DIVISION_SET
            else -> SUMMATION_SET
        }

        val sharedPreferences = context.getSharedPreferences(setType, MODE_PRIVATE)

        if(!sharedPreferences.contains(FIRST_RANGE_FIRST) ||
            !sharedPreferences.contains(FIRST_RANGE_LAST) ||
            !sharedPreferences.contains(SECOND_RANGE_FIRST) ||
            !sharedPreferences.contains(SECOND_RANGE_LAST))
            return defaultSet

        val firstRangeFirst =
            sharedPreferences.getInt(FIRST_RANGE_FIRST, defaultSet.firstRange.first)
        val firstRangeLast = sharedPreferences.getInt(FIRST_RANGE_LAST, defaultSet.firstRange.last)
        val secondRangeFirst =
            sharedPreferences.getInt(SECOND_RANGE_FIRST, defaultSet.secondRange.first)
        val secondRangeLast =
            sharedPreferences.getInt(SECOND_RANGE_LAST, defaultSet.secondRange.last)

        return SettingsSet(
            defaultSet.exerciseType,
            RangeOfInt(firstRangeFirst, firstRangeLast),
            RangeOfInt(secondRangeFirst, secondRangeLast)
        )
    }
}