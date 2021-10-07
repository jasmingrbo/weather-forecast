package ba.grbo.weatherforecast.framework.data.dto.remote.locationiq.adapters

import ba.grbo.weatherforecast.framework.data.dto.remote.locationiq.qualifiers.CountryCode
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

@Suppress("UNUSED")
object AddressAdapter {
    private const val COUNTRY_CODE = "country_code"

    @FromJson
    @CountryCode
    fun fromJson(address: Map<String?, Any?>): String? {
        return if (address.containsKey(COUNTRY_CODE)) address[COUNTRY_CODE] as String else null
    }

    @ToJson
    fun toJson(@CountryCode countryCode: String?): Map<String?, Any?> {
        return if (countryCode != null) mapOf(COUNTRY_CODE to countryCode)
        else mapOf()
    }
}