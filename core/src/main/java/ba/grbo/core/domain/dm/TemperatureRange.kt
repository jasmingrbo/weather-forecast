package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class TemperatureRange(
    val minimum: Int,
    val maximum: Int
) {
    companion object {
        val DEFAULT = TemperatureRange(minimum = Int.DEFAULT, maximum = Int.DEFAULT)
    }
}
