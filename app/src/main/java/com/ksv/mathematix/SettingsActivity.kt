package com.ksv.mathematix

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.ksv.mathematix.databinding.ActivitySettingsBinding
import com.ksv.mathematix.util.RangeOfInt
import com.ksv.mathematix.util.SettingsSet

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var exerciseType: ExerciseType
    private lateinit var settingsSet: SettingsSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val income = intent.getSerializableExtra(Values.EXERCISE_TYPE) as SettingsSet
        // Call requires API level 33 (current min is 29)
        // val income = intent.getSerializableExtra(Values.EXERCISE_TYPE, SettingsSet::class.java)
        exerciseType = income.exerciseType
        settingsSet = income

        setTitles()
        fillFields()
    }

    private fun setTitles() {
        when (exerciseType) {
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

    private fun fillFields(){
        binding.etNum1Min.setText(settingsSet.firstRange.first.toString())
        binding.etNum1Max.setText(settingsSet.firstRange.last.toString())
        binding.etNum2Min.setText(settingsSet.secondRange.first.toString())
        binding.etNum2Max.setText(settingsSet.secondRange.last.toString())
    }


    fun onClickOk(view: View) {
        val checkInputResult = checkInput()
        if (checkInputResult != ErrorCodes.OK) {
            showErrorMessage(checkInputResult)
            return
        }

        val set = makeSet()

        val checkSetResult = checkSet(set)
        if (checkSetResult != ErrorCodes.OK) {
            showErrorMessage(checkSetResult)
            return
        }

        makeIntentAndFinish(set)

    }

    private fun checkInput(): ErrorCodes {
        if (binding.etNum1Min.text.isEmpty()) return ErrorCodes.EMPTY_FIELD
        if (binding.etNum1Max.text.isEmpty()) return ErrorCodes.EMPTY_FIELD
        if (binding.etNum2Min.text.isEmpty()) return ErrorCodes.EMPTY_FIELD
        if (binding.etNum2Max.text.isEmpty()) return ErrorCodes.EMPTY_FIELD

        return ErrorCodes.OK
    }

    private fun makeSet(): SettingsSet {
        var rangeOneMin = binding.etNum1Min.text.toString().toInt()
        var rangeOneMax = binding.etNum1Max.text.toString().toInt()

        if (rangeOneMax < rangeOneMin) {
            val tmp = rangeOneMax
            rangeOneMax = rangeOneMin
            rangeOneMin = tmp
        }

        var rangeTwoMin = binding.etNum2Min.text.toString().toInt()
        var rangeTwoMax = binding.etNum2Max.text.toString().toInt()

        if (rangeTwoMax < rangeTwoMin) {
            val tmp = rangeTwoMax
            rangeTwoMax = rangeTwoMin
            rangeTwoMin = tmp
        }

        val firstRange = RangeOfInt(rangeOneMin, rangeOneMax)
        val secondRange = RangeOfInt(rangeTwoMin, rangeTwoMax)

        return SettingsSet(exerciseType, firstRange, secondRange)
    }

    private fun checkSet(set: SettingsSet): ErrorCodes {
        if (exerciseType == ExerciseType.DIVISION) {
            if (set.firstRange.first == 0)
                return ErrorCodes.DIVISION_BY_ZERO
        }


        return ErrorCodes.OK
    }

    private fun showErrorMessage(errCode: ErrorCodes) {
        val message = when (errCode) {
            ErrorCodes.EMPTY_FIELD -> "Не все поля заполнены"
            ErrorCodes.DIVISOR_OVER_RESULT -> "Делитель больше частного"
            ErrorCodes.SUBTRAHEND_OVER_RESULT -> "Вычитаемое больше разности"
            else -> "Неизвестная ошибка"
        }

        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    private fun makeIntentAndFinish(set: SettingsSet) {
        intent = Intent()
        intent.putExtra(Values.SETTINGS_RESULT, set)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    enum class ErrorCodes() {
        OK, EMPTY_FIELD, DIVISION_BY_ZERO, DIVISOR_OVER_RESULT, SUBTRAHEND_OVER_RESULT
    }

    object Errors {
        val DIVISOR_OVER_RESULT = "Делитель больше частного"
        val SUBTRAHEND_OVER_RESULT = "Вычитаемое больше разности"
    }
}
