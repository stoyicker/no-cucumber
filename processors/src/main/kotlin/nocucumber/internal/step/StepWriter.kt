package nocucumber.internal.step

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import nocucumber.internal.Logger
import nocucumber.step.Step
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.lang.model.util.Elements
import javax.tools.StandardLocation

internal class StepWriter(private val elements: Elements, private val filer: Filer, messager: Messager)
    : Logger(messager) {
    private var stepCollection = JsonStepCollection()

    fun saveStep(element: Element) = element.getAnnotation(Step::class.java).run {
        names.forEach {
            saveStep(JsonStep.fromElement(it, element, elements))
        }
    }

    fun dumpStepsToFile() {
        openStepsFile().openWriter().use {
            it.write(Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build().adapter(JsonStepCollection::class.java).toJson(stepCollection))
        }
    }

    private fun saveStep(jsonStep: JsonStep) {
        stepCollection = JsonStepCollection(stepCollection.steps + jsonStep)
    }

    private fun openStepsFile() = filer.createResource(
                    StandardLocation.SOURCE_OUTPUT,
                    "nocucumber.json",
                    "steps.json")
}