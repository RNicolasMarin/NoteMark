package com.example.notemark.core.presentation.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.notemark.core.presentation.designsystem.DimensButtons
import com.example.notemark.core.presentation.designsystem.dimen

@Composable
fun NoteMarkTextButton(
    modifier: Modifier = Modifier,
    dimens: DimensButtons = MaterialTheme.dimen.generic.buttons,
    text: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(
                horizontal = dimens.paddingHorizontal,
                vertical = dimens.paddingVertical,
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}