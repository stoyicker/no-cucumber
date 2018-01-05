package nocucumber

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * Generates features as described by the processed annotations.
 */
@SupportedAnnotationTypes(value = ["nocucumber.Scenario", "nocucumber.Step"])
@SupportedSourceVersion(SourceVersion.RELEASE_6)
class GherkinGenerator : AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        throw IllegalStateException("Hello world, found ${annotations?.size ?: "null"} annotations")
    }
}
