package com.example.notemark.core.presentation.designsystem

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DimensButtons(
    val corner: Dp,
    val paddingVertical: Dp,
    val paddingHorizontal: Dp,
)

data class DimensLabelAndInputField(
    val corner: Dp,
    val paddingVertical: Dp,
    val paddingHorizontal: Dp,
    val spaceBetweenLabelAndInputField: Dp,
    val spaceToStartToMessage: Dp
)

data class DimensGeneric(
    val buttons: DimensButtons,
    val spaceBetweenTexts: Dp,
    val labelAndInputFields: DimensLabelAndInputField,
    val spaceAfterStatsBar: Dp,
    val spaceBetweenLabelInputFields: Dp,
    val spaceBeforeFilledButton: Dp,
    val spaceBeforeTextButton: Dp,
)

data class DimensLandingSheet(
    val corner: Dp,
    val paddingHorizontal: Dp,
    val paddingTop: Dp,
    val paddingBottom: Dp,
    val spaceBetweenButtons: Dp,
)

data class DimensLanding(
    val sheet: DimensLandingSheet
)

data class DimensLoginSheet(
    val corner: Dp,
    val paddingHorizontal: Dp,
    val paddingTop: Dp,
    val paddingBottom: Dp,
    //val spaceBetweenButtons: Dp,
)

data class DimensLogin(
    val sheet: DimensLoginSheet,
    val spaceBetweenTextAndForm: Dp,
)

data class Dimens(
    val generic: DimensGeneric,
    val landing: DimensLanding,
    val login: DimensLogin
)





val dimensPortrait = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 12.dp,//12px
            paddingVertical = 12.dp,//12px
            paddingHorizontal = 20.dp,//20px
        ),
        spaceBetweenTexts = 4.dp,//6px
        labelAndInputFields = DimensLabelAndInputField(
            corner = 12.dp,
            paddingVertical = 12.dp,
            paddingHorizontal = 16.dp,
            spaceBetweenLabelAndInputField = 8.dp,//7.px
            spaceToStartToMessage = 12.dp//12.px
        ),
        spaceAfterStatsBar = 8.dp,//8px
        spaceBetweenLabelInputFields = 16.dp,//16px
        spaceBeforeFilledButton = 24.dp,//24px
        spaceBeforeTextButton = 12.dp,//12px
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 20.dp,//20px
            paddingHorizontal = 16.dp,//16px
            paddingTop = 16.dp,//32px
            paddingBottom = 16.dp, //40px
            spaceBetweenButtons = 6.dp//12px
        )
    ),
    login = DimensLogin(
        sheet = DimensLoginSheet(
            corner = 20.dp,//20px
            paddingHorizontal = 16.dp,//16px
            paddingTop = 32.dp,//32px
            paddingBottom = 16.dp, //40px
        ),
        spaceBetweenTextAndForm = 40.dp//40px
    )
)

val dimensLandscape = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 12.dp,//12px
            paddingVertical = 12.dp,//12px
            paddingHorizontal = 20.dp,//20px
        ),
        spaceBetweenTexts = 4.dp,//6px
        labelAndInputFields = DimensLabelAndInputField(
            corner = 12.dp,
            paddingVertical = 12.dp,
            paddingHorizontal = 16.dp,
            spaceBetweenLabelAndInputField = 8.dp,//7.px
            spaceToStartToMessage = 12.dp//12.px
        ),
        spaceAfterStatsBar = 8.dp,//8px
        spaceBetweenLabelInputFields = 16.dp,//16px
        spaceBeforeFilledButton = 24.dp,//24px
        spaceBeforeTextButton = 12.dp,//12px
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 20.dp,//20px
            paddingHorizontal = 32.dp,//50px
            paddingTop = 20.dp,//40px
            paddingBottom = 20.dp, //40px
            spaceBetweenButtons = 6.dp//12px
        )
    ),
    login = DimensLogin(
        sheet = DimensLoginSheet(
            corner = 20.dp,//20px
            paddingHorizontal = 40.dp,//60px
            paddingTop = 32.dp,//32px
            paddingBottom = 32.dp,//32px
        ),
        spaceBetweenTextAndForm = 24.dp//24px
    )
)

val dimensTabletPortrait = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 12.dp,//12px
            paddingVertical = 12.dp,//12px
            paddingHorizontal = 20.dp,//20px
        ),
        spaceBetweenTexts = 4.dp,//6px
        labelAndInputFields = DimensLabelAndInputField(
            corner = 12.dp,
            paddingVertical = 12.dp,
            paddingHorizontal = 16.dp,
            spaceBetweenLabelAndInputField = 8.dp,//7.px
            spaceToStartToMessage = 12.dp//12.px
        ),
        spaceAfterStatsBar = 8.dp,//8px
        spaceBetweenLabelInputFields = 16.dp,//16px
        spaceBeforeFilledButton = 24.dp,//24px
        spaceBeforeTextButton = 12.dp,//12px
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 24.dp,//20px
            paddingHorizontal = 40.dp,//48px
            paddingTop = 20.dp,//48px
            paddingBottom = 20.dp, //48px
            spaceBetweenButtons = 6.dp//12px
        )
    ),
    login = DimensLogin(
        sheet = DimensLoginSheet(
            corner = 24.dp,//20px
            paddingHorizontal = 120.dp,//48px
            paddingTop = 100.dp,//48px
            paddingBottom = 100.dp, //48px
        ),
        spaceBetweenTextAndForm = 32.dp//32px
    )
)

val dimensTabletLandscape = Dimens(
    generic = DimensGeneric(
        buttons = DimensButtons(
            corner = 24.dp,
            paddingVertical = 24.dp,
            paddingHorizontal = 40.dp,
        ),
        spaceBetweenTexts = 12.dp,
        labelAndInputFields = DimensLabelAndInputField(
            corner = 12.dp,
            paddingVertical = 12.dp,
            paddingHorizontal = 16.dp,
            spaceBetweenLabelAndInputField = 8.dp,//7.px
            spaceToStartToMessage = 12.dp//12.px
        ),
        spaceAfterStatsBar = 8.dp,//8px
        spaceBetweenLabelInputFields = 16.dp,//16px
        spaceBeforeFilledButton = 24.dp,//24px
        spaceBeforeTextButton = 12.dp,//12px
    ),
    landing = DimensLanding(
        sheet = DimensLandingSheet(
            corner = 40.dp,
            paddingHorizontal = 96.dp,
            paddingTop = 0.dp,
            paddingBottom = 0.dp,
            spaceBetweenButtons = 16.dp
        )
    ),
    login = DimensLogin(
        sheet = DimensLoginSheet(
            corner = 40.dp,
            paddingHorizontal = 60.dp,
            paddingTop = 48.dp,
            paddingBottom = 48.dp,
        ),
        spaceBetweenTextAndForm = 24.dp//24px
    )
)