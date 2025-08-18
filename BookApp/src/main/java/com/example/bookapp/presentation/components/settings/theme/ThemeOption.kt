package com.example.bookapp.presentation.components.settings.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookapp.presentation.components.settings.SettingsItem

@Composable
fun ThemeOption(
    item: SettingsItem.ThemeSettings,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .padding(4.dp)
                .border(
                    width = 2.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "title",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Text(
            text = stringResource(id = item.titleRes),
            color = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        )
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onSelect() },
            colors = CheckboxDefaults.colors(
                checkedColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
        )
    }
}