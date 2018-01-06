package nocucumber.internal.step

import com.squareup.moshi.FromJson
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import java.util.*

internal class JsonStepAdapter {
    private val baseAdapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(JsonStep::class.java)

    @ToJson
    fun toJson(source: JsonStep) = baseAdapter.toJson(JsonStep(
            stepName = Base64Coder.encode(source.stepName),
            methodName = Base64Coder.encode(source.methodName),
            className = Base64Coder.encode(source.className)
    ))!!

    @FromJson
    fun fromJson(source: String) = baseAdapter.fromJson(source)?.run { JsonStep(
            stepName = Base64Coder.decode(stepName),
            methodName = Base64Coder.decode(methodName),
            className = Base64Coder.decode(className))
    }
}

internal object Base64Coder {
    private val encoder = Base64.getEncoder()
    private val decoder = Base64.getDecoder()

    fun encode(source: String) = encoder.encodeToString(source.toByteArray(Charsets.UTF_8))

    fun decode(source: String) = decoder.decode(source).toString(Charsets.UTF_8)
}
