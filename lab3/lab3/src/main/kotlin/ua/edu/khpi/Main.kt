package ua.edu.khpi

import com.opencsv.CSVWriter
import ua.edu.khpi.logic.FitnessFunction.calculateFitnessFunction
import ua.edu.khpi.logic.FitnessFunction.sumFitnessFunction
import ua.edu.khpi.logic.FitnessFunction.wholePopulationFitnessFunction
import ua.edu.khpi.logic.Generation
import ua.edu.khpi.logic.Generation.crossTwoEntries
import ua.edu.khpi.logic.Randomizer.rouletteMethod
import ua.edu.khpi.model.EntryPoint
import ua.edu.khpi.model.EntryProperties
import java.io.FileWriter
import java.io.IOException
import java.text.DecimalFormat
import java.util.*
import java.util.function.Consumer

object Main {
    private const val FUNCTION_INCREMENT = 26.0
    private const val ERAS = 3000
    private const val CSV_FILE_PATH = "src/main/resources/results1.csv"
    private val decimalFormat = DecimalFormat("0.0")
    private val entryProperties = EntryProperties(-1.0, 5.0, -4.0, 0.0, 0.1, 6, 6)

    @JvmStatic
    fun main(args: Array<String>) {
        var entries = initialization()
        for (i in 0 until ERAS) {
            entries.forEach(Consumer { e: EntryPoint ->
                e.f = calculateFitnessFunction(e)
            })
            val results = parseResults(entries)
            appendCsv(CSV_FILE_PATH, results)

            // Cross entries
            val newGenerationEntries: MutableList<EntryPoint> = LinkedList()
            val indexesToCross = rouletteMethod(entries, sumFitnessFunction(entries, FUNCTION_INCREMENT), FUNCTION_INCREMENT)
            val iterator = indexesToCross.iterator()
            while (iterator.hasNext()) {
                val i1 = iterator.next()
                if (!iterator.hasNext()) {
                    break
                }
                val i2 = iterator.next()
                crossTwoEntries(newGenerationEntries, entryProperties, entries[i1], entries[i2])
            }

            // Mutate with 1% chance
            newGenerationEntries.forEach(Generation::mutateEntry)
            // Switch generation lists
            entries = newGenerationEntries
        }
    }

    private fun parseResults(entries: List<EntryPoint>): Array<String?> {
        val bestEntry =
            entries.stream().max { o1: EntryPoint, o2: EntryPoint -> (o1.f * 100 - o2.f * 100).toInt() }
                .orElse(null)
        return arrayOf(
            wholePopulationFitnessFunction(entries).toString(), bestEntry.f.toString(),
            bestEntry.xBytes,
            bestEntry.yBytes,
            decimalFormat.format(bestEntry.x),
            decimalFormat.format(bestEntry.y)
        )
    }

    private fun appendCsv(fileName: String, params: Array<String?>) {
        try {
            FileWriter(fileName, true).use { fileWriter ->
                CSVWriter(fileWriter).use { csvWriter ->
                    csvWriter.writeNext(params)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun initialization(): List<EntryPoint> {
        val entries: MutableList<EntryPoint> = LinkedList()
        var i = -0.5
        while (i < 4.5) {
            var j = -3.3
            while (j < -0.8) {
                val e = EntryPoint(entryProperties)
                e.x = i
                e.y = j
                entries.add(e)
                j += 0.5
            }
            i += 0.5
        }
        return entries
    }
}