package com.ksv.mathematix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.mathematix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btSummation.setOnClickListener {
            intent = Intent(this, ExerciseActivity::class.java)
//            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.SUMMATION.value)
            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.SUMMATION)
            startActivity(intent)
        }

        binding.btSubtration.setOnClickListener {
            intent = Intent(this, ExerciseActivity::class.java)
//            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.SUBTRACTION.value)
            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.SUBTRACTION)
            startActivity(intent)
        }

        binding.btMultiplicftion.setOnClickListener {
            intent = Intent(this, ExerciseActivity::class.java)
//            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.MULTIPLICATION.value)
            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.MULTIPLICATION)
            startActivity(intent)
        }

        binding.btDivision.setOnClickListener {
            intent = Intent(this, ExerciseActivity::class.java)
//            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.DIVISION.value)
            intent.putExtra(Values.EXERCISE_TYPE, ExerciseType.DIVISION)
            startActivity(intent)
        }
    }
}