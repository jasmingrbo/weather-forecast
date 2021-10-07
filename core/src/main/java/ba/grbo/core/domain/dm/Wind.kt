package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class Wind(
    val speed: Double,
    val degrees: Double,
) {
    val direction = Direction.fromDegrees(degrees)

    val description = when (speed) {
        in (0.0..0.5) -> "Calm"
        in (0.5..1.5) -> "Light air"
        in (1.5..3.3) -> "Light breeze"
        in (3.3..5.5) -> "Gentle breeze"
        in (5.5..7.9) -> "Moderate breeze"
        in (7.9..10.7) -> "Fresh breeze"
        in (10.7..13.8) -> "Strong breeze"
        in (13.8..17.1) -> "Near gale"
        in (17.1..20.7) -> "Gale"
        in (20.7..24.4) -> "Strong gale"
        in (24.4..28.4) -> "Storm"
        in (28.4..32.6) -> "Violent storm"
        in (32.6..Double.MAX_VALUE) -> "Hurricane force"
        else -> throw IllegalArgumentException("Invalid speed: $speed")
    }
    val rotationAngle = (degrees + if (degrees in 0.0..180.0) 180.0 else -180.0).toFloat()

    enum class Direction {
        N,
        NbE,
        NNE,
        NEbN,
        NE,
        NEbE,
        ENE,
        EbN,
        E,
        EbS,
        ESE,
        SEbE,
        SE,
        SEbS,
        SSE,
        SbE,
        S,
        SbW,
        SSW,
        SWbS,
        SW,
        SWbW,
        WSW,
        WbS,
        W,
        WbN,
        WNW,
        NWbW,
        NW,
        NWbN,
        NNW,
        NbW,
        Default;

        companion object {
            fun fromDegrees(degrees: Double): Direction = when (degrees) {
                in (354.376..360.0),
                in (0.0..5.625) -> N
                in (5.625..16.875) -> NbE
                in (16.875..28.125) -> NNE
                in (28.125..39.375) -> NEbN
                in (39.375..50.625) -> NE
                in (50.625..61.875) -> NEbE
                in (61.875..73.125) -> ENE
                in (73.125..84.375) -> EbN
                in (84.375..95.625) -> E
                in (95.625..106.875) -> EbS
                in (106.875..118.125) -> ESE
                in (118.125..129.375) -> SEbE
                in (129.375..140.625) -> SE
                in (140.625..151.875) -> SEbS
                in (151.875..163.125) -> SSE
                in (163.125..174.375) -> SbE
                in (174.375..185.625) -> S
                in (185.625..196.875) -> SbW
                in (196.875..208.125) -> SSW
                in (208.125..219.375) -> SWbS
                in (219.375..230.625) -> SW
                in (230.625..241.875) -> SWbW
                in (241.875..253.125) -> WSW
                in (253.125..264.375) -> WbS
                in (264.375..275.625) -> W
                in (275.625..286.875) -> WbN
                in (286.875..298.125) -> WNW
                in (298.125..309.375) -> NWbW
                in (309.375..320.625) -> NW
                in (320.625..331.875) -> NWbN
                in (331.875..343.125) -> NNW
                in (343.125..354.375) -> NbW
                in (Double.DEFAULT..Double.DEFAULT) -> Default
                else -> throw IllegalArgumentException("Unknown degrees: $degrees")
            }
        }
    }

    companion object {
        val DEFAULT = Wind(
            speed = 45.0,
            degrees = 185.0
        )
    }
}