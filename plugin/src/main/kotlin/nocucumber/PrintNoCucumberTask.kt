package nocucumber

import org.gradle.api.Project
import org.gradle.api.Task
import java.nio.file.Paths

internal class PrintNoCucumberTask : NoCucumberTask {
    override fun name() = "noCucumberPrint"

    override fun apply(project: Project, task: Task) {
        System.out.println(Paths.get(project.buildDir.absolutePath,
                "generated", "source", *langSpecificPath(), "nocucumber"))
    }

    private fun langSpecificPath() = arrayOf("kapt", "debugAndroidTest")
}
