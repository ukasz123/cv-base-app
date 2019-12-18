package sections.common

import com.itextpdf.text.FontFactory
import java.awt.Font

internal val sectionHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20f)
internal val defaultFont = FontFactory.getFont(FontFactory.COURIER, 14f)
internal val labelFont = FontFactory.getFont(defaultFont.familyname, defaultFont.size, Font.ITALIC)
internal val defaultBoldFont = FontFactory.getFont(defaultFont.familyname, defaultFont.size, Font.BOLD)
internal val captionFont = FontFactory.getFont(defaultFont.familyname, defaultFont.size * 0.8f, defaultFont.style)