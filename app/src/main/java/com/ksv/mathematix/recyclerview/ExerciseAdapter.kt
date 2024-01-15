package com.ksv.mathematix.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksv.mathematix.R
import com.ksv.mathematix.model.MathExercise

class ExerciseAdapter: RecyclerView.Adapter<ExerciseHolder>() {
    private val exerciseList = ArrayList<MathExercise>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseHolder(view)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    fun addExercise(exercise: MathExercise){
        exerciseList.add(exercise)
        notifyDataSetChanged()
        //notifyItemChanged(exerciseList.lastIndex)
        //notifyItemInserted(exerciseList.lastIndex)
    }
    fun clear(){
        exerciseList.clear()
        notifyDataSetChanged()
    }
}