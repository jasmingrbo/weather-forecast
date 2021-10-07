package ba.grbo.core.domain.dm

data class Date(val start: String, val end: String) {
    companion object {
        val DEFAULT = Date(start = "January 1, 01:00", end = "January 1, 01:00")
    }
}