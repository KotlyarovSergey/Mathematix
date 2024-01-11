package com.ksv.mathematix

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.ksv.mathematix.util.SettingsSet

//class SettingsActivityActivityContract : ActivityResultContract<ExerciseType, SettingsSet?>() {
class SettingsActivityActivityContract : ActivityResultContract<SettingsSet, SettingsSet?>() {
//    override fun createIntent(context: Context, input: ExerciseType): Intent {
    override fun createIntent(context: Context, input: SettingsSet): Intent {
        return Intent(context, SettingsActivity::class.java)
            .putExtra(Values.EXERCISE_TYPE, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): SettingsSet?{
//        Log.i("ksvlog", "Activity.RESULT: ${Activity.RESULT_OK.toString()}")
//
//        if(intent == null){
//            Log.i("ksvlog", "RESULT: Intent is null")
//        }

        val result = when {
            resultCode != Activity.RESULT_OK -> null
            else -> intent?.getSerializableExtra(Values.SETTINGS_RESULT) as SettingsSet?
        }
//        if(result == null)
//            Log.i("ksvlog", "RESULT is null")
//        else
//            Log.i("ksvlog", "RESULT: ${result.exerciseType.name}")
        return result
    }

}