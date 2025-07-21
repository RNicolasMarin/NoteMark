package com.example.notemark.presentation.design_system.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.notemark.R
import com.example.notemark.presentation.design_system.DimensLabelAndInputField
import com.example.notemark.presentation.design_system.LabelAndInputFieldContent
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility
import com.example.notemark.presentation.design_system.LabelAndInputFieldValueVisibility.*
import com.example.notemark.presentation.design_system.components.LabelAndInputFieldMessageState.*
import com.example.notemark.presentation.design_system.dimen
import com.example.notemark.presentation.design_system.getMessageResource

@Composable
fun LabelAndInputField(
    modifier: Modifier = Modifier,
    dimens: DimensLabelAndInputField = MaterialTheme.dimen.generic.labelAndInputFields,
    enable: Boolean = true,
    @StringRes labelRes: Int,
    @StringRes placeHolder: Int,
    content: LabelAndInputFieldContent,
    onValueChange: (String) -> Unit,
    onIsPasswordHidden: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(labelRes),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelAndInputField))

        var isFocused by remember { mutableStateOf(false) }

        val state = when {
            content.errorMessage != null && content.text.isNotEmpty() && !isFocused -> ERROR
            isFocused -> SUPPORT
            else -> NOT_FOCUSED
        }

        val borderColor = when (state) {
            ERROR -> MaterialTheme.colorScheme.error
            SUPPORT -> MaterialTheme.colorScheme.primary
            NOT_FOCUSED -> MaterialTheme.colorScheme.surface
        }

        val backgroundColor = when (state) {
            ERROR, SUPPORT -> MaterialTheme.colorScheme.surfaceContainerLowest
            NOT_FOCUSED -> MaterialTheme.colorScheme.surface
        }

        BasicTextField(
            value = content.text,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            enabled = enable,
            cursorBrush = SolidColor(borderColor),
            singleLine = true,

            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused }
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(dimens.corner)
                )
                .background(backgroundColor, RoundedCornerShape(dimens.corner))
                .padding(horizontal = dimens.paddingHorizontal, vertical = dimens.paddingVertical),
            keyboardOptions = if (content.visibility == HIDDEN) KeyboardOptions(
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password
            ) else
                KeyboardOptions.Default,
            visualTransformation = if (content.visibility == HIDDEN)
                AsteriskPasswordVisualTransformation
            else
                VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (content.text.isEmpty()) {
                            Text(
                                text = stringResource(placeHolder),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        innerTextField()
                    }

                    Spacer(modifier = Modifier.width(dimens.paddingHorizontal))

                    when (content.visibility) {
                        HIDDEN -> {
                            Icon(
                                painter = painterResource(R.drawable.icon_eye_open),
                                contentDescription = null,
                                modifier = Modifier.clickable(
                                    onClick = {
                                        onIsPasswordHidden(false)
                                    }
                                )
                            )
                        }
                        SHOWN -> {
                            Icon(
                                painter = painterResource(R.drawable.icon_eye_close),
                                contentDescription = null,
                                modifier = Modifier.clickable(
                                    onClick = {
                                        onIsPasswordHidden(true)
                                    }
                                )
                            )
                        }
                        NONE -> Unit
                    }
                }
            }
        )

        val message = when {
            state == ERROR -> content.errorMessage
            state == SUPPORT && content.supportingMessage != null -> content.supportingMessage
            else -> null
        }

        if (message != null) {
            Spacer(modifier = Modifier.height(dimens.spaceBetweenLabelAndInputField))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(dimens.spaceToStartToMessage))

                Text(
                    text = stringResource(message.getMessageResource()),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (state == ERROR) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        }
    }
}

val AsteriskPasswordVisualTransformation = VisualTransformation {
    val transformed = "*".repeat(it.text.length)
    TransformedText(
        AnnotatedString(transformed),
        OffsetMapping.Identity
    )
}

enum class LabelAndInputFieldMessageState {
    ERROR,
    SUPPORT,
    NOT_FOCUSED
}