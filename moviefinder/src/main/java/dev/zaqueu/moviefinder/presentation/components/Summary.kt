package dev.zaqueu.moviefinder.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.zaqueu.moviefinder.R
import dev.zaqueu.ui.theme.LocalSpacing

@Composable
fun Summary(
    summary: String
) {
    val spacing = LocalSpacing.current
    Column() {
        Text(
            text = stringResource(id = R.string.summary),
            style = MaterialTheme.typography.h1,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        Text(
            text = summary,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
        )
    }
}