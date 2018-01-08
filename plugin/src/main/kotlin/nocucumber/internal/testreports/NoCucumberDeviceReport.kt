package nocucumber.internal.testreports

internal class NoCucumberDeviceReport(
        val device: String,
        val project: String,
        private val timestamp: String,
        val featureReports: List<NoCucumberFeatureReport>)
