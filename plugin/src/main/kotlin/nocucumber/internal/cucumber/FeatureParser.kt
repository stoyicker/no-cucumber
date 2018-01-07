package nocucumber.internal.cucumber

import java.io.File

internal class FeatureParser {
    fun fromFile(source: File, stepMap: Map<String, String>): Feature {
        var featureName: String? = null
        var scenarios = listOf<Scenario>()
        var ongoingScenarioName: String? = null
        var ongoingStepName: String? = null
        source.readLines(Charsets.UTF_8).run { map { it.trim() }.forEach { it ->
            println("Line: $it")
            when {
                it.isBlank() || it.startsWith("#") -> {
                    // Skip the line, it is either blank or a comment
                }
                it.startsWith("Feature: ") -> {
                    println("Found feature ${it.substringAfter("Feature: ")}")
                    featureName = it.substringAfter("Feature: ")
                }
                it.startsWith("Scenario: ") -> {
                    if (!scenarios.isEmpty()) {
                        scenarios += buildScenario(ongoingScenarioName!!, ongoingStepName!!, stepMap)
                        println("Resetting stepname")
                        ongoingStepName = null
                    }
                    ongoingScenarioName = it.substringAfter("Scenario: ")
                }
                else -> {
                    ongoingStepName = if (ongoingStepName == null) { it } else { ongoingStepName + it }
                    println("ongoingStepName just became $ongoingStepName")
                }
            }
        }
        }
        // Add the last scenario since it is not bound by another one
        scenarios += buildScenario(ongoingScenarioName!!, ongoingStepName!!, stepMap)
        return Feature(featureName!!, scenarios)
    }

    private fun buildScenario(scenarioName: String, stepName: String, stepMap: Map<String, String>) =
            Scenario(scenarioName, Step(stepMap[stepName]!!)).also {
                print("Adding scenario $scenarioName with step ${stepMap[stepName]}")
            }
}