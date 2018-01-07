package nocucumber.internal.testreports

import java.io.File

internal data class NoCucumberDeviceReport(
        private val device: String,
        private val project: String,
        private val timestamp: String,
        private val featureReports: List<NoCucumberFeatureReport>) {
    fun dumpToFile(file: File) = file.run {
        createNewFile()
        writeText(this@NoCucumberDeviceReport.toString(), Charsets.UTF_8)
    }
}
