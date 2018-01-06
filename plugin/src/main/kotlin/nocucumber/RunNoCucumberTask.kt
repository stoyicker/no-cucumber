package nocucumber

import org.gradle.api.Project
import org.gradle.api.Task

internal class RunNoCucumberTask : NoCucumberTask {
    override fun name() = "noCucumberRun"

    override fun apply(project: Project, task: Task) {
        with (project) {
            afterEvaluate {
                task.apply {
                    setDependsOn(setOf(tasks.findByName("connectedAndroidTest")))
                    finalizedBy(tasks.findByName("noCucumberPrint"))
                }
            }
        }
    }
}
