package nocucumber.internal

import org.gradle.api.Task

internal class RunNoCucumberTask : NoCucumberTask() {
    override fun name() = "noCucumberRun"

    override fun configuration(task: Task) {
        with (task.project) {
            afterEvaluate {
                task.apply {
                    setDependsOn(setOf(tasks.findByName("connectedAndroidTest")))
                    finalizedBy(tasks.findByName("noCucumberPrint"))
                }
            }
        }
    }
}
