package nocucumber

import org.gradle.api.Task

internal class GenerateNoCucumberTask : NoCucumberTask() {
    override fun name() = "noCucumberGenerate"

    override fun configuration(task: Task) {
        with (task.project) {
            afterEvaluate {
                task.finalizedBy(tasks.findByName("kaptDebugAndroidTestKotlin") ?:
                        tasks.getByName("compileDebugAndroidTestJavaWithJavac"))
            }
        }
    }
}
