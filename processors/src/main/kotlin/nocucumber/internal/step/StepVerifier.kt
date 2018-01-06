package nocucumber.internal.step

import nocucumber.internal.AnnotationVerifier
import nocucumber.step.Step
import org.junit.Test
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

internal class StepVerifier(messager: Messager) : AnnotationVerifier<Step>(messager) {
    private var stepsFound = mapOf<String, Element>()

    override fun verify(element: Element, annotation: Step) = when {
        annotation.names.filterNot { it.isBlank() }.isEmpty() -> {
            err("Steps must have at least one non-blank name", element)
            false
        }
        element.kind !in arrayOf(ElementKind.METHOD) -> {
            err("Steps can only be defined as methods", element)
            false
        }
        element.getAnnotation(Test::class.java) == null -> {
            err("Step elements must be annotated with @${Test::class.java.canonicalName}", element)
            false
        }
        annotation.names.any { it in stepsFound.keys } -> {
            val step = annotation.names.first { it in stepsFound.keys }
            err("Step $step already added by ${stepsFound[step]!!.apply {
                "${enclosingElement.simpleName}#$simpleName"}}}", element)
            false
        }
        else -> {
            annotation.names.forEach { stepsFound += it to element }
            true
        }
    }
}