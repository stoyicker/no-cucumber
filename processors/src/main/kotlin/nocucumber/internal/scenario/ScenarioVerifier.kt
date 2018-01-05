package nocucumber.internal.scenario

import nocucumber.internal.AnnotationVerifier
import nocucumber.scenario.Scenario
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

internal class ScenarioVerifier(messager: Messager) : AnnotationVerifier<Scenario>(messager) {
    override fun verify(element: Element, annotation: Scenario) = when {
        annotation.name.isBlank() -> {
            fail("Scenario name cannot be blank", element)
            false
        }
        annotation.featureNames.isEmpty() -> {
            fail("Each scenario must belong to at least one feature", element)
            false
        }
        annotation.steps.isEmpty() -> {
            fail("Each scenario must have at least one step", element)
            false
        }
        element.kind !in arrayOf(ElementKind.CLASS, ElementKind.INTERFACE) -> {
            fail("Scenarios can only be defined in classes or interfaces", element)
            false
        }
        else -> true
    }
}