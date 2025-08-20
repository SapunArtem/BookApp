package com.example.bookapp.presentation.components.settings.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.presentation.components.settings.SettingsItem

@Composable
fun LanguageOption(
    item: SettingsItem.LanguageSettings,
    isSelected: Boolean,
    onSelect: () -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .testTag("LanguageOption")
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Image(
            painter = painterResource(id = item.flagRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(item.titleRes),
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.weight(1f))

        if (isSelected) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = stringResource(R.string.selected),
                tint = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
        }

    }
}