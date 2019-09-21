package i18n

import app.fetchDataFromJsonFile
import kotlin.js.Json

object Translator {
    private var currentLanguage = "non-existent"
    var language: String
        get() = currentLanguage
        set(value) {
            if (value != currentLanguage) {
                currentLanguage = value
                updateTranslations()
                Gtag.setLanguageUsed(value)
            }
        }
    var onTranslationUpdated: (String) -> Unit = {}
    private var translation: Json? = null

    fun getTranslation(key: String): String {
        return translation?.get(key) as String? ?: key
    }

    private fun updateTranslations() {
        console.log("Loading translations for $currentLanguage")
        fetchDataFromJsonFile("/data/translations.${Translator.currentLanguage}.json") {
            Translator.translation = it as Json
            Translator.onTranslationUpdated(Translator.currentLanguage)
        }

    }
}