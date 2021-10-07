package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class Temperature(
    val measured: Int,
    val feelsLike: Int
) {
    companion object {
        val DEFAULT = Temperature(
            measured = Int.DEFAULT,
            feelsLike = Int.DEFAULT
        )
    }
}