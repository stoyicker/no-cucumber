@file:JvmName("JsonStep")
package nocucumber.internal.step

import com.squareup.moshi.Json
import java.util.*
import javax.lang.model.element.Element
import javax.lang.model.util.Elements

internal class JsonStep private constructor(
        @Json(name = "step_name")
        private val stepName: String,
        @Json(name = "name")
        private val methodName: String,
        @Json(name ="classname")
        private val className: String) {
    companion object {
        fun fromElement(stepName: String, element: Element, elements: Elements) =
                JsonStep(stepName = ENCODER.encodeToString(stepName.toByteArray(Charsets.UTF_8)),
                        methodName = ENCODER.encodeToString(element.simpleName.toString().toByteArray(Charsets.UTF_8)),
                        className = ENCODER.encodeToString(
                                "${elements.getPackageOf(element)}.${element.enclosingElement.simpleName}"
                                        .toByteArray(Charsets.UTF_8)))
    }
}

private val ENCODER = Base64.getEncoder()