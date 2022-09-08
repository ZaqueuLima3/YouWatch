package dev.zaqueu.onboarding.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.zaqueu.onboarding.R

@Composable
fun PinTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onTogglePinVisibility: () -> Unit,
    modifier: Modifier = Modifier,
    isPinVisible: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = colors,
        label = label,
        enabled = enabled,
        isError = isError,
        singleLine = true,
        visualTransformation = if (isPinVisible)
            VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        trailingIcon = {
            IconButton(onClick = onTogglePinVisibility) {
                Crossfade(targetState = isPinVisible) { visible ->
                    Icon(
                        painter = painterResource(
                            id = if (visible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    )
}
