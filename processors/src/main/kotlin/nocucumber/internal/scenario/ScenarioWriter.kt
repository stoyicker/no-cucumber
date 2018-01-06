@file:JvmName("ScenarioWriter")
package nocucumber.internal.scenario

import nocucumber.internal.Logger
import nocucumber.scenario.Scenario
import java.io.File
import java.io.FileWriter
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.StandardLocation

internal class ScenarioWriter(private val filer: Filer, messager: Messager) : Logger(messager) {
    private var featureFileMap = mapOf<String, File>()

    fun writeScenario(element: Element) = element.getAnnotation(Scenario::class.java).run {
        featureNames.map {
            writeScenario(name, it, steps, element)
        }.fold(true) { old, new -> old && new }
    }

    private fun writeScenario(name: String, featureName: String, steps: Array<String>, element: Element): Boolean {
        if (!featureFileMap.containsKey(featureName)) {
            try {
                File(openFeatureFile(featureName).toUri()).run {
                    featureFileMap += featureName to this
                    parentFile.mkdirs()
                    writer(Charsets.UTF_8).use { it.appendln("Feature: $featureName") }
                }
            } catch (exception: Exception) {
                err(exception.message ?: "N/A", element)
                return false
            }
        }
        val featureFile = featureFileMap[featureName]
        when (featureFile) {
            null -> {
                err("Feature file lost from map", element)
                return false
            }
            else -> FileWriter(featureFile, true).use {
                it.appendln()
                it.appendln("  Scenario: $name")
                steps.forEach { step ->
                    it.appendln("    $step")
                }
            }
        }
        return true
    }

    private fun openFeatureFile(featureName: String) =
            filer.createResource(
                    StandardLocation.SOURCE_OUTPUT,
                    "nocucumber",
                    "${featureName.replace(" ", "_")}.feature")
}