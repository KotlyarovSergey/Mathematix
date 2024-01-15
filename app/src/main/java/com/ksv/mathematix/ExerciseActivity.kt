package com.ksv.mathematix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import com.ksv.mathematix.databinding.ActivityExerciseBinding
import com.ksv.mathematix.model.MathGenerator
import com.ksv.mathematix.recyclerview.ExerciseAdapter
import com.ksv.mathematix.util.SettingsKeeper
import com.ksv.mathematix.util.SettingsSet

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exerciseType: ExerciseType
    private lateinit var settingSet: SettingsSet
    private lateinit var settingsActivityLauncher: ActivityResultLauncher<SettingsSet>
    private val rcAdapter = ExerciseAdapter()


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

        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = rcAdapter

    }

    fun onClickSettings(view: View) {
        settingsActivityLauncher.launch(settingSet)
    }

    fun onClickStart(view: View) {
        val mathGenerator = MathGenerator()
        val firstRange = IntRange(settingSet.firstRange.first, settingSet.firstRange.last)
        val secondRange = IntRange(settingSet.secondRange.first, settingSet.secondRange.last)
        val exercises = when (exerciseType) {
            ExerciseType.MULTIPLICATION -> mathGenerator.multiplicationList(
                firstRange,
                secondRange,
                Values.exercisesCount
            )

            ExerciseType.DIVISION -> mathGenerator.divisionList(
                firstRange,
                secondRange,
                Values.exercisesCount
            )

            ExerciseType.SUMMATION -> mathGenerator.summList(
                firstRange,
                secondRange,
                Values.exercisesCount
            )

            else -> mathGenerator.subtractionList(firstRange, secondRange, Values.exercisesCount)
        }

        rcAdapter.clear()
        for (ex in exercises) {
            rcAdapter.addExercise(ex)
        }

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

