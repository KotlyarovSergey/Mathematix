package com.ksv.mathematix.util

import java.io.Serializable

data class RangeOfInt(
    val first: Int,
    val last: Int
) : Serializable {
    fun random(): Int{
        if(first > last)
            return (last..first).random()

        return (first..last).random()
    }
}