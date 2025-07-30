package com.example.notemark.auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.notemark.R
import com.example.notemark.core.presentation.designsystem.DimensGeneric
import com.example.notemark.core.presentation.designsystem.DimensLogin
import com.example.notemark.core.presentation.designsystem.MultiDevicePreview
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState
import com.example.notemark.core.presentation.designsystem.NoteMarkTheme
import com.example.notemark.core.presentation.designsystem.ObserveAsEvents
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration.PHONE_LANDSCAPE
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration.PHONE_PORTRAIT
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration.TABLET_LANDSCAPE
import com.example.notemark.core.presentation.designsystem.ScreenConfiguration.TABLET_PORTRAIT
import com.example.notemark.core.presentation.designsystem.components.LabelAndInputField
import com.example.notemark.core.presentation.designsystem.components.NoteMarkFilledButton
import com.example.notemark.core.presentation.designsystem.components.NoteMarkTextButton
import com.example.notemark.core.presentation.designsystem.components.TitleAndSubtitleText
import com.example.notemark.core.presentation.designsystem.dimen
import com.example.notemark.core.presentation.designsystem.screenConfiguration
import com.example.notemark.core.presentation.designsystem.statusBarHeight
import com.example.notemark.auth.presentation.register.RegisterAction.*
import com.example.notemark.auth.presentation.register.RegisterEvent.RegistrationError
import com.example.notemark.auth.presentation.register.RegisterEvent.RegistrationSuccess
import com.example.notemark.core.domain.util.DataError.Network.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreenRoot(
    onLoginClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = koinViewModel()
) {
    var snackBarMessageRes by remember { mutableIntStateOf(-1) }
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegistrationSuccess -> {
                keyboardController?.hide()
                onLoginClick(R.string.register_screen_registration_successful)
            }
            is RegistrationError -> {
                keyboardController?.hide()
                val messageRes = when (event.error) {
                    FORMAT_ERROR, SERIALIZATION -> R.string.register_screen_error_format
                    UNAUTHORIZED -> R.string.register_screen_error_unauthorized
                    METHOD_ERROR -> R.string.register_screen_error_method
                    CONFLICT -> R.string.register_screen_error_conflict
                    TOO_MANY_REQUESTS -> R.string.register_screen_error_too_many_requests
                    NO_INTERNET -> R.string.error_no_internet
                    SERVER_ERROR -> R.string.error_from_server
                    UNKNOWN -> R.string.register_screen_error_unknown
                }
                snackBarMessageRes = messageRes
            }
        }
    }

    RegistrationScreen(
        modifier = modifier,
        state = viewModel.state,
        message = if (snackBarMessageRes != -1) stringResource(snackBarMessageRes) else "",
        onAction = { action ->
            when (action) {
                GoToLogin -> onLoginClick(-1)
                ClearMessage -> {
                    snackBarMessageRes = -1
                }
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
    message: String,
    onAction: (RegisterAction) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
                onAction(ClearMessage)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
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

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun RegistrationSheet(
    state: RegistrationState,
    modifier: Modifier = Modifier,
    screenConfiguration: ScreenConfiguration = MaterialTheme.screenConfiguration,
    dimens: DimensLogin = MaterialTheme.dimen.login,
    onAction: (RegisterAction) -> Unit
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

    val scrollState = rememberScrollState()

    when (screenConfiguration) {
        PHONE_PORTRAIT, TABLET_PORTRAIT -> {
            Column(
                modifier = mod.verticalScroll(scrollState)
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
        title = R.string.register_screen_title,
        subtitle = R.string.register_screen_subtitle
    )
}

@Composable
fun RegistrationSheetForm(
    state: RegistrationState,
    onAction: (RegisterAction) -> Unit,
    modifier: Modifier = Modifier,
    dimens: DimensGeneric = MaterialTheme.dimen.generic,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val fieldEnables = state.buttonState != NoteMarkButtonState.LOADING
        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.register_screen_username_label,
            placeHolder = R.string.register_screen_username_input,
            content = state.userName,
            enable = fieldEnables,
            onValueChange = {
                onAction(UpdateOnScreenUserName(it))
            },
            onIsPasswordHidden = {}
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.register_screen_email_label,
            placeHolder = R.string.register_screen_email_input,
            content = state.email,
            enable = fieldEnables,
            onValueChange = {
                onAction(UpdateOnScreenEmail(it))
            },
            onIsPasswordHidden = {}
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.register_screen_password_label,
            placeHolder = R.string.register_screen_password_input,
            content = state.password,
            enable = fieldEnables,
            onValueChange = {
                onAction(UpdateOnScreenPassword(it))
            },
            onIsPasswordHidden = {
                onAction(UpdatePasswordVisibility(it))
            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelInputFields))

        LabelAndInputField(
            modifier = Modifier.fillMaxWidth(),
            labelRes = R.string.register_screen_repeat_password_label,
            placeHolder = R.string.register_screen_repeat_password_input,
            content = state.repeatPassword,
            enable = fieldEnables,
            onValueChange = {
                onAction(UpdateOnScreenRepeatPassword(it))
            },
            onIsPasswordHidden = {
                onAction(UpdateRepeatPasswordVisibility(it))
            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBeforeFilledButton))

        NoteMarkFilledButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.register_screen_button_create_account),
            buttonState = state.buttonState,
            onClick = {
                onAction(CreateAccount)
            }
        )

        Spacer(modifier = Modifier.height(dimens.spaceBeforeTextButton))

        NoteMarkTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.register_screen_button_already_account),
            onClick = {
                onAction(GoToLogin)
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
            message = "",
            onAction = {}
        )
    }
}