package nocucumber.internal.testreports

import java.io.File
import java.nio.file.Paths
import java.util.*

internal class HtmlReportGenerator {
    fun generate(deviceReport: NoCucumberDeviceReport, targetFile: File) {
        with (targetFile) {
            arrayOf("app.css",
                    "app-theme.css",
                    "bootstrap.min.js",
                    "bootstrap-combined.min.css",
                    "html5shiv.min.js",
                    "icon-github.png",
                    "jquery.smooth-scroll.min.js").forEach { fileName ->
                HtmlReportGenerator::class.java
                        .getResourceAsStream("/test-report-template/$fileName").apply {
                        with(Paths.get(
                                targetFile.parentFile.absolutePath,
                                "static",
                                fileName)
                                .toFile()) {
                            parentFile.mkdirs()
                            createNewFile()
                            outputStream().use {
                                this@apply.copyTo(it)
                            }
                        }
                }
            }
            createNewFile()
            writeText(applyReplacements(
                    deviceReport,
                    HtmlReportGenerator::class.java
                            .getResource("/test-report-template/index.html")
                            .readText(Charsets.UTF_8)), Charsets.UTF_8)
        }
    }

    private fun applyReplacements(deviceReport: NoCucumberDeviceReport, template: String) =
            applyFeatureReportReplacement(deviceReport,
                    applyFeatureIndexReplacement(deviceReport,
                            applyProjectReplacement(deviceReport,
                                    applyDeviceReplacement(deviceReport, template))))

    private fun applyFeatureReportReplacement(deviceReport: NoCucumberDeviceReport, template: String): String {
        val featureReportTemplate =
                HtmlReportGenerator::class.java
                        .getResource("/test-report-template/feature-report.html")
                        .readText(Charsets.UTF_8)
        val featureReport = deviceReport.featureReports.joinToString(separator = "\n") {
            featureReportTemplate
                    .replace("\${FEATURE_ID}", it.name.toLowerCase(Locale.ENGLISH).replace(" ", "_"))
                    .replace("\${FEATURE_NAME}", it.name)
        }
        return template.replace("\${FEATURE_REPORT}", featureReport)
    }

    private fun applyFeatureIndexReplacement(deviceReport: NoCucumberDeviceReport, template: String): String {
        val featureIndexTemplate =
                HtmlReportGenerator::class.java
                        .getResource("/test-report-template/feature-index.html")
                        .readText(Charsets.UTF_8)
        val featureIndex = deviceReport.featureReports.joinToString(separator = "\n") {
            featureIndexTemplate
                    .replace("\${FEATURE_ID}", it.name.toLowerCase(Locale.ENGLISH).replace(" ", "_"))
                    .replace("\${FEATURE_NAME}", it.name)
        }
        return template.replace("\${FEATURE_INDEX}", featureIndex)
    }

    private fun applyProjectReplacement(deviceReport: NoCucumberDeviceReport, template: String) =
            template.replace("\${PROJECT}", deviceReport.project)

    private fun applyDeviceReplacement(deviceReport: NoCucumberDeviceReport, template: String) =
            template.replace("\${DEVICE}", deviceReport.device)
}