package nocucumber

/**
 * Defines a Cucumber step. Steps can be reused across different scenarios. The same test can correspond to
 * as many steps as required. Steps must be annotated as [org.junit.Test].
 *
 * @property names The names of the step, as utilized from Gherkin.
 */
@Repeatable
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class Step(vararg val names: String)
