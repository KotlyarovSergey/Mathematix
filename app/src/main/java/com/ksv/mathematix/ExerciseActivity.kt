package com.ksv.mathematix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.ksv.mathematix.databinding.ActivityExerciseBinding
import com.ksv.mathematix.model.MathExercise
import com.ksv.mathematix.model.MathGenerator
import com.ksv.mathematix.util.RangeOfInt
import com.ksv.mathematix.util.SettingsKeeper
import com.ksv.mathematix.util.SettingsSet

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exerciseType: ExerciseType
    private lateinit var settingSet: SettingsSet
    private lateinit var settingsActivityLauncher: ActivityResultLauncher<SettingsSet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exerciseType = intent.getSerializableExtra(Values.EXERCISE_TYPE) as ExerciseType

        prepareActivity()
        loadSettings()

        settingsActivityLauncher =
            registerForActivityResult(SettingsActivityActivityContract()) { result ->
                if (result != null) {
                    settingSet = result
                    saveSettings()
                }
            }

    }

    fun onClickSettings(view: View) {
        settingsActivityLauncher.launch(settingSet)
    }

    fun onClickStart(view: View) {
        val mathGenerator = MathGenerator()
        val range1 = IntRange(settingSet.firstRange.first, settingSet.firstRange.last)
        val range2 = IntRange(settingSet.secondRange.first, settingSet.secondRange.last)
        val exerciseQuantity = 10
        val exercises = when (exerciseType) {
            ExerciseType.SUMMATION -> mathGenerator.summList(range1, range2, exerciseQuantity)
            ExerciseType.SUBTRACTION -> mathGenerator.subtractionList(
                range1,
                range2,
                exerciseQuantity
            )
            ExerciseType.MULTIPLICATION -> mathGenerator.multiplicationList(
                range1,
                range2,
                exerciseQuantity
            )

            else -> mathGenerator.divisionList(range1, range2, exerciseQuantity)
        }

        val stringBuilder = StringBuilder()

        for (ex in exercises) {
            stringBuilder.append(ex.toString())
            stringBuilder.append("\n");
        }
        binding.editTextTextMultiLine.setText(stringBuilder.toString())
    }

    private fun prepareActivity() {
        when (exerciseType) {
            ExerciseType.SUMMATION -> {
//                exerciseType = ExerciseType.SUMMATION
                binding.root.setBackgroundColor(getColor(R.color.summation_color))
//                settingSet = Values.DEFAULT_SETTING_SET_SUMMATION
                binding.tvTitle.text = getString(R.string.menu_item_summation_title)
                binding.tvTitle.setTextColor(getColor(R.color.summation_color))
            }

            ExerciseType.SUBTRACTION -> {
//                exerciseType = ExerciseType.SUBTRACTION
                binding.root.setBackgroundColor(getColor(R.color.subtraction_color))
//                settingSet = Values.DEFAULT_SETTING_SET_SUBTRACTION
                binding.tvTitle.text = getString(R.string.menu_item_subtraction_title)
                binding.tvTitle.setTextColor(getColor(R.color.subtraction_color))
            }

            ExerciseType.MULTIPLICATION -> {
//                exerciseType = ExerciseType.MULTIPLICATION
                binding.root.setBackgroundColor(getColor(R.color.multiplication_color))
//                settingSet = Values.DEFAULT_SETTING_SET_MULTIPLICATION
                binding.tvTitle.text = getString(R.string.menu_item_multiplication_title)
                binding.tvTitle.setTextColor(getColor(R.color.multiplication_color))
            }

            ExerciseType.DIVISION -> {
//                exerciseType = ExerciseType.DIVISION
                binding.root.setBackgroundColor(getColor(R.color.division_color))
//                settingSet = Values.DEFAULT_SETTING_SET_DIVISION
                binding.tvTitle.text = getString(R.string.menu_item_division_title)
                binding.tvTitle.setTextColor(getColor(R.color.division_color))
            }
        }
    }

    private fun saveSettings() {
        val settingsKeeper = SettingsKeeper(this)
        settingsKeeper.save(settingSet)
    }

    private fun loadSettings() {
        val settingsKeeper = SettingsKeeper(this)
        settingSet = when (exerciseType) {
            ExerciseType.SUBTRACTION -> settingsKeeper.load(Values.DEFAULT_SETTING_SET_SUBTRACTION)
            ExerciseType.MULTIPLICATION -> settingsKeeper.load(Values.DEFAULT_SETTING_SET_MULTIPLICATION)
            ExerciseType.DIVISION -> settingsKeeper.load(Values.DEFAULT_SETTING_SET_DIVISION)
            else -> settingsKeeper.load(Values.DEFAULT_SETTING_SET_SUMMATION)
        }

//        val txt = "${settingSet.exerciseType.name} \n" +
//                "[${settingSet.firstRange.first} .. ${settingSet.firstRange.last}] \n" +
//                "[${settingSet.secondRange.first} .. ${settingSet.secondRange.last}]"
//        binding.editTextTextMultiLine.setText(txt)
    }

}

