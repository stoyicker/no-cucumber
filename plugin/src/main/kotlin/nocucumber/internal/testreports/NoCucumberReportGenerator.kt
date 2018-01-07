package nocucumber.internal.testreports

import nocucumber.internal.cucumber.Feature

internal class NoCucumberReportGenerator(
        private val features: List<Feature>,
        private val stepMap: Map<String, String>){
    fun fromParsed(parsedTestReport: ParsedTestReport): NoCucumberDeviceReport {
        val device = parsedTestReport.properties.first { it.name == "device" }.value
        val project = parsedTestReport.properties.first { it.name == "project" }.value
        val timestamp = parsedTestReport.timestamp
        var featureReports = emptyList<NoCucumberFeatureReport>()
        features.forEach {
            featureReports += NoCucumberFeatureReport(
                    it.name,
                    it.scenarios.map {
                        NoCucumberScenarioReport(
                                it.name,
                                NoCucumberStepReport(
                                        it.step.name,
                                        parsedTestReport.durationSeconds(it.step, stepMap),
                                        parsedTestReport.result(it.step, stepMap)))
                    })
        }
        return NoCucumberDeviceReport(device, project, timestamp, featureReports)
    }
}
