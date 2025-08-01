package com.example.notemark.auth.presentation.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notemark.R
import com.example.notemark.core.presentation.designsystem.DimensLanding
import com.example.notemark.core.presentation.designsystem.DimensLandingSheet
import com.example.notemark.core.presentation.designsystem.MultiDevicePreview
import com.example.notemark.core.presentation.designsystem.NoteMarkTheme
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration.*
import com.example.notemark.core.presentation.designsystem.components.NoteMarkFilledButton
import com.example.notemark.core.presentation.designsystem.components.NoteMarkOutlinedButton
import com.example.notemark.core.presentation.designsystem.components.TitleAndSubtitleText
import com.example.notemark.core.presentation.designsystem.dimen
import com.example.notemark.core.presentation.designsystem.landingImageBackground
import com.example.notemark.core.presentation.designsystem.screenConfiguration
import com.example.notemark.auth.presentation.landing.LandingAction.*
import com.example.notemark.core.presentation.designsystem.RootComposable

@Composable
fun LandingScreenRoot(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    RootComposable {
        LandingScreen(
            onAction = { action ->
                when (action) {
                    OnLoginClick -> onLoginClick()
                    OnRegisterClick -> onRegisterClick()
                }
            }
        )
    }
}

@Composable
fun LandingScreen(
    screenConfiguration: ScreenConfiguration = MaterialTheme.screenConfiguration,
    onAction: (LandingAction) -> Unit
) {
    when (screenConfiguration) {
        PHONE_PORTRAIT -> LandingScreenPortrait(onAction = onAction)
        TABLET_PORTRAIT -> LandingScreenTabletPortrait(onAction = onAction)
        PHONE_LANDSCAPE, TABLET_LANDSCAPE -> LandingScreenLandscape(onAction = onAction)
    }
}

@Composable
fun LandingScreenPortrait(
    dimens: DimensLanding = MaterialTheme.dimen.landing,
    onAction: (LandingAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(landingImageBackground),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(R.mipmap.landing_screen_image_portrait),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().weight(0.65f),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter,
            )
            Spacer(modifier = Modifier.fillMaxWidth().weight(0.35f))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().weight(0.60f))
            LandingSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.40f)
                    .clip(
                        RoundedCornerShape(
                            topStart = dimens.sheet.corner,
                            topEnd = dimens.sheet.corner,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                onAction = onAction
            )
        }
    }
}

@Composable
fun LandingScreenLandscape(
    dimens: DimensLanding = MaterialTheme.dimen.landing,
    onAction: (LandingAction) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize().background(landingImageBackground),
    ) {
        Spacer(modifier = Modifier.fillMaxHeight().weight(0.01f))

        Image(
            painter = painterResource(R.mipmap.landing_screen_image_portrait),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().weight(0.35f),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        Spacer(modifier = Modifier.fillMaxHeight().weight(0.01f))

        Column(
            modifier = Modifier.fillMaxHeight().weight(0.45f),
        ) {

            Spacer(modifier = Modifier.weight(0.1f))

            LandingSheet(
                modifier = Modifier
                    .fillMaxWidth().weight(0.8f)
                    .clip(
                        RoundedCornerShape(
                            topStart = dimens.sheet.corner,
                            topEnd = 0.dp,
                            bottomStart = dimens.sheet.corner,
                            bottomEnd = 0.dp
                        )
                    ),
                onAction = onAction
            )

            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

@Composable
fun LandingScreenTabletPortrait(
    dimens: DimensLanding = MaterialTheme.dimen.landing,
    onAction: (LandingAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(landingImageBackground),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(R.mipmap.landing_screen_image_portrait),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().weight(0.9f),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter,
            )
            Spacer(modifier = Modifier.fillMaxWidth().weight(0.1f))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().weight(0.78f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.22f)
            ) {
                Spacer(modifier = Modifier.weight(0.06f))

                LandingSheet(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.8f)
                        .clip(
                            RoundedCornerShape(
                                topStart = dimens.sheet.corner,
                                topEnd = dimens.sheet.corner,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        ),
                    onAction = onAction
                )

                Spacer(modifier = Modifier.weight(0.06f))
            }
        }
    }
}

@Composable
fun LandingSheet(
    modifier: Modifier = Modifier,
    dimens: DimensLandingSheet = MaterialTheme.dimen.landing.sheet,
    onAction: (LandingAction) -> Unit
) {
    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(
                start = dimens.paddingHorizontal,
                end = dimens.paddingHorizontal,
                top = dimens.paddingTop,
                bottom = dimens.paddingBottom
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        TitleAndSubtitleText(
            modifier = Modifier.fillMaxWidth(),
            title = R.string.landing_screen_title,
            subtitle = R.string.landing_screen_subtitle
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //Filled button
            NoteMarkFilledButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.landing_screen_button_register),
                onClick = {
                    onAction(OnRegisterClick)
                }
            )

            Spacer(modifier = Modifier.height(dimens.spaceBetweenButtons))

            //Outlined button
            NoteMarkOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.landing_screen_button_login),
                onClick = {
                    onAction(OnLoginClick)
                }
            )
        }

    }
}

@MultiDevicePreview
@Composable
private fun LandingScreenPreview() {
    NoteMarkTheme {
        LandingScreen(
            onAction = {}
        )
    }
}