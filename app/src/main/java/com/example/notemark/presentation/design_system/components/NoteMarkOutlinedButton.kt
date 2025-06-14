package com.example.notemark.presentation.design_system.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.notemark.presentation.design_system.Dimens
import com.example.notemark.presentation.design_system.dimen

@Composable
fun NoteMarkOutlinedButton(
    modifier: Modifier = Modifier,
    dimens: Dimens = MaterialTheme.dimen,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .clip(
                RoundedCornerShape(dimens.filledButtonCorner)
            ),
        shape = RoundedCornerShape(dimens.filledButtonCorner),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
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