package nocucumber.scenario

/**
 * Defines a Cucumber scenario. Scenarios must be associated to a feature and contain one or more steps.
 *
 * @property name The name of the scenario.
 * @property featureNames The names of the features the scenario belongs to.
 * @property steps The steps forming the scenario, sorted as necessary.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Scenario(val name: String, val featureNames: Array<String>, val steps: Array<String>)
