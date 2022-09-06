package dev.zaqueu.moviefinder.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.utils.extensions.parseHtml
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.theme.Shapes

@Composable
fun EpisodeCard(
    name: String,
    image: String?,
    summary: String?,
    onItemClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(300.dp)
            .border(
                width = 0.5.dp,
                shape = Shapes.small,
                brush = SolidColor(Color.LightGray)
            )
            .padding(spacing.spaceMedium)
            .clickable(
                onClick = { onItemClick() }
            )
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(Shapes.large),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Column() {
            Text(
                text = name,
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (summary != null) {
                if (summary.isNotBlank()) {
                    Text(
                        text = stringResource(id = R.string.summary),
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = summary.parseHtml(),
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}