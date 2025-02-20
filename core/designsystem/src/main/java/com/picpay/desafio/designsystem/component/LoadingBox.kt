package com.picpay.desafio.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.picpay.desafio.designsystem.theme.PicpayTheme
import com.picpay.desafio.designsystem.theme.PrimaryColor

@Composable
fun LoadingBox(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    PicpayTheme {
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
            if (isLoading) {
                CircularProgressIndicator(color = PrimaryColor)
            }
        }
    }
}
