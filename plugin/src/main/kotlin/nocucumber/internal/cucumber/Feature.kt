package nocucumber.internal.cucumber

internal data class Feature(internal val name: String, private val scenarios: List<Scenario>)
