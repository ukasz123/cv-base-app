package sections.icons

import com.itextpdf.text.Font
import com.itextpdf.text.pdf.BaseFont
import pl.ukaszapps.itext.nodes.Node
import pl.ukaszapps.itext.nodes.Text
import sections.common.defaultFont


private val techSkillsFont: Font by lazy {
  val font = BaseFont.createFont("fonts/tech.ttf", BaseFont.IDENTITY_H, true)
  Font(font, defaultFont.size, Font.NORMAL)
}

enum class Skills(internal val code: Char) {
  CSS3('\uE901'),
  GIT('\uE902'),
  GRADLE('\uE903'),
  CPLUSPLUS('\uE904'),
  ANGULAR('\uE905'),
  BOOTSTRAP('\uE906'),
  JAVA('\uE907'),
  ANDROID('\uE908'),
  FLUTTER('\uE915'),
  XAMARIN('\uE91A'),
  SCRIPT('\uE90C'),
  NODEJS('\uE90D'),
  JQUERY('\uE910'),
  SHELL('\uE911'),
  DART('\uE912'),
  PYTHON('\uE913'),
  SQLITE('\uE914'),
  RXJAVA('\uE90B'),
  JAVASCRIPT('\uE90A'),
  HTML5('\uE909'),
  MAVEN('\uE90E'),
  SVG('\uE90F'),
  KOTLIN('\uE900'),
  XML('\uEA80'),
  GOOGLE('\uEA88'),
  IOS('\uEABE')
}

fun techIcon(skill: Skills): Node = Text("${skill.code}", font = techSkillsFont)