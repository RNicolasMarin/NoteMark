package com.example.notemark.presentation.ui.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notemark.R
import com.example.notemark.presentation.design_system.DimensGeneric
import com.example.notemark.presentation.design_system.DimensLogin
import com.example.notemark.presentation.design_system.MultiDevicePreview
import com.example.notemark.presentation.design_system.NoteMarkButtonState
import com.example.notemark.presentation.design_system.NoteMarkTheme
import com.example.notemark.presentation.design_system.ScreenConfiguration
import com.example.notemark.presentation.design_system.ScreenConfiguration.PHONE_LANDSCAPE
import com.example.notemark.presentation.design_system.ScreenConfiguration.PHONE_PORTRAIT
import com.example.notemark.presentation.design_system.ScreenConfiguration.TABLET_LANDSCAPE
import com.example.notemark.presentation.design_system.ScreenConfiguration.TABLET_PORTRAIT
import com.example.notemark.presentation.design_system.components.LabelAndInputField
import com.example.notemark.presentation.design_system.components.NoteMarkFilledButton
import com.example.notemark.presentation.design_system.components.NoteMarkTextButton
import com.example.notemark.presentation.design_system.components.TitleAndSubtitleText
import com.example.notemark.presentation.design_system.dimen
import com.example.notemark.presentation.design_system.screenConfiguration
import com.example.notemark.presentation.design_system.statusBarHeight

@Composable
fun RegistrationScreen(
    goToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    RegistrationScreen(
        modifier = modifier,
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                RegistrationAction.GoToLogin -> goToLogin()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun RegistrationScreen(
    state: RegistrationState,
    modifier: Modifier = Modifier,
    dimens: DimensGeneric = MaterialTheme.dimen.generic,
    statusBarHeight: Dp = statusBarHeight(),
    onAction: (RegistrationAction) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Spacer(Modifier.height(statusBarHeight + dimens.spaceAfterStatsBar))
        RegistrationSheet(
            state = state,
            onAction = onAction,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun RegistrationSheet(
    state: RegistrationState,
    modifier: Modifier = Modifier,
    screenConfiguration: ScreenConfiguration = MaterialTheme.screenConfiguration,
    dimens: DimensLogin = MaterialTheme.dimen.login,
    onAction: (RegistrationAction) -> Unit
) {
    val mod = modifier
        .fillMaxSize()
        .clip(
            RoundedCornerShape(
                topStart = dimens.sheet.corner,
                topEnd = dimens.sheet.corner,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        )
        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
        .padding(
            start = dimens.sheet.paddingHorizontal,
            end = dimens.sheet.paddingHorizontal,
            top = dimens.sheet.paddingTop,
            bottom = dimens.sheet.paddingBottom
        )
    when (screenConfiguration) {
        PHONE_PORTRAIT, TABLET_PORTRAIT -> {
            Column(
                modifier = mod
            ) {
                RegistrationSheetText(
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimens.spaceBetweenTextAndForm))

                RegistrationSheetForm(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        PHONE_LANDSCAPE, TABLET_LANDSCAPE -> {
            Row(
                modifier = mod
            ) {
                RegistrationSheetText(
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(dimens.spaceBetweenTextAndForm))

                val scrollState = rememberScrollState()

                RegistrationSheetForm(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.weight(1f).verticalScroll(scrollState)
                )
            }
        }
    }
}

@Composable
fun RegistrationSheetText(
    modifier: Modifier = Modifier
) {
    TitleAndSubtitleText(
        modifier = modifier,
        title = R.string.registration_screen_title,
        subtitle = R.string.registration_screen_subtitle
    )
}

@Composable
fun RegistrationSheetForm(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
    modifier: Modifier = Modifier,
    dimens: DimensGeneric = MaterialTheme.dimen.generic,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.registration_screen_username_label,
            placeHolder = R.string.registration_screen_username_input,
            content = state.userName,
            onValueChange = {
                onAction(RegistrationAction.UpdateOnScreenUserName(it))
            },
            onIsPasswordHidden = {}
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.registration_screen_email_label,
            placeHolder = R.string.registration_screen_email_input,
            content = state.email,
            onValueChange = {
                onAction(RegistrationAction.UpdateOnScreenEmail(it))
            },
            onIsPasswordHidden = {}
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.registration_screen_password_label,
            placeHolder = R.string.registration_screen_password_input,
            content = state.password,
            onValueChange = {
                onAction(RegistrationAction.UpdateOnScreenPassword(it))
            },
            onIsPasswordHidden = {
                onAction(RegistrationAction.UpdatePasswordVisibility(it))
            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.registration_screen_repeat_password_label,
            placeHolder = R.string.registration_screen_repeat_password_input,
            content = state.repeatPassword,
            onValueChange = {
                onAction(RegistrationAction.UpdateOnScreenRepeatPassword(it))
            },
            onIsPasswordHidden = {
                onAction(RegistrationAction.UpdateRepeatPasswordVisibility(it))
            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBeforeFilledButton))

        NoteMarkFilledButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.registration_screen_button_create_account),
            enable = state.buttonState == NoteMarkButtonState.ENABLE,
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBeforeTextButton))

        NoteMarkTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.registration_screen_button_already_account),
            onClick = {
                onAction(RegistrationAction.GoToLogin)
            }
        )
    }


}

@MultiDevicePreview
@Composable
private fun RegistrationScreenPreview() {
    NoteMarkTheme {
        RegistrationScreen(
            modifier = Modifier.fillMaxSize(),
            state = RegistrationState(),
            onAction = {}
        )
    }
}