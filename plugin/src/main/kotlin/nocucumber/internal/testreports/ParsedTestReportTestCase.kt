package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

internal class ParsedTestReportTestCase(
        @field:Attribute(name = "name")
        var methodName: String,
        @field:Attribute(name = "classname")
        var className: String,
        @field:Attribute(name = "time")
        var durationSeconds: Float,
        @field:Element(name = "failure", required = false)
        var failure: String?) {
        @Suppress("unused")
        constructor() : this("", "", 0F, null)
}
