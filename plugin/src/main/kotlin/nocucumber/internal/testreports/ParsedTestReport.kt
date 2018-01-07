package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "testsuite", strict = false)
internal data class ParsedTestReport(
        @field:Attribute(name = "timestamp")
        var timestamp: String,
        @field:ElementList(entry = "property")
        var properties: List<ParsedTestReportProperty>,
        @field:ElementList(entry = "testcase", inline = true)
        var testCases: List<ParsedTestReportTestCase>) {
    @Suppress("unused")
    constructor() : this(
            "", mutableListOf<ParsedTestReportProperty>(), mutableListOf<ParsedTestReportTestCase>())
}
