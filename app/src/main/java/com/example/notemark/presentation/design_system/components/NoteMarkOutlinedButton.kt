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
import com.example.notemark.presentation.design_system.DimensButtons
import com.example.notemark.presentation.design_system.dimen

@Composable
fun NoteMarkOutlinedButton(
    modifier: Modifier = Modifier,
    dimens: DimensButtons = MaterialTheme.dimen.generic.buttons,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .clip(
                RoundedCornerShape(dimens.corner)
            ),
        shape = RoundedCornerShape(dimens.corner),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(
            horizontal = dimens.paddingHorizontal,
            vertical = dimens.paddingVertical,
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall

        )
    }
}