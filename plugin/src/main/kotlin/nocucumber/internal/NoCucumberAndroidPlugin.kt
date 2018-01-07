package nocucumber.internal

import org.gradle.api.Plugin
import org.gradle.api.Project

class NoCucumberAndroidPlugin : Plugin<Project> {
    private val printNoCucumberTask = PrintNoCucumberTask()

    override fun apply(project: Project) { printNoCucumberTask.apply(project) }
}