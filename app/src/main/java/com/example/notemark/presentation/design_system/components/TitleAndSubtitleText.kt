package com.example.notemark.presentation.design_system.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.notemark.presentation.design_system.DimensGeneric
import com.example.notemark.presentation.design_system.ScreenConfiguration
import com.example.notemark.presentation.design_system.ScreenConfiguration.*
import com.example.notemark.presentation.design_system.dimen
import com.example.notemark.presentation.design_system.screenConfiguration

@Composable
fun TitleAndSubtitleText(
    modifier: Modifier = Modifier,
    screenConfiguration: ScreenConfiguration = MaterialTheme.screenConfiguration,
    dimens: DimensGeneric = MaterialTheme.dimen.generic,
    @StringRes title: Int,
    @StringRes subtitle: Int,
) {
    val textAlignment = when (screenConfiguration) {
        TABLET_PORTRAIT -> TextAlign.Center
        PHONE_PORTRAIT, PHONE_LANDSCAPE, TABLET_LANDSCAPE -> null
    }

    val titleStyle = when (screenConfiguration) {
        TABLET_PORTRAIT, TABLET_LANDSCAPE -> MaterialTheme.typography.titleLarge
        PHONE_PORTRAIT, PHONE_LANDSCAPE -> MaterialTheme.typography.titleMedium
    }

    Column(
        modifier = modifier
    ) {
        //Title
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(title),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = textAlignment,
            style = titleStyle
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenTexts))

        //Subtitle
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(subtitle),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = textAlignment,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}