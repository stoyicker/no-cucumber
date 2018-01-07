package nocucumber.internal.cucumber

import java.io.File

internal class FeatureParser {
    fun fromFile(source: File, stepMap: Map<String, String>): Feature {
        var featureName: String? = null
        var scenarios = listOf<Scenario>()
        var ongoingScenarioName: String? = null
        var ongoingStepName: String? = null
        source.readLines(Charsets.UTF_8).run { map { it.trim() }.forEach {
            when {
                it.isBlank() || it.startsWith("#") -> {
                    // Skip the line, it is either blank or a comment
                }
                it.startsWith("Feature: ") -> {
                    featureName = it.substringAfter("Feature: ")
                }
                it.startsWith("Scenario: ") -> {
                    if (!scenarios.isEmpty()) {
                        scenarios += Scenario(ongoingScenarioName!!, Step(stepMap[ongoingStepName]!!))
                        ongoingStepName = null
                    }
                    ongoingScenarioName = it.substringAfter("Scenario: ")
                }
                else -> {
                    ongoingStepName = if (ongoingStepName == null) { it } else { ongoingStepName + it }
                }
            }
        }
        }
        return Feature(featureName!!, scenarios)
    }
}