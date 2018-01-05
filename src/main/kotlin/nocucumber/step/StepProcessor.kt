package nocucumber.step

import nocucumber.scenario.Scenario
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * Generates steps as described by the processed annotations.
 */
class StepProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes() = mutableSetOf(ANNOTATION_CLASS.name)

    override fun getSupportedSourceVersion() = SourceVersion.latest()!!

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(ANNOTATION_CLASS)?.forEach {
            fail("holahola", it)
        }
        return true
    }

    private fun fail(message: String, culprit: Element) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, culprit)
    }
}

private val ANNOTATION_CLASS = Step::class.java
