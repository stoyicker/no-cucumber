package nocucumber.internal.testreports

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "property")
internal data class ParsedTestReportProperty(
        @Attribute(name = "name")
        val name: String,
        @Attribute(name = "value")
        val value: String)
