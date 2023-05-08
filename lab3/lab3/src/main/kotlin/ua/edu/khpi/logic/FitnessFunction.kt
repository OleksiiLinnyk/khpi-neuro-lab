package ua.edu.khpi.logic

import ua.edu.khpi.model.EntryPoint
import kotlin.math.pow

object FitnessFunction {
    @JvmStatic
    fun calculateFitnessFunction(e: EntryPoint): Double {
        return -e.x.pow(2) + 3 * e.y.pow(2)
    }

    @JvmStatic
    fun sumFitnessFunction(entries: List<EntryPoint>): Double {
        return entries.sumOf { it.f }
    }

    @JvmStatic
    fun wholePopulationFitnessFunction(entries: List<EntryPoint>): Double {
        return sumFitnessFunction(entries) / entries.size
    }

    @JvmStatic
    fun sumFitnessFunction(entries: List<EntryPoint>, increaseEach: Double): Double {
        return entries.sumOf { it.f + increaseEach }
    }
}
