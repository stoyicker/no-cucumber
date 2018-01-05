package nocucumber.internal

import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic

internal abstract class AnnotationVerifier<in AnnotationType : Annotation>(private val messager: Messager) {
    inline fun <reified T : AnnotationType> verify(element: Element) = verify(
            element, element.getAnnotation(T::class.java))

    /**
     * @return <code>true</code> if the verification succeeded, <code>false</code> otherwise.
     */
    protected abstract fun verify(element: Element, annotation: AnnotationType): Boolean

    protected fun fail(message: String, culprit: Element) =
            messager.printMessage(Diagnostic.Kind.ERROR, message, culprit)
}