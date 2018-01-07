package nocucumber.internal.testreports

import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Root

@Root(name = "properties")
internal data class ParsedTestReportProperties(
    @field:ElementArray(name = "property")
    var properties: Array<ParsedTestReportProperty>) {
    @Suppress("unused")
    constructor() : this(emptyArray())
}
