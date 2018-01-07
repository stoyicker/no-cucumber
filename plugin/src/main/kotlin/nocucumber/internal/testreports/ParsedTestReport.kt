package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Root

@Root(name = "testsuite", strict = false)
internal data class ParsedTestReport(
        @field:Attribute(name = "timestamp")
        var timestamp: Float,
        @field:Element(name = "properties")
        var properties: ParsedTestReportProperties,
        @field:ElementArray(name = "testcase")
        var testCases: Array<ParsedTestReportTestCase>) {
    @Suppress("unused")
    constructor() : this(0F, ParsedTestReportProperties(), emptyArray<ParsedTestReportTestCase>())
}
