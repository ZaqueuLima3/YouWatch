package dev.zaqueu.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.zaqueu.ui.R

val PoppinsFamily = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

object FontSize {
    val extraSmall: TextUnit = 12.sp
    val small: TextUnit = 14.sp
    val medium: TextUnit = 16.sp
    val large: TextUnit = 18.sp
    val extraLarge: TextUnit = 24.sp
}
