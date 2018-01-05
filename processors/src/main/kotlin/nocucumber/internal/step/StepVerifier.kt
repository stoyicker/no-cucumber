package nocucumber.internal.step

import nocucumber.internal.AnnotationVerifier
import nocucumber.step.Step
import org.junit.Test
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

internal class StepVerifier(messager: Messager) : AnnotationVerifier<Step>(messager) {
    override fun verify(element: Element, annotation: Step) = when {
        annotation.names.filterNot { it.isBlank() }.isEmpty() -> {
            fail("Steps must have at least one non-blank name", element)
            false
        }
        element.kind !in arrayOf(ElementKind.METHOD) -> {
            fail("Steps can only be defined as methods", element)
            false
        }
        element.getAnnotation(Test::class.java) == null -> {
            fail("Step elements must be annotated with @${Test::class.java.canonicalName}", element)
            false
        }
        else -> true
    }
}