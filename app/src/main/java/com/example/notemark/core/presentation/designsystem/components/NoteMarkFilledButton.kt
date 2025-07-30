package com.example.notemark.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.notemark.core.presentation.designsystem.DimensButtons
import com.example.notemark.core.presentation.designsystem.NoteMarkButtonState
import com.example.notemark.core.presentation.designsystem.dimen

@Composable
fun NoteMarkFilledButton(
    modifier: Modifier = Modifier,
    dimens: DimensButtons = MaterialTheme.dimen.generic.buttons,
    buttonState: NoteMarkButtonState = NoteMarkButtonState.ENABLE,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .clip(
                RoundedCornerShape(dimens.corner)
            ),
        shape = RoundedCornerShape(dimens.corner),
        contentPadding = PaddingValues(
            horizontal = dimens.paddingHorizontal,
            vertical = dimens.paddingVertical,
        ),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        enabled = buttonState == NoteMarkButtonState.ENABLE,
        onClick = onClick
    ) {
        if (buttonState == NoteMarkButtonState.LOADING) {
            CircularProgressIndicator()
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}