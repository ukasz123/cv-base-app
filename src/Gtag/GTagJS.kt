package Gtag

import gtag

private fun CustomParams(params: Map<String, String> = emptyMap()): CustomParams = (js("{}") as CustomParams).apply {
    params.forEach { entry ->
        this[entry.key] = entry.value
    }
}

fun setLanguageUsed(
        language: String
) {
    val params = mapOf(
            "lang" to language
    )
    gtag.invoke(command = "set", config = CustomParams(params))
}

private fun Item(): Item = (js("{}") as Item)

private fun EventParams(screenName: String? = null,
                        label: String? = null,
                        items: Array<Map<String, String>>? = null): EventParams = (js("{}") as EventParams).apply {
    screenName?.let { this.screen_name = it }
    label?.let { this.event_label = it }
    items?.let {
        this.items = it.map { map ->
            Item().apply {
                map["name"]?.let { name -> this.name = name }
            }
        }.toTypedArray()
    }
}

fun sendNavigationEvent(
        viewName: String,
        click: Boolean = false
) {
    gtag.invoke(command = "event",
            eventName = "screen_view",
            eventParams = EventParams(items = arrayOf(
                    mapOf("name" to viewName),
                    mapOf("click" to "$click")
            ),
                    screenName = viewName).apply {
                screen_name = viewName

            })

}

fun sendScrollingEvent(
        viewName: String
) {
    gtag.invoke(command = "event", eventName = "scrolling",
            eventParams = EventParams(
                    screenName = viewName,
                    label = "scrolling event",
                    items = arrayOf(mapOf("value" to viewName))
            ))
}

fun sendEvent(eventName: String, value: String?) {
    gtag.invoke("event", eventName = eventName, eventParams = value?.let {
        return@let EventParams(
                screenName = value,
                items = arrayOf(mapOf("value" to value))
        )
    })
}

fun config() {
    gtag.invoke("config", "GA_MEASUREMENT_ID", CustomParams(mapOf(
            "app_name" to "CV_app"

    )))
}