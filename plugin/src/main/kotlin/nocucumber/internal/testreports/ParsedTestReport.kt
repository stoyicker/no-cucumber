package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Root

@Root(name = "testsuite")
internal class ParsedTestReport(
        @Attribute(name = "timestamp")
        val timestamp: String,
        @Element(name = "properties")
        val properties: ParsedTestReportProperties,
        @ElementArray(name = "testcase")
        val testCases: ParsedTestReportTestCase)
