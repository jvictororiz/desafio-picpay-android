package com.picpay.desafio.contacts.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.contacts.R
import com.picpay.desafio.contacts.ui.viewModel.model.ContactUi
import com.picpay.desafio.designsystem.component.PicpayCachedImage


@Composable
internal fun ContactItem(
    modifier: Modifier = Modifier,
    contact: ContactUi
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PicpayCachedImage(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .aspectRatio(1.0F),
            url = contact.urlImage,
            drawableError = R.drawable.ic_round_account_circle,
            contentDescription = stringResource(R.string.content_description_contact)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = contact.username,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.4f
                    )
                )
            )
        }
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
internal fun ContactItemNightPreview() {
    MaterialTheme {
        ContactItem(
            modifier = Modifier.fillMaxWidth(),
            contact = ContactUi(
                "",
                "",
                "@jvictororiz",
                "João Victor"
            )
        )
    }
}

@Composable
@Preview
internal fun ContactItemLightPreview() {
    MaterialTheme {
        ContactItem(
            modifier = Modifier.fillMaxWidth(),
            contact = ContactUi(
                "",
                "",
                "@jvictororiz",
                "João Victor"
            )
        )
    }
}