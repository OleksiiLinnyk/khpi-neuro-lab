package ua.edu.khpi.logic

import ua.edu.khpi.model.EntryPoint
import kotlin.math.pow

object Function {
    @JvmStatic
    fun calculateF(e: EntryPoint): Double {
        return -e.x.pow(2) + 3 * e.y.pow(2)
    }

    @JvmStatic
    fun sumF(entries: List<EntryPoint>): Double {
        return entries.sumOf { it.f }
    }

    @JvmStatic
    fun wholePopulationF(entries: List<EntryPoint>): Double {
        return sumF(entries) / entries.size
    }

    @JvmStatic
    fun sumFModified(entries: List<EntryPoint>, increaseEach: Double): Double {
        return entries.sumOf { it.f + increaseEach }
    }
}
