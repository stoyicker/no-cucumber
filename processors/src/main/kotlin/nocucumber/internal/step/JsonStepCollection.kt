package nocucumber.internal.step

import com.squareup.moshi.Json

internal class JsonStepCollection(@Json(name = "steps") val steps: List<JsonStep> = emptyList())