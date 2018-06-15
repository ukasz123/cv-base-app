package skills

import app.*
import carousel.carousel
import i18n.Translator
import kotlinx.html.Tag
import react.*
import react.dom.*
import kotlin.js.json

internal interface SkillsState : RState {
    var skillsList: List<List<List<Skill>>>
}

internal class SkillsSection : RComponent<LanguageState, SkillsState>() {

    override fun SkillsState.init() {
        skillsList = emptyList()
    }

    override fun componentWillMount() {
        fetchDataFromJsonFile<SkillsData>("data/skills/skills_data.json") {
            val skillsList = it.skills.asSequence().sortedBy { it.name }
            val primarySkills = skillsList.filter { it.primary }.sortedByDescending { it.level }
                    .windowed(3, 3, true).map { listOf(it) }.toList()

            val secondarySkills = skillsList.filterNot { it.primary }
            val secondarySkillsWithIcons = secondarySkills.filter { !it.icon.isNullOrEmpty() }.sortedByDescending { it.level }
                    .windowed(3, 3, true)
            val secondarySkillsWithoutIcons = secondarySkills.filter { it.icon.isNullOrEmpty() }
                    .sortedByDescending { it.level }.windowed(3,3, true)

            val secondarySkillsGrouped = secondarySkillsWithIcons.plus(secondarySkillsWithoutIcons)
                    .windowed(2, 2, true).toList()

            val combined = primarySkills.plus(secondarySkillsGrouped)
            setState { this.skillsList = combined }
        }
    }

    override fun RBuilder.render() {
        contentSection(
                classes = "lime darken-4",
                id = "skills"
        ) {
            contentTitle(Translator.getTranslation("skills"))
            // carousel
            carousel("cv-skillsData-carousel", "skillsCarousel",
                    state.skillsList.asSequence()
                            .map {
                                return@map fun RBuilder.(): ReactElement {
                                    val blockClass = if (it.size == 1) "block" else ""
                                    return skillsCarouselItem(it, blockClass)
                                }
                            }
            )

        }
    }

    override fun componentDidUpdate(prevProps: LanguageState, prevState: SkillsState) {
        val slider =
                jquery.jq("#skillsCarousel").asDynamic()
        if (slider.hasClass("initialized")) {
            slider.removeClass("initialized")
        }
        slider.carousel(json("fullWidth" to true))
    }
}

private fun RBuilder.skillsCarouselItem(skillsPage: List<List<Skill>>, blockClass: String? = null): ReactElement {
    return div("container") {
        skillsPage.forEach {
            div("row valign-wrapper " + (blockClass ?: "")) {
                val colSize = 12 / it.size
                it.forEach {
                    val bigIcons = it.primary
                    div("col s$colSize") {
                        it.icon?.let {
                            val icon = fun RDOMBuilder<Tag>.() {
                                i("icon-tech-$it") {}
                            }
                            if (bigIcons) {
                                p("center-align white-text h2", icon)
                            } else {
                                h2("center-align white-text", icon)
                            }
                        }
                        if (bigIcons) {
                            h4("center white-text") { +it.name }
                        } else {
                            h5("center white-text") { +it.name }
                        }
                        p("center white-text") {
                            +"${it.level}/10"
                        }
                    }
                }
            }
        }
    }
}


fun RBuilder.skillsData(selectedLanguage: String) = child(SkillsSection::class) {}


internal data class SkillsData(
        val skills: Array<Skill>
)

internal data class Skill(
        val name: String,
        val primary: Boolean,
        val icon: String?,
        val level: Int
)