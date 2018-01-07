package nocucumber.internal

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import nocucumber.internal.cucumber.Feature
import nocucumber.internal.cucumber.FeatureParser
import nocucumber.internal.step.JsonStepAdapter
import nocucumber.internal.step.JsonStepCollection
import nocucumber.internal.testreports.ParsedTestReport
import nocucumber.internal.testreports.XmlTestReportParser
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File
import java.nio.file.Paths

internal class PrintNoCucumberTask : NoCucumberTask() {
    override fun name() = "noCucumberPrint"

    override fun configuration(task: Task) {
        task.project.afterEvaluate {
            task.setMustRunAfter(mutableSetOf("connectedAndroidTest"))
        }
    }

    override fun action() = { task: Task ->
        var stepMap = mapOf<String, String>()
        val featureParser = FeatureParser()
        var features = listOf<Feature>()
        val testReportParser = XmlTestReportParser()
        var parsedTestReports = emptyList<ParsedTestReport>()
        Moshi.Builder()
                .add(JsonStepAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()
                .adapter(JsonStepCollection::class.java)
                .fromJson(jsonStepCollectionFile(task.project).readText(Charsets.UTF_8))!!.steps.forEach {
            stepMap += it.stepName to "${it.className}#${it.methodName}"
        }
        File(packagePath(task.project)).listFiles { _, name -> name.endsWith(".feature") }.forEach {
            features = (features + featureParser.fromFile(it, stepMap)).sortedBy { it.name }
        }
        testReportFiles(task.project).forEach { parsedTestReports += testReportParser.parse(it) }
    }

    private fun testReportFiles(project: Project) = Paths.get(
            project.buildDir.absolutePath, "outputs", "androidTest-results", "connected")
            .toAbsolutePath().toFile().listFiles { _, name -> name.endsWith(".xml") }

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
