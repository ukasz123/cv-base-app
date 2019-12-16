package sections

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.Okio
import java.io.File

//internal class MapAdapter {
//  @FromJson fun mapFromJson(eventJson: EventJson) {
//
//  }
//}

private val simpleMapType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

internal inline fun <reified T> parse(jsonFile: File): T = moshi.adapter(T::class.java).fromJson(
    Okio.buffer(Okio.source(jsonFile))
)!!

internal fun parseMap(jsonFile: File): Map<String, String> = moshi.adapter<Map<String, String>>(simpleMapType).fromJson(
    Okio.buffer(Okio.source(jsonFile))
)!!

internal fun Map<String, String>.getOrError(key: String): String = get(key) ?: error("No value for '$key'")
