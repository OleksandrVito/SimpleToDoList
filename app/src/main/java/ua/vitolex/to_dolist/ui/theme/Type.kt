package ua.vitolex.to_dolist.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ua.vitolex.to_dolist.R

// Set of Material typography styles to start with

val montserratFF = FontFamily(
    Font(
        R.font.montserrat_medium,
        weight = FontWeight.Medium
    ),
    Font(
        R.font.montserrat_regular,
        weight = FontWeight.Normal
    ),
    Font(
        R.font.montserrat_bold,
        weight = FontWeight.Bold
    )
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = montserratFF,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = montserratFF,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
/* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)