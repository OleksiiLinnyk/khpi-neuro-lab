package ua.edu.khpi.model

data class EntryProperties(
    val xRangeStart: Double,
    val xRangeEnd: Double,
    val yRangeStart: Double,
    val yRangeEnd: Double,
    val step: Double,
    val xByteRepresentationLength: Int,
    val yByteRepresentationLength: Int
)
