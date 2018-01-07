package nocucumber.internal.testreports

import nocucumber.internal.cucumber.Step
import org.gradle.api.GradleException
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "testsuite", strict = false)
internal class ParsedTestReport(
        @field:Attribute(name = "timestamp")
        var timestamp: String,
        @field:ElementList(entry = "property")
        var properties: List<ParsedTestReportProperty>,
        @Suppress("MemberVisibilityCanPrivate") @field:ElementList(entry = "testcase", inline = true)
        var testCases: List<ParsedTestReportTestCase>) {
    @Suppress("unused")
    constructor() : this(
            "", mutableListOf<ParsedTestReportProperty>(), mutableListOf<ParsedTestReportTestCase>())

    fun durationSeconds(step: Step, stepMap: Map<String, String>) =
            findTestCase(step, stepMap).durationSeconds

    fun result(step: Step, stepMap: Map<String, String>) = findTestCase(step, stepMap).failure.run {
        when (this) {
            null -> Success()
            else -> Failure(this)
        }
    }

    private fun findTestCase(step: Step, stepMap: Map<String, String>): ParsedTestReportTestCase {
        if (stepMap[step.name] == null) {
            throw GradleException("No step found in mapping for '${step.name}'")
        }
        return testCases.first { "${it.className}#${it.methodName}" == stepMap[step.name] }
    }
}