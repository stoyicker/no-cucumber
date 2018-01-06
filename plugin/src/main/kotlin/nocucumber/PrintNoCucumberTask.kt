package nocucumber

import org.gradle.api.Project
import org.gradle.api.Task

internal class PrintNoCucumberTask : NoCucumberTask {
    override fun name() = "noCucumberPrint"

    override fun apply(project: Project, task: Task) {
        // TODO
    }
}
