package nocucumber

import org.gradle.api.Project
import org.gradle.api.Task

internal class RunNoCucumberTask : NoCucumberTask {
    override fun name() = "noCucumberRun"

    override fun apply(project: Project, task: Task) {
        with (project) {
            afterEvaluate {
                task.setDependsOn(setOf(project.tasks.findByName("connectedAndroidTest")))
            }
        }
    }
}
