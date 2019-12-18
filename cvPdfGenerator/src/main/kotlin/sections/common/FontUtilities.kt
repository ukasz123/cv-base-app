package sections.common

import com.itextpdf.text.Font
import com.itextpdf.text.FontFactory
import com.itextpdf.text.pdf.BaseFont

private val  robotoRegular = BaseFont.createFont("fonts/Roboto/Roboto-Regular.ttf", FontFactory.defaultEncoding, true)
internal val sectionHeaderFont = Font(BaseFont.createFont("fonts/Baloo_Bhai/BalooBhai-Regular.ttf", FontFactory.defaultEncoding, true), 20f, Font.NORMAL)//FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16f)
internal val defaultFont = Font(robotoRegular, 12f)
internal val labelFont = Font(BaseFont.createFont("fonts/Roboto/Roboto-Light.ttf", FontFactory.defaultEncoding, true), 11f, Font.ITALIC)
internal val defaultBoldFont = Font(BaseFont.createFont("fonts/Roboto/Roboto-Bold.ttf", FontFactory.defaultEncoding, true), defaultFont.size, Font.NORMAL)
internal val captionFont = Font(robotoRegular, defaultFont.size *0.8f, defaultFont.style)