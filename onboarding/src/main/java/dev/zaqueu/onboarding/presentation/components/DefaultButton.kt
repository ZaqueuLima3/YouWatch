package dev.zaqueu.onboarding.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.theme.Shapes


@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    val spacing = LocalSpacing.current
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnable,
        shape = Shapes.large
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(spacing.spaceSmall)
        )
    }
}
