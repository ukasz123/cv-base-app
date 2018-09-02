package skills

enum class SkillNames(val code: String, val title: String) {
    Android("android", "Android"),
    Kotlin("kotlin", "Kotlin"),
    RxJava("rxjava", "RxJava"),
    Git("git", "Git"),
    Gradle("gradle", "Gradle"),
    CPP("cplusplus", "C++"),
    Maven("maven", "Maven"),
    Java("java", "Java"),
    Angular("angular", "Angular"),
    JavaScript("javascript", "JavaScript"),
    HTML5("html5", "HTML"),
    Bootstrap3("bootstrap", "Bootstrap 3"),
    CSS("css3", "CSS"),
    Google("google", "Google APIs"),
    Xamarin("xamarin", "Xamarin"),
    Flutter("flutter", title = "Flutter"),
    iOS("ios", title ="iOS"),
    Dart(code = "dart", title = "Dart");

}
internal fun forCode(code: String): SkillNames? {
    return SkillNames.values().find { it.code == code }
}