package ba.grbo.weatherforecast.framework.data.source.mapper

interface Mapper<out O> {
    fun toDomain(): O
}