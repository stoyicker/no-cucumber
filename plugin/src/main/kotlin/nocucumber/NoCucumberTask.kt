package nocucumber

import org.gradle.api.Project
import org.gradle.api.Task

interface NoCucumberTask {
    fun name(): String

    fun apply(project: Project) = apply(project, project.task(name()))

    fun apply(project: Project, task: Task)
}