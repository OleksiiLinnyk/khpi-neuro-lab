package ua.edu.khpi.logic

import ua.edu.khpi.logic.Randomizer.getRandomDouble
import ua.edu.khpi.logic.Randomizer.getRandomInt
import ua.edu.khpi.model.EntryPoint
import ua.edu.khpi.model.EntryProperties

object Generation {
    @JvmStatic
    fun crossTwoEntries(
        newGenerationList: MutableList<EntryPoint>,
        props: EntryProperties?,
        e1: EntryPoint,
        e2: EntryPoint
    ) {
        val newE1 = EntryPoint(props!!)
        val newE2 = EntryPoint(props)
        val newXBites = crossBiteStrings(e1.xBytes, e2.xBytes)
        newE1.setXBytes(newXBites.component1(), e1.xBytes)
        newE2.setXBytes(newXBites.component2(), e2.xBytes)
        val newYBites = crossBiteStrings(e1.yBytes, e2.yBytes)
        newE1.setYBytes(newYBites.component1(), e1.yBytes)
        newE2.setYBytes(newYBites.component2(), e2.yBytes)
        newGenerationList.add(newE1)
        newGenerationList.add(newE2)
    }

    @JvmStatic
    fun mutateEntry(entry: EntryPoint) {
        if (getRandomDouble(0.0, 100.0) < 1.0) {
            val xCharArray = entry.xBytes!!.toCharArray()
            val indexX = getRandomInt(0, xCharArray.size - 1)
            xCharArray[indexX] = if (xCharArray[indexX] == '0') '1' else '0'
            entry.setXBytes(String(xCharArray), entry.xBytes)
        }
        if (getRandomDouble(0.0, 100.0) < 1.0) {
            val yCharArray = entry.yBytes!!.toCharArray()
            val indexY = getRandomInt(0, yCharArray.size - 1)
            yCharArray[indexY] = if (yCharArray[indexY] == '0') '1' else '0'
            entry.setYBytes(String(yCharArray), entry.yBytes)
        }
    }

    private fun crossBiteStrings(s1: String?, s2: String?): Pair<String, String> {
        val index = getRandomInt(1, s1!!.length - 1)
        val r1 = s1.substring(0, index) + s2!!.substring(index)
        val r2 = s2.substring(0, index) + s1.substring(index)
        return Pair(r1, r2)
    }
}
