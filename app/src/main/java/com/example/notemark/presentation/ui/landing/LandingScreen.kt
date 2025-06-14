package com.example.notemark.presentation.ui.landing

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.notemark.R
import com.example.notemark.presentation.design_system.Dimens
import com.example.notemark.presentation.design_system.MultiDevicePreview
import com.example.notemark.presentation.design_system.NoteMarkTheme
import com.example.notemark.presentation.design_system.ScreenConfiguration
import com.example.notemark.presentation.design_system.ScreenConfiguration.*
import com.example.notemark.presentation.design_system.components.NoteMarkFilledButton
import com.example.notemark.presentation.design_system.components.NoteMarkOutlinedButton
import com.example.notemark.presentation.design_system.dimen
import com.example.notemark.presentation.design_system.landingImageBackground
import com.example.notemark.presentation.design_system.screenConfiguration

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    screenConfiguration: ScreenConfiguration = MaterialTheme.screenConfiguration,
) {
    when (screenConfiguration) {
        PHONE_PORTRAIT -> LandingScreenPortrait(modifier)
        PHONE_LANDSCAPE -> { LandingScreenLandscape(modifier) }
        TABLET_PORTRAIT -> { LandingScreenTabletPortrait(modifier) }
        TABLET_LANDSCAPE -> { LandingScreenLandscape(modifier) }
    }
}

@Composable
fun LandingScreenPortrait(
    modifier: Modifier = Modifier,
    dimens: Dimens = MaterialTheme.dimen,
) {
    Box(
        modifier = modifier.fillMaxSize().background(landingImageBackground),
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
                            topStart = dimens.landingSheetCorner,
                            topEnd = dimens.landingSheetCorner,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                titleStyle = MaterialTheme.typography.titleMedium,
                subtitleStyle = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun LandingScreenLandscape(
    modifier: Modifier = Modifier,
    dimens: Dimens = MaterialTheme.dimen,
) {
    Row(
        modifier = modifier.fillMaxSize().background(landingImageBackground),
    ) {
        Spacer(modifier = Modifier.fillMaxHeight().width(dimens.landingPadding))

        Image(
            painter = painterResource(R.mipmap.landing_screen_image_portrait),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().weight(0.35f),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        Spacer(modifier = Modifier.fillMaxHeight().width(dimens.landingPadding))

        Column(
            modifier = Modifier.fillMaxHeight().weight(0.45f),
        ) {

            Spacer(modifier = Modifier.height(dimens.landingPaddingVertical))

            LandingSheet(
                modifier = Modifier
                    .fillMaxWidth().weight(1f)
                    .clip(
                        RoundedCornerShape(
                            topStart = dimens.landingSheetCorner,
                            topEnd = 0.dp,
                            bottomStart = dimens.landingSheetCorner,
                            bottomEnd = 0.dp
                        )
                    ),
                titleStyle = MaterialTheme.typography.titleMedium,
                subtitleStyle = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(dimens.landingPaddingVertical))
        }
    }
}

@Composable
fun LandingScreenTabletPortrait(
    modifier: Modifier = Modifier,
    dimens: Dimens = MaterialTheme.dimen,
) {
    Box(
        modifier = modifier.fillMaxSize().background(landingImageBackground),
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
            modifier = Modifier.fillMaxSize().padding(horizontal = dimens.landingPaddingVertical),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().weight(0.78f))
            LandingSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.22f)
                    .clip(
                        RoundedCornerShape(
                            topStart = dimens.landingSheetCorner,
                            topEnd = dimens.landingSheetCorner,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                textAligns = TextAlign.Center,
                titleStyle = MaterialTheme.typography.titleLarge,
                subtitleStyle = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun LandingSheet(
    modifier: Modifier = Modifier,
    dimens: Dimens = MaterialTheme.dimen,
    textAligns: TextAlign? = null,
    titleStyle: TextStyle,
    subtitleStyle: TextStyle
) {
    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(
                start = dimens.landingSheetPaddingHorizontal,
                end = dimens.landingSheetPaddingHorizontal,
                top = dimens.landingSheetPaddingTop,
                bottom = dimens.landingSheetPaddingBottom
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //Title
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.landing_screen_title),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = textAligns,
                style = titleStyle
            )

            Spacer(modifier = Modifier.height(dimens.landingSheetPaddingBetweenTexts))

            //Subtitle
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.landing_screen_subtitle),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = textAligns,
                style = subtitleStyle
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //Filled button
            NoteMarkFilledButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.landing_screen_button_register)
            )

            Spacer(modifier = Modifier.height(dimens.landingSheetPaddingBetweenButtons))

            //Outlined button
            NoteMarkOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.landing_screen_button_login)
            )
        }

    }
}

@MultiDevicePreview
@Composable
private fun LandingScreenPreview() {
    NoteMarkTheme {
        LandingScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}