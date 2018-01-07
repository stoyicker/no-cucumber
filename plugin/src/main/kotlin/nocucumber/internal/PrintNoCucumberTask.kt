package nocucumber.internal

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import nocucumber.internal.cucumber.Feature
import nocucumber.internal.cucumber.FeatureParser
import nocucumber.internal.step.JsonStepAdapter
import nocucumber.internal.step.JsonStepCollection
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File
import java.nio.file.Paths

internal class PrintNoCucumberTask : NoCucumberTask() {
    private var stepMap = mapOf<String, String>()
    private val featureParser = FeatureParser()
    private var features = listOf<Feature>()

    override fun name() = "noCucumberPrint"

    override fun configuration(task: Task) {
        task.setDependsOn(setOf(task.project.getTasksByName("connectedAndroidTest", false)))
    }

    override fun action() = { task: Task ->
        Moshi.Builder()
                .add(JsonStepAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()
                .adapter(JsonStepCollection::class.java)
                .fromJson(jsonStepCollectionFile(task.project).readText(Charsets.UTF_8))!!.steps.forEach {
            stepMap += "${it.className}#${it.methodName}" to it.stepName
        }
        File(packagePath(task.project)).list { _, name -> name.endsWith(".feature") }.forEach {
            features = (features + featureParser.fromFile(File(it), stepMap)).sortedBy { it.name }
        }
    }

    private fun jsonStepCollectionFile(project: Project) =
            Paths.get(packagePath(project), "json", "steps.json").toAbsolutePath().toFile()

    private fun packagePath(project: Project) =
            Paths.get(langSpecificPath(project), "nocucumber").toAbsolutePath().toString()

    private fun langSpecificPath(project: Project): String {
        val kotlinPath = Paths.get(
                project.buildDir.absolutePath, "generated", "source", "kapt", "debugAndroidTest")
                .toAbsolutePath()
        val javaPath = Paths.get(
                project.buildDir.absolutePath, "generated", "source", "apt", "androidTest", "debug")
                .toAbsolutePath()
        return if (kotlinPath.toFile().exists()) {
            kotlinPath
        } else {
            if (javaPath.toFile().exists()) {
                javaPath
            } else {
                throw GradleException("Did not find Kotlin or Java generated source folders")
            }
        }.toAbsolutePath().toString()
    }
}
