package nocucumber.internal

import org.gradle.api.Plugin
import org.gradle.api.Project

class NoCucumberAndroidPlugin : Plugin<Project> {
    private val generateNoCucumberTask = GenerateNoCucumberTask()
    private val printNoCucumberTask = PrintNoCucumberTask()

    override fun apply(project: Project) {
        generateNoCucumberTask.apply(project)
        printNoCucumberTask.apply(project)
    }
}