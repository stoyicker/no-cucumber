package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "property")
internal data class ParsedTestReportProperty(
        @field:Attribute(name = "name")
        var name: String,
        @field:Attribute(name = "value")
        var value: String) {
    @Suppress("unused")
    constructor() : this("", "")
}
