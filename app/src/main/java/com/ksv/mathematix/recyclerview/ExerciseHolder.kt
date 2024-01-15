package com.ksv.mathematix.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ksv.mathematix.ExerciseType
import com.ksv.mathematix.Values
import com.ksv.mathematix.databinding.ExerciseItemBinding
import com.ksv.mathematix.model.MathExercise

class ExerciseHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ExerciseItemBinding.bind(view)
    fun bind(exercise: MathExercise) = with(binding){
        tvNumber1.text = exercise.num1.toString()
        tvNumber2.text = exercise.num2.toString()

        tvMathSign.text = when(exercise.exType){
            ExerciseType.SUMMATION -> Values.SIGN_PLUS
            ExerciseType.DIVISION -> Values.SIGN_DIVIDE
            ExerciseType.MULTIPLICATION -> Values.SIGN_MULT
            else -> Values.SIGN_MINUS
        }
    }
}