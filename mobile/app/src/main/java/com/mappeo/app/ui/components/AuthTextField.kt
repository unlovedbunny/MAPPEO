package com.mappeo.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mappeo.app.ui.theme.ErrorColor
import com.mappeo.app.ui.theme.FieldBackground
import com.mappeo.app.ui.theme.FieldBorder
import com.mappeo.app.ui.theme.MappeoTheme
import com.mappeo.app.ui.theme.PrimaryGreen
import com.mappeo.app.ui.theme.TextSecondary

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    contentDescriptionText: String = placeholder,
    enabled: Boolean = true
) {
    var isFocused by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = when {
            isError   -> ErrorColor
            isFocused -> PrimaryGreen
            else      -> FieldBorder
        },
        animationSpec = tween(durationMillis = 200),
        label = "field_border_color"
    )

    val bgColor by animateColorAsState(
        targetValue = if (isError) ErrorColor.copy(alpha = 0.04f) else FieldBackground,
        animationSpec = tween(200),
        label = "field_bg_color"
    )

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(bgColor, RoundedCornerShape(14.dp))
                .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
                .onFocusChanged { isFocused = it.isFocused }
                .semantics { contentDescription = contentDescriptionText },
            placeholder = {
                Text(
                    text = placeholder,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = if (isFocused) PrimaryGreen else TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Outlined.Visibility
                            else Icons.Outlined.VisibilityOff,
                            contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha",
                            tint = TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else null,
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onNext = { onImeAction() },
                onDone = { onImeAction() },
                onGo   = { onImeAction() },
                onSearch = { onImeAction() }
            ),
            enabled = enabled,
            isError = isError,
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor    = Color.Transparent,
                unfocusedBorderColor  = Color.Transparent,
                errorBorderColor      = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor   = Color.Transparent,
                cursorColor           = PrimaryGreen,
                focusedTextColor      = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor    = MaterialTheme.colorScheme.onBackground
            )
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = ErrorColor,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 6.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthTextFieldPreview() {
    MappeoTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AuthTextField(
                value = "",
                onValueChange = {},
                placeholder = "E-mail",
                leadingIcon = Icons.Outlined.Email
            )
            AuthTextField(
                value = "senha123",
                onValueChange = {},
                placeholder = "Senha",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = true
            )
            AuthTextField(
                value = "invalido",
                onValueChange = {},
                placeholder = "E-mail",
                leadingIcon = Icons.Outlined.Email,
                isError = true,
                errorMessage = "E-mail inválido"
            )
        }
    }
}