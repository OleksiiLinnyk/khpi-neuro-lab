package ua.edu.khpi.logic

import ua.edu.khpi.model.EntryPoint
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object Randomizer {
    @JvmStatic
    fun rouletteMethod(entities: List<EntryPoint>, sumF: Double, increaseF: Double): List<Int> {
        val winnerIndexes: MutableList<Int> = LinkedList()

        for (i in entities.indices) {
            val rouletteResult = getRandomDouble(0.0, 100.0)
            var currentPointer = 0.0
            for (j in entities.indices) {
                currentPointer += (entities[j].f + increaseF) / sumF * 100
                if (rouletteResult <= currentPointer) {
                    winnerIndexes.add(j)
                    break
                }
            }
        }
        return winnerIndexes
    }

    @JvmStatic
    fun getRandomDouble(min: Double, max: Double): Double {
        return (min + Math.random() * (max - min)).toDouble()
    }

    @JvmStatic
    fun getRandomInt(min: Int, max: Int): Int {
        return ThreadLocalRandom.current().nextInt(min, max + 1)
    }
}
