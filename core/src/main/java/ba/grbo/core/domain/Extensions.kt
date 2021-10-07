package ba.grbo.core.domain

val String.Companion.DEFAULT: String
    get() = String.EMPTY

val Long.Companion.DEFAULT: Long
    get() = 0

val Int.Companion.DEFAULT: Int
    get() = 0

val Double.Companion.DEFAULT: Double
    get() = MIN_VALUE

val String.Companion.EMPTY: String
    get() = ""