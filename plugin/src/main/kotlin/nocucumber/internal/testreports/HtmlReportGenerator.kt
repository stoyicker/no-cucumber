package nocucumber.internal.testreports

import java.io.File

internal class HtmlReportGenerator {
    fun generate(deviceReport: NoCucumberDeviceReport, targetFile: File) {
        with (targetFile) {
            createNewFile()
            writeText(applyReplacements(
                    deviceReport,
                    HtmlReportGenerator::class.java
                            .getResource("/test-report-template/index.html")
                            .readText(Charsets.UTF_8)), Charsets.UTF_8)
        }
    }

    private fun applyReplacements(deviceReport: NoCucumberDeviceReport, template: String)=
        applyProjectReplacement(deviceReport, applyDeviceReplacement(deviceReport, template))

    private fun applyProjectReplacement(deviceReport: NoCucumberDeviceReport, template: String) =
            template.replace("\${PROJECT}", deviceReport.project)

    private fun applyDeviceReplacement(deviceReport: NoCucumberDeviceReport, template: String) =
            template.replace("\${DEVICE}", deviceReport.device)
}