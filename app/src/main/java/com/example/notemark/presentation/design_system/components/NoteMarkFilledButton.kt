package com.example.notemark.presentation.design_system.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.notemark.presentation.design_system.Dimens
import com.example.notemark.presentation.design_system.dimen

@Composable
fun NoteMarkFilledButton(
    modifier: Modifier = Modifier,
    dimens: Dimens = MaterialTheme.dimen,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .clip(
                RoundedCornerShape(dimens.filledButtonCorner)
            ),
        shape = RoundedCornerShape(dimens.filledButtonCorner),
        contentPadding = PaddingValues(
            horizontal = dimens.filledButtonPaddingHorizontal,
            vertical = dimens.filledButtonPaddingVertical,
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall

        )
    }
}