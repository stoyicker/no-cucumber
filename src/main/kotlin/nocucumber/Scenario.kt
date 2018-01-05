package nocucumber

/**
 * Defines a Cucumber scenario. Scenarios must be associated to a feature and contain one or more steps.
 *
 * @property name The name of the scenario.
 * @property featureName The name of the features the scenario belongs to.
 * @property steps The steps forming the scenario, sorted as necessary.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Scenario(val name: String, val featureName: Array<String>, vararg val steps: String)
