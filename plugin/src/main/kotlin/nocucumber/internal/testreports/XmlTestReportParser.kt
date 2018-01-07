package nocucumber.internal.testreports

import org.gradle.api.GradleException
import org.simpleframework.xml.core.Persister
import java.io.File

internal class XmlTestReportParser {
    private val serializer = Persister()

    fun parse(file: File) = serializer.read(ParsedTestReport::class.java, file) ?:
            throw GradleException("Failed parsing XML test report ${file.absolutePath}")
}