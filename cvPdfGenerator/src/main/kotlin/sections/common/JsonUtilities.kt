package sections.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.Okio
import okio.buffer
import okio.source
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

internal inline fun <reified T> parse(jsonFile: File): T? = if (jsonFile.exists()) moshi.adapter(T::class.java).fromJson(
    jsonFile.source().buffer()
) else null

internal fun parseMap(jsonFile: File): Map<String, String> = moshi.adapter<Map<String, String>>(simpleMapType).fromJson(
    jsonFile.source().buffer()
)!!

internal fun Map<String, String>.getOrError(key: String): String = get(key) ?: error("No value for '$key'")
