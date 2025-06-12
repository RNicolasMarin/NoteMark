package com.example.notemark.presentation.design_system

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun ProvideDimens(
    dimens: Dimens,
    content: @Composable () -> Unit
) {
    val dimensSet = remember { dimens }
    CompositionLocalProvider(
        LocalDimen provides dimensSet,
        content = content
    )
}

private val LocalDimen = staticCompositionLocalOf {
    dimensPortrait
}

val MaterialTheme.dimen: Dimens
    @Composable
    get() = LocalDimen.current