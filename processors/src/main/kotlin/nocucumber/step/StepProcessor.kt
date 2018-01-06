@file:JvmName("StepProcessor")
package nocucumber.step

import nocucumber.internal.NoCucumberProcessor
import nocucumber.internal.step.StepVerifier
import nocucumber.internal.step.StepWriter
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * Generates steps as described by the processed annotations.
 */
class StepProcessor : NoCucumberProcessor() {
    private val verifier by lazy { StepVerifier(messager) }
    private val stepWriter by lazy { StepWriter(elements, filer, messager) }

    override fun getSupportedAnnotationTypes() = mutableSetOf(ANNOTATION_CLASS.name)

    override fun getSupportedSourceVersion() = SourceVersion.latest()!!

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(ANNOTATION_CLASS)?.forEach {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "Processing @Step from ${it.enclosingElement.simpleName}#${it.simpleName}")
            return true
            if (verifier.verify<Step>(it)) {
                stepWriter.saveStep(it)
            }
        }
        if (roundEnv.processingOver()) {
            stepWriter.dumpStepsToFile()
        }
        return true
    }
}

private val ANNOTATION_CLASS = Step::class.java
