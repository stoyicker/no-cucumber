package nocucumber.internal

internal object StepIdToNameMap {
    private var map = mapOf<String, String>()

    operator fun set(key: String, value: String) { map += key to value }

    operator fun get(key: String) = map[key]
}