@file:JvmName("Logger")
package nocucumber.internal

import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic

internal abstract class Logger(private val messager: Messager) {
    protected fun err(message: String, culprit: Element) =
            messager.printMessage(Diagnostic.Kind.ERROR, message, culprit)

    protected fun warn(message: String, culprit: Element) =
            messager.printMessage(Diagnostic.Kind.WARNING, message, culprit)
}
