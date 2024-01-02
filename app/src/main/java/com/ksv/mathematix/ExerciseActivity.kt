package com.ksv.mathematix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ksv.mathematix.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exerciseType: ExerciseType
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exType = intent.getIntExtra(Values.EXERCISE_TYPE, -1)
        var txt = "Незивестон"
        when(exType){
            ExerciseType.SUMMATION.value -> {
                exerciseType = ExerciseType.SUMMATION
                txt = "Сложение"
                binding.root.setBackgroundColor(getColor(R.color.summation_color))
            }
            ExerciseType.SUBTRACTION.value -> {
                exerciseType = ExerciseType.SUBTRACTION
                txt = "Вычимтание"
                binding.root.setBackgroundColor(getColor(R.color.subtraction_color))
            }
            ExerciseType.MULTIPLICATION.value -> {
                exerciseType = ExerciseType.MULTIPLICATION
                txt = "Умножение"
                binding.root.setBackgroundColor(getColor(R.color.multiplication_color))
            }
            ExerciseType.DIVISION.value -> {
                exerciseType = ExerciseType.DIVISION
                txt = "Деление"
                binding.root.setBackgroundColor(getColor(R.color.division_color))
            }

        }
    }

    fun onClickSettings(view: View){
        intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra(Values.EXERCISE_TYPE, exerciseType.value)
        startActivity(intent)
    }

    fun onClickStart(view: View){
        val mathGenerator = MathGenerator()
        val range1 = 10..99
        val range2 = 10..999
//        val exercises = mathGenerator.summList(range1, range2, 10)
//        val exercises = mathGenerator.multiplicationList(range1, range2, 10)
//        val exercises = mathGenerator.subtractionList(range1, range2, 10)
        val exercises = mathGenerator.divisionList(range1, range2, 10)
        val stringBuilder = StringBuilder()

        for(ex in exercises) {
            stringBuilder.append(ex.toString())
            stringBuilder.append("\n");
        }
        binding.editTextTextMultiLine.setText(stringBuilder.toString())
    }

}