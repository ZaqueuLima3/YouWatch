package dev.zaqueu.moviefinder.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import dev.zaqueu.moviefinder.R
import dev.zaqueu.ui.theme.LocalSpacing

@Composable
fun TabBarHeader(
    onBackClick: () -> Unit,
    title: String = "",
    icon: ImageVector? = null,
    onIconClick: () -> Unit = { }
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                PaddingValues(
                    vertical = spacing.spaceMedium
                )
            )
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable {
                    onBackClick()
                }
        )

        if (title.isNotBlank()) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.favorite),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        onIconClick()
                    }
            )
        }
    }
}
