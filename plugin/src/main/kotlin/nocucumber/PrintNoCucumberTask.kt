package nocucumber

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import java.nio.file.Path
import java.nio.file.Paths

internal class PrintNoCucumberTask : NoCucumberTask {
    override fun name() = "noCucumberPrint"

    override fun apply(project: Project, task: Task) {
        System.out.println("Applying PrintNoCucumberTask")
    }

    private fun jsonStepCollectionFile(project: Project) =
            Paths.get(langSpecificPath(project), "nocucumber", "json", "steps.json").toFile()

    private fun langSpecificPath(project: Project): String {
        val kotlinPath =
                Paths.get(project.buildDir.absolutePath, "generated", "source", "kapt", "debugAndroidTest")
        val javaPath =
                Paths.get(project.buildDir.absolutePath, "generated", "source", "apt", "debugAndroidTest")
        return if (kotlinPath.toFile().exists()) {
            kotlinPath
        } else {
            if (javaPath.toFile().exists()) {
                javaPath
            } else {
                throw GradleException("Did not found Kotlin or Java generated source folders")
            }
        }.toAbsolutePath().toString()
    }
}
