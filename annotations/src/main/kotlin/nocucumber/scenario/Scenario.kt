package nocucumber.scenario

/**
 * Defines a Cucumber scenario. Scenarios must be defined on an interface, mapped to one or more features and contain
 * a single multi-line step, which name may contain newline characters for clearer descriptions.
 *
 * @property name The name of the scenario.
 * @property featureNames The names of the features the scenario belongs to.
 * @property steps The step describing the scenario.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Scenario(val name: String, val featureNames: Array<String>, val stepId: String)
