package nocucumber.internal.step

import com.squareup.moshi.Json
import javax.lang.model.element.Element
import javax.lang.model.util.Elements

internal class JsonStep (
        @Json(name = "step_name")
        val stepName: String,
        @Json(name = "name")
        val methodName: String,
        @Json(name ="classname")
        val className: String) {
        companion object {
                fun fromElement(stepName: String, element: Element, elements: Elements) = JsonStep(
                        stepName = stepName,
                        methodName = element.simpleName.toString(),
                        className = "${elements.getPackageOf(element)}.${element.enclosingElement.simpleName}")
        }
}
