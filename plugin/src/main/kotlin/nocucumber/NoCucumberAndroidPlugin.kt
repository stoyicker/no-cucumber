package nocucumber

import org.gradle.api.Plugin
import org.gradle.api.Project

class NoCucumberAndroidPlugin : Plugin<Project> {
    private val generateNoCucumberTask = GenerateNoCucumberTask()
    private val printNoCucumberTask = PrintNoCucumberTask()
    private val runNoCucumberTask = RunNoCucumberTask()

    override fun apply(project: Project) {
        generateNoCucumberTask.apply(project)
        printNoCucumberTask.apply(project)
        runNoCucumberTask.apply(project)
    }
}