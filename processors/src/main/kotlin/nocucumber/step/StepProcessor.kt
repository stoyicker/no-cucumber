package nocucumber.step

import nocucumber.internal.NoCucumberProcessor
import nocucumber.internal.step.StepVerifier
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
class StepProcessor : NoCucumberProcessor() {
    private val verifier by lazy { StepVerifier(messager) }

    override fun getSupportedAnnotationTypes() = mutableSetOf(ANNOTATION_CLASS.name)

    override fun getSupportedSourceVersion() = SourceVersion.latest()!!

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(ANNOTATION_CLASS)?.forEach {
            if (verifier.verify<Step>(it)) {
                // TODO Write scenario
            }
        }
        return true
    }
}

private val ANNOTATION_CLASS = Step::class.java
