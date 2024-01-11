package com.ksv.mathematix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.ksv.mathematix.databinding.ActivityExerciseBinding
import com.ksv.mathematix.model.MathExercise
import com.ksv.mathematix.model.MathGenerator
import com.ksv.mathematix.util.RangeOfInt
import com.ksv.mathematix.util.SettingsSet

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exerciseType: ExerciseType
    private lateinit var settingSet: SettingsSet
    private lateinit var settingsActivityLauncher: ActivityResultLauncher<SettingsSet>
//    private lateinit var settingsActivityLauncher: ActivityResultLauncher<ExerciseType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val exType = intent.getIntExtra(Values.EXERCISE_TYPE, -1)

        exerciseType = intent.getSerializableExtra(Values.EXERCISE_TYPE) as ExerciseType

        prepareActivity()


        loadSettings()


        settingsActivityLauncher = registerForActivityResult(SettingsActivityActivityContract()) { result ->
            // используем result
            if(result != null){
                val resultText= "${result.exerciseType.name}, \n[${result.firstRange.first}..${result.firstRange.last}], [${result.secondRange.first}..${result.secondRange.last}]"
                binding.editTextTextMultiLine.setText(resultText)
            }else
                binding.editTextTextMultiLine.setText("return NULL!!")

            if(result != null){
                settingSet = result
                saveSettings()
            }
        }



    }

    fun onClickSettings(view: View){
        //val set = SettingsSet(exerciseType, RangeOfInt(10, 20), RangeOfInt(30, 40))
        settingsActivityLauncher.launch(settingSet)
    }

    fun onClickStart(view: View){
        val mathGenerator = MathGenerator()
        val range1 = IntRange(settingSet.firstRange.first, settingSet.firstRange.last)
        val range2 = IntRange(settingSet.secondRange.first, settingSet.secondRange.last)
        val exerciseQuantity = 10
        val exercises = when(exerciseType){
            ExerciseType.SUMMATION -> mathGenerator.summList(range1, range2, exerciseQuantity)
            ExerciseType.SUBTRACTION -> mathGenerator.subtractionList(range1, range2, exerciseQuantity)
            ExerciseType.MULTIPLICATION -> mathGenerator.multiplicationList(range1, range2, exerciseQuantity)
            else -> mathGenerator.divisionList(range1, range2, exerciseQuantity)
        }

        val stringBuilder = StringBuilder()

        for(ex in exercises) {
            stringBuilder.append(ex.toString())
            stringBuilder.append("\n");
        }
        binding.editTextTextMultiLine.setText(stringBuilder.toString())
    }

    private fun prepareActivity(){
        when(exerciseType){
            ExerciseType.SUMMATION -> {
//                exerciseType = ExerciseType.SUMMATION
                binding.root.setBackgroundColor(getColor(R.color.summation_color))
                settingSet = Values.DEFAULT_SETTING_SET_SUMMATION
            }
            ExerciseType.SUBTRACTION -> {
//                exerciseType = ExerciseType.SUBTRACTION
                binding.root.setBackgroundColor(getColor(R.color.subtraction_color))
                settingSet = Values.DEFAULT_SETTING_SET_SUBTRACTION
            }
            ExerciseType.MULTIPLICATION -> {
//                exerciseType = ExerciseType.MULTIPLICATION
                binding.root.setBackgroundColor(getColor(R.color.multiplication_color))
                settingSet = Values.DEFAULT_SETTING_SET_MULTIPLICATION
            }
            ExerciseType.DIVISION -> {
//                exerciseType = ExerciseType.DIVISION
                binding.root.setBackgroundColor(getColor(R.color.division_color))
                settingSet = Values.DEFAULT_SETTING_SET_DIVISION
            }
        }
    }

    private fun saveSettings(){


    }

    private fun loadSettings(){


    }

}

