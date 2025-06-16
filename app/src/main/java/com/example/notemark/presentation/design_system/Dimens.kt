package com.example.notemark.presentation.design_system

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DimensButtons(
    val corner: Dp,
    val paddingVertical: Dp,
    val paddingHorizontal: Dp,
)

data class DimensGeneric(
    val buttons: DimensButtons
)

data class DimensLandingSheet(
    val corner: Dp,
    val paddingHorizontal: Dp,
    val paddingTop: Dp,
    val paddingBottom: Dp,
    val spaceBetweenTexts: Dp,
    val spaceBetweenButtons: Dp,
)

data class DimensLanding(
    val sheet: DimensLandingSheet
)

data class Dimens(
    val generic: DimensGeneric,
    val landing: DimensLanding
)





val dimensPortrait = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 12.dp,//12px
            paddingVertical = 12.dp,//12px
            paddingHorizontal = 20.dp,//20px
        )
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 20.dp,//20px
            paddingHorizontal = 16.dp,//16px
            paddingTop = 16.dp,//32px
            paddingBottom = 16.dp, //40px
            spaceBetweenTexts = 4.dp,//6px
            spaceBetweenButtons = 6.dp//12px
        )
    )
)

val dimensLandscape = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 12.dp,//12px
            paddingVertical = 12.dp,//12px
            paddingHorizontal = 20.dp,//20px
        )
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 20.dp,//20px
            paddingHorizontal = 32.dp,//50px
            paddingTop = 20.dp,//40px
            paddingBottom = 20.dp, //40px
            spaceBetweenTexts = 4.dp,//6px
            spaceBetweenButtons = 6.dp//12px
        )
    )
)

val dimensTabletPortrait = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 12.dp,//12px
            paddingVertical = 12.dp,//12px
            paddingHorizontal = 20.dp,//20px
        )
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 24.dp,//20px
            paddingHorizontal = 40.dp,//48px
            paddingTop = 20.dp,//48px
            paddingBottom = 20.dp, //48px
            spaceBetweenTexts = 4.dp,//6px
            spaceBetweenButtons = 6.dp//12px
        )
    )
)

val dimensTabletLandscape = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 24.dp,
            paddingVertical = 24.dp,
            paddingHorizontal = 40.dp,
        )
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 40.dp,
            paddingHorizontal = 96.dp,
            paddingTop = 0.dp,
            paddingBottom = 0.dp,
            spaceBetweenTexts = 12.dp,
            spaceBetweenButtons = 16.dp
        )
    )
)