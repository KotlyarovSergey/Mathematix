package com.ksv.mathematix.util

import com.ksv.mathematix.ExerciseType
import java.io.Serializable

data class SettingsSet(
    val exerciseType: ExerciseType,
    val firstRange: RangeOfInt,
    val secondRange: RangeOfInt
): Serializable
