package com.example.notemark.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.notemark.presentation.design_system.ScreenConfiguration.*
import com.example.notemark.presentation.design_system.components.LabelAndInputField
import com.example.notemark.presentation.design_system.components.NoteMarkFilledButton
import com.example.notemark.presentation.design_system.components.NoteMarkTextButton
import com.example.notemark.presentation.design_system.components.TitleAndSubtitleText
import com.example.notemark.presentation.design_system.components.ValueVisibility.*
import com.example.notemark.presentation.design_system.dimen
import com.example.notemark.presentation.design_system.screenConfiguration
import com.example.notemark.presentation.design_system.statusBarHeight

@Composable
fun LoginScreenRoot(
    goToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        modifier = modifier,
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                LoginAction.GoToRegister -> goToRegister()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    modifier: Modifier = Modifier,
    dimens: DimensGeneric = MaterialTheme.dimen.generic,
    statusBarHeight: Dp = statusBarHeight(),
    onAction: (LoginAction) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Spacer(Modifier.height(statusBarHeight + dimens.spaceAfterStatsBar))
        LoginSheet(
            state = state,
            onAction = onAction,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun LoginSheet(
    state: LoginState,
    modifier: Modifier = Modifier,
    screenConfiguration: ScreenConfiguration = MaterialTheme.screenConfiguration,
    dimens: DimensLogin = MaterialTheme.dimen.login,
    onAction: (LoginAction) -> Unit
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
                LoginSheetText(
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimens.spaceBetweenTextAndForm))

                LoginSheetForm(
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
                LoginSheetText(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun LoginSheetText(
    modifier: Modifier = Modifier
) {
    TitleAndSubtitleText(
        modifier = modifier,
        title = R.string.login_screen_title,
        subtitle = R.string.login_screen_subtitle
    )
}

@Composable
fun LoginSheetForm(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier,
    dimens: DimensGeneric = MaterialTheme.dimen.generic,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.login_screen_email_label,
            placeHolder = R.string.login_screen_email_input,
            value = state.email,
            valueVisibility = NONE,
            onValueChange = {
                onAction(LoginAction.UpdateOnScreenEmail(it))
            },
            onIsPasswordHidden = {}
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.login_screen_password_label,
            placeHolder = R.string.login_screen_password_input,
            value = state.password,
            valueVisibility = if (state.isPasswordHidden) HIDDEN else SHOWN,
            onValueChange = {
                onAction(LoginAction.UpdateOnScreenPassword(it))
            },
            onIsPasswordHidden = {
                onAction(LoginAction.UpdatePasswordVisibility(it))
            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBeforeFilledButton))

        NoteMarkFilledButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login_screen_button_login),
            enable = state.buttonState == NoteMarkButtonState.ENABLE,
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBeforeTextButton))

        NoteMarkTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login_screen_button_no_account),
            onClick = {
                onAction(LoginAction.GoToRegister)
            }
        )
    }


}

@MultiDevicePreview
@Composable
private fun LoginScreenPreview() {
    NoteMarkTheme {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            state = LoginState(),
            onAction = {}
        )
    }
}