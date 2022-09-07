package dev.zaqueu.moviefinder.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.zaqueu.moviefinder.R
import dev.zaqueu.ui.theme.Shapes

@Composable
fun DetailsCover(
    image: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(280.dp)
                .clip(Shapes.large)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
        )
    }
}
