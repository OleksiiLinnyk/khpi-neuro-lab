package ua.edu.khpi.model

data class EntryPoint(
    var xRangeStart: Double,
    var xRangeEnd: Double,
    var yRangeStart: Double,
    var yRangeEnd: Double,
    var step: Double,
    var xMaxAllowed: Int,
    var yMaxAllowed: Int,
    var xByteRepresentationLength: Int,
    var yByteRepresentationLength: Int
) {

    var xBytes: String? = null
        private set
    var yBytes: String? = null
        private set
    var f = 0.0

    constructor(
        xRangeStart: Double, xRangeEnd: Double, yRangeStart: Double, yRangeEnd: Double, step: Double,
        xByteRepresentationLength: Int, yByteRepresentationLength: Int
    ) : this(
        xRangeStart,
        xRangeEnd,
        yRangeStart,
        yRangeEnd,
        step,
        ((xRangeEnd - xRangeStart) / step).toInt(),
        ((yRangeEnd - yRangeStart) / step).toInt(),
        xByteRepresentationLength,
        yByteRepresentationLength
    )

    constructor(entryProps: EntryProperties) : this(
        entryProps.xRangeStart,
        entryProps.xRangeEnd,
        entryProps.yRangeStart,
        entryProps.yRangeEnd,
        entryProps.step,
        ((entryProps.xRangeStart - entryProps.xRangeEnd) / entryProps.step).toInt(),
        ((entryProps.yRangeEnd - entryProps.yRangeStart) / entryProps.step).toInt(),
        entryProps.xByteRepresentationLength,
        entryProps.yByteRepresentationLength
    )

    fun setXBytes(s: String, previousValue: String?) {
        if (xRangeStart + step * s.toInt(2) > xRangeEnd || xRangeStart + step * s.toInt(2) < xRangeStart) {
            xBytes = previousValue
            return
        }
        if (s.length == xByteRepresentationLength) {
            xBytes = s
        }
    }

    fun setYBytes(s: String, previousValue: String?) {
        if (yRangeStart + step * s.toInt(2) > yRangeEnd || yRangeStart + step * s.toInt(2) < yRangeStart) {
            yBytes = previousValue
            return
        }
        if (s.length == yByteRepresentationLength) {
            yBytes = s
        }
    }

    var x: Double
        get() {
            val xInt = xBytes!!.toInt(2)
            return xRangeStart + step * xInt
        }
        set(x) {
            if (x > xRangeEnd || x < xRangeStart) {
                return
            }
            val xInt = ((x - xRangeStart) / step).toInt()
            val s = Integer.toBinaryString(xInt)
            xBytes = String.format("%" + xByteRepresentationLength + "s", s).replace(" ".toRegex(), "0")
        }
    var y: Double
        get() {
            val yInt = yBytes!!.toInt(2)
            return yRangeStart + step * yInt
        }
        set(y) {
            if (y > yRangeEnd || y < yRangeStart) {
                return
            }
            val yInt = ((y - yRangeStart) / step).toInt()
            val s = Integer.toBinaryString(yInt)
            yBytes = String.format("%" + yByteRepresentationLength + "s", s).replace(" ".toRegex(), "0")
        }
}
