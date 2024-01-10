package com.ksv.mathematix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.ksv.mathematix.databinding.ActivityExerciseBinding
import com.ksv.mathematix.model.MathGenerator

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exerciseType: ExerciseType
    private lateinit var settingsActivityLauncher: ActivityResultLauncher<ExerciseType>

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

        settingsActivityLauncher = registerForActivityResult(SettingsActivityActivityContract()) { result ->
            // используем result
//            binding.editTextTextMultiLine.setText(result.toString())
            if(result != null){
                val resultText= "${result.exerciseType.name}, \n[${result.firstRange.first}..${result.firstRange.last}], [${result.secondRange.first}..${result.secondRange.last}]"
                binding.editTextTextMultiLine.setText(resultText)
            }else
                binding.editTextTextMultiLine.setText("return NULL!!")
        }



    }

    fun onClickSettings(view: View){
//        intent = Intent(this, SettingsActivity::class.java)
//        intent.putExtra(Values.EXERCISE_TYPE, exerciseType.value)
//        startActivity(intent)

        settingsActivityLauncher.launch(exerciseType)

//        intent = Intent(this, SettingsActivity::class.java)
//        val set = SettingsSet(exerciseType, IntRange(10,20), IntRange(20,30))
//        intent.putExtra(Values.SETTINGS_RESULT, set)
//        startActivity(intent)
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

