package com.pm.savings.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pm.savings.R

//@Composable
//fun MyApplicationTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colors = if (darkTheme) {
//        dar(
//            primary = Color(0xFFBB86FC),
//            primaryVariant = Color(0xFF3700B3),
//            secondary = Color(0xFF03DAC5)
//        )
//    } else {
//        lightColors(
//            primary = Color(0xFF6200EE),
//            primaryVariant = Color(0xFF3700B3),
//            secondary = Color(0xFF03DAC5)
//        )
//    }
//    val typography = Typography(
//        body1 = TextStyle(
//            fontFamily = FontFamily.Default,
//            fontWeight = FontWeight.Normal,
//            fontSize = 16.sp
//        )
//    )
//    val shapes = Shapes(
//        small = RoundedCornerShape(4.dp),
//        medium = RoundedCornerShape(4.dp),
//        large = RoundedCornerShape(0.dp)
//    )
//
//    MaterialTheme(
//        colors = colors,
//        typography = typography,
//        shapes = shapes,
//        content = content
//    )
//}

val textColor = Color(0xFF232323)
val poppins = FontFamily(
    Font(R.font.poppins_normal, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val small12 = TextStyle(
    color = textColor,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)

val small14 = TextStyle(
    color = textColor,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)


val small16 = TextStyle(
    color = textColor,
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)

val headlineSmall = TextStyle(
    color = textColor,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)

val topBarHeadline = TextStyle(
    color = textColor,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)

val secondaryHeadline = TextStyle(
    color = textColor,
    fontSize = 24.sp,
    fontWeight = FontWeight.Normal,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)

val button = TextStyle(
    color = textColor,
    fontSize = 28.sp,
    fontWeight = FontWeight.SemiBold,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)

val headline = TextStyle(
    color = textColor,
    fontSize = 40.sp,
    fontWeight = FontWeight.SemiBold,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    fontFamily = poppins
)