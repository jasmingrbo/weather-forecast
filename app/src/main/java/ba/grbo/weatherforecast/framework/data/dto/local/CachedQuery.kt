package ba.grbo.weatherforecast.framework.data.dto.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper

@Entity(
    tableName = CachedQuery.TABLE_NAME,
    indices = [Index(value = ["value"], unique = true)]
)
data class CachedQuery(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val value: String,
) : Mapper<String> {
    override fun toDomain() = value

    companion object {
        const val TABLE_NAME = "queries_table"
    }
}