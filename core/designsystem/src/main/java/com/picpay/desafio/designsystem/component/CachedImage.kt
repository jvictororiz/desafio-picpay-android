package com.picpay.desafio.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
fun CachedImage(
    modifier: Modifier = Modifier,
    url: String, contentDescription: String,
    @DrawableRes drawableError: Int
) {
    if (url.isEmpty()) {
        Image(
            modifier = modifier,
            painter =  painterResource(id = drawableError),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            contentDescription = contentDescription,
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = url,
            error = painterResource(id = drawableError),
            contentDescription = contentDescription,
        )
    }
}