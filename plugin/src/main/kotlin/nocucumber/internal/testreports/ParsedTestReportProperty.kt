package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute

internal data class ParsedTestReportProperty(
        @field:Attribute(name = "name")
        var name: String,
        @field:Attribute(name = "value")
        var value: String) {
    @Suppress("unused")
    constructor() : this("", "")
}
