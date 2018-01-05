package nocucumber.scenario

import nocucumber.internal.NoCucumberProcessor
import nocucumber.internal.scenario.ScenarioVerifier
import nocucumber.internal.scenario.ScenarioWriter
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * Generates scenarios and features as described by the processed annotations.
 */
class ScenarioProcessor : NoCucumberProcessor() {
    private val verifier by lazy { ScenarioVerifier(messager) }
    private val writer by lazy { ScenarioWriter(filer) }

    override fun getSupportedAnnotationTypes() = mutableSetOf(ANNOTATION_CLASS.name)

    override fun getSupportedSourceVersion() = SourceVersion.latest()!!

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(ANNOTATION_CLASS)?.forEach {
            if (verifier.verify<Scenario>(it)) {
                // TODO Write scenario
            }
        }
        return true
    }
}

private val ANNOTATION_CLASS = Scenario::class.java
