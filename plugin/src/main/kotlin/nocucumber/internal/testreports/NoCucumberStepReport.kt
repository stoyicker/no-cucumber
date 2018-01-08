package nocucumber.internal.testreports

internal class NoCucumberStepReport(
        val name: String,
        val durationSeconds: Float,
        val result: StepResult)

internal sealed class StepResult
internal class Success : StepResult()
internal class Failure(private val message: String) : StepResult()
