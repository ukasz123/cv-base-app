package sections.common

import com.itextpdf.text.FontFactory
import java.awt.Font

internal val sectionHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18f)
internal val defaultFont = FontFactory.getFont(FontFactory.COURIER)
internal val labelFont = FontFactory.getFont(defaultFont.familyname, defaultFont.size, Font.ITALIC)
internal val defaultBoldFont = FontFactory.getFont(defaultFont.familyname, defaultFont.size, Font.BOLD)
