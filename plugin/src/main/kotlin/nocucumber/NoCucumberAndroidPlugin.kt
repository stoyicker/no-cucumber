package nocucumber

import org.gradle.api.Plugin
import org.gradle.api.Project

class NoCucumberAndroidPlugin : Plugin<Project> {
    private val generateNoCucumberTask = GenerateNoCucumberTask()

    override fun apply(project: Project) {
        generateNoCucumberTask.apply(project)
    }
}