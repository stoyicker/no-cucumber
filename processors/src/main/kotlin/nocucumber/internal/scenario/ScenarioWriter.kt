package nocucumber.internal.scenario

import nocucumber.internal.Logger
import nocucumber.scenario.Scenario
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.FileObject
import javax.tools.StandardLocation

internal class ScenarioWriter(private val filer: Filer, messager: Messager) : Logger(messager) {
    fun writeScenario(element: Element) = element.getAnnotation(Scenario::class.java).run {
        featureNames.map {
            writeScenario(name, it, steps, element)
        }.fold(true) { old, new -> old && new }
    }

    private fun writeScenario(name: String, featureName: String, steps: Array<String>, element: Element): Boolean {
        val fileObject: FileObject?
        try {
            fileObject = openFeatureFile(featureName)
        } catch (exception: Exception) {
            err(exception.message ?: "N/A", element)
            return false
        }
        fileObject.openWriter().use {
            it.append("Scenario: $name\n")
            steps.forEach { step ->
                it.append("  $step")
            }
            it.append("\n")
        }
        return true
    }

    private fun openFeatureFile(featureName: String) =
            filer.createResource(StandardLocation.SOURCE_OUTPUT, "nocucumber", "$featureName.feature")
}