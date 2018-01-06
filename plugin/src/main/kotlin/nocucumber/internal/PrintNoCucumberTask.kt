package nocucumber.internal

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import nocucumber.internal.step.JsonStepAdapter
import nocucumber.internal.step.JsonStepCollection
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import java.nio.file.Paths

internal class PrintNoCucumberTask : NoCucumberTask() {
    private lateinit var stepCollection: JsonStepCollection

    override fun name() = "noCucumberPrint"

    override fun configuration(task: Task) {
        with (task.project) {
            afterEvaluate {
                task.setDependsOn(setOf(tasks.getByName("noCucumberGenerate")))
            }
        }
    }

    override fun action() = { task: Task ->
        stepCollection = Moshi.Builder()
                .add(JsonStepAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()
                .adapter(JsonStepCollection::class.java)
                .fromJson(jsonStepCollectionFile(task.project).readText(Charsets.UTF_8))!!
        stepCollection.steps.forEach {
            println(it)
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
