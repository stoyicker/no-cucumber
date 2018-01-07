package nocucumber.internal.testreports

internal data class NoCucumberScenarioReport(
        private val name: String,
        private val stepReport: NoCucumberStepReport)
