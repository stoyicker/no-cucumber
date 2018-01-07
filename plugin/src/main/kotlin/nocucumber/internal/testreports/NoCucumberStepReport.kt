package nocucumber.internal.testreports

internal data class NoCucumberStepReport(
        private val name: String,
        private val durationSeconds: Float,
        private val result: StepResult)

internal sealed class StepResult
internal class Success : StepResult()
internal data class Failure(private val message: String) : StepResult()
