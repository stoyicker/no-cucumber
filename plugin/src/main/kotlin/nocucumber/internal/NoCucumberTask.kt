package nocucumber.internal

import org.gradle.api.Project
import org.gradle.api.Task

abstract class NoCucumberTask {
    abstract fun name(): String

    @Suppress("UNNECESSARY_NOT_NULL_ASSERTION") // False positive, avoids warning about ambiguity in return type
    fun apply(project: Project) = project.task(name()).apply {
        group = "android"
        configuration(this)
        doFirst(action())
    }!!

    open fun configuration(task: Task) {}

    open fun action(): ((Task) -> Unit) = { }
}