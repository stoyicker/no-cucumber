package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "testcase", strict = false)
internal data class ParsedTestReportTestCase(
        @Attribute(name = "name")
        val methodName: String,
        @Attribute(name = "classname")
        val className: String,
        @Attribute(name = "time")
        val durationMs: Float)
