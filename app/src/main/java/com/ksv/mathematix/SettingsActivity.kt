package com.ksv.mathematix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.mathematix.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var exerciseType: ExerciseType
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exerciseType = when(intent.getIntExtra(Values.EXERCISE_TYPE, -1)){
            1 -> ExerciseType.SUBTRACTION
            2 -> ExerciseType.MULTIPLICATION
            3 -> ExerciseType.DIVISION
            else -> ExerciseType.SUMMATION
        }

        setTitles()


    }

    private fun setTitles(){
        when(exerciseType){
            ExerciseType.SUMMATION -> {
                binding.tvNum1Description.text = getString(R.string.tv_number1_desc_summa)
                binding.tvNum2Description.text = getString(R.string.tv_number2_desc_summa)
                binding.toolbar.title = getString(R.string.menu_item_summation_title)
                binding.toolbar.setBackgroundColor(getColor(R.color.summation_color))
            }
            ExerciseType.SUBTRACTION -> {
                binding.tvNum1Description.text = getString(R.string.tv_number1_desc_subtract)
                binding.tvNum2Description.text = getString(R.string.tv_number2_desc_subtract)
                binding.toolbar.title = getString(R.string.menu_item_subtraction_title)
                binding.toolbar.setBackgroundColor(getColor(R.color.subtraction_color))
            }
            ExerciseType.MULTIPLICATION -> {
                binding.tvNum1Description.text = getString(R.string.tv_number1_desc_multi)
                binding.tvNum2Description.text = getString(R.string.tv_number2_desc_multi)
                binding.toolbar.title = getString(R.string.menu_item_multiplication_title)
                binding.toolbar.setBackgroundColor(getColor(R.color.multiplication_color))
            }
            ExerciseType.DIVISION -> {
                binding.tvNum1Description.text = getString(R.string.tv_number1_desc_divide)
                binding.tvNum2Description.text = getString(R.string.tv_number2_desc_divide)
                binding.toolbar.title = getString(R.string.menu_item_division_title)
                binding.toolbar.setBackgroundColor(getColor(R.color.division_color))
            }
        }
    }
}