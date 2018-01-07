package nocucumber.internal.testreports

import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Root

@Root(name = "properties")
internal class ParsedTestReportProperties(
    @ElementArray(name = "property")
    val properties: Array<ParsedTestReportProperty>)
