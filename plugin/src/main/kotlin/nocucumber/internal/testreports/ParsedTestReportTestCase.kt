package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute

internal class ParsedTestReportTestCase(
        @field:Attribute(name = "name")
        var methodName: String,
        @field:Attribute(name = "classname")
        var className: String,
        @field:Attribute(name = "time")
        var durationMs: Float) {
        @Suppress("unused")
        constructor() : this("", "", 0F)
}
