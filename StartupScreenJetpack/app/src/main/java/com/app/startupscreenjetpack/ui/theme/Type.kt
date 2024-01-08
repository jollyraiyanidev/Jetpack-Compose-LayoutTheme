package com.app.startupscreenjetpack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.startupscreenjetpack.R
val fonts = FontFamily(
    Font(R.font.o_heavy, FontWeight.ExtraBold),
    Font(R.font.o_regualr,FontWeight.Normal),
    Font(R.font.o_demi_bold, FontWeight.SemiBold),
    Font(R.font.o_bold, FontWeight.Bold)
)
val Typography.customTitle: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily =  fonts,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
            fontSize = 30.sp,
            //lineHeight = TextUnit.Unspecified


        )
    }

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)