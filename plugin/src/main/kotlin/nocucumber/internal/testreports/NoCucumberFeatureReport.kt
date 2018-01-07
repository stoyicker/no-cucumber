package nocucumber.internal.testreports

internal data class NoCucumberFeatureReport(
        private val name: String,
        private val scenarioReports: List<NoCucumberScenarioReport>)
