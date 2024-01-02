package com.ksv.mathematix

class MathGenerator {

    fun summOnce(summand: Int, addend: Int): MathExercise{
        val amount = summand + addend
        return MathExercise(summand, addend, ExerciseType.SUMMATION, amount)
    }

    fun summList(summandRange: IntRange, addendRange: IntRange, count: Int): List<MathExercise>{
        val result = mutableListOf<MathExercise>()
        val maxResultCount = getMaxResultCount(summandRange, addendRange, count)

        while(result.size < maxResultCount){
            val summand = summandRange.random()
            val addend = addendRange.random()
            val amount = summand + addend
            val exercise = MathExercise(summand, addend, ExerciseType.SUMMATION, amount)

            if(checkUnique(exercise, result))
                result.add(exercise)
        }

        return  result
    }

    fun multiplicationList(firstMultiplierRange: IntRange, secondMultiplierRange: IntRange, count: Int): List<MathExercise>{
        val result = mutableListOf<MathExercise>()
        val maxResultCount = getMaxResultCount(firstMultiplierRange, secondMultiplierRange, count)

        while(result.size < maxResultCount){
            val firstMultiplier = firstMultiplierRange.random()
            val secondMultiplier = secondMultiplierRange.random()
            val multResult = firstMultiplier * secondMultiplier
            val exercise = MathExercise(firstMultiplier, secondMultiplier, ExerciseType.MULTIPLICATION, multResult)

            if(checkUnique(exercise, result))
                result.add(exercise)
        }

        return  result
    }


    fun subtractionList(subtrahendRange: IntRange, differenceRange: IntRange, count: Int): List<MathExercise>{
        val result = mutableListOf<MathExercise>()
        val maxResultCount = getMaxResultCount(subtrahendRange, differenceRange, count)

        while (result.size < maxResultCount){
            val subtrahend = subtrahendRange.random()
            val difference = differenceRange.random()
            val reduced = subtrahend + difference
            val exercise = MathExercise(reduced, subtrahend, ExerciseType.SUBTRACTION, difference)

            if(checkUnique(exercise, result))
                result.add(exercise)
        }
        return result
    }

    fun divisionList(divisorRange: IntRange, divisoinResultRange: IntRange, count: Int): List<MathExercise>{
        val result = mutableListOf<MathExercise>()
        val maxResultCount = getMaxResultCount(divisorRange, divisoinResultRange, count)

        while (result.size < maxResultCount){
            val divisor = divisorRange.random()
            val divisionResult = divisoinResultRange.random()
            val divisible = divisor * divisionResult
            val exercise = MathExercise(divisible, divisor, ExerciseType.DIVISION, divisionResult)

            if(checkUnique(exercise, result))
                result.add(exercise)
        }
        return result
    }

    private fun getMaxResultCount(range1: IntRange, range2: IntRange, count: Int): Int{
        val all = listOf(range1.count(), range2.count(), count)
        return all.min()
//        if(range1.count() < range2.count())
//            return range1.count()
//        return range2.count()

    }
    private fun checkUnique(exercise: MathExercise, list: List<MathExercise>): Boolean{
        for(itm in list){
            if(exercise.num1 == itm.num1) return false
            if(exercise.num2 == itm.num2) return false
            if(exercise.num1 == itm.num2 && exercise.num2 == itm.num1) return false
        }
        return true
    }
}