package ba.grbo.weatherforecast.framework.data.source.mapper

interface JsonMapper<DM, DTO> {
    fun toDomain(dto: DTO): DM

    fun toData(dm: DM): DTO
}