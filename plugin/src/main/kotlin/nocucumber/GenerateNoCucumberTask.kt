package nocucumber

import org.gradle.api.Project
import org.gradle.api.Task

internal class GenerateNoCucumberTask : NoCucumberTask {
    override fun name() = "noCucumberGenerate"

    override fun apply(project: Project, task: Task) {
        with (project) {
            afterEvaluate {
                task.finalizedBy(tasks.findByName("kaptDebugAndroidTestKotlin") ?:
                        tasks.getByName("generateDebugAndroidTestSources"))
            }
        }
    }
}
