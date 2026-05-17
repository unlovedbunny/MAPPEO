package com.mappeo.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mappeo.app.ui.theme.Green500
import com.mappeo.app.ui.theme.Green800
import com.mappeo.app.ui.theme.White

@Composable
fun PrimaryButton(
    text     : String,
    onClick  : () -> Unit,
    modifier : Modifier = Modifier,
    enabled  : Boolean  = true
) {
    Button(
        onClick   = onClick,
        modifier  = modifier.height(56.dp),
        enabled   = enabled,
        shape     = RoundedCornerShape(28.dp),
        colors    = ButtonDefaults.buttonColors(
            containerColor         = Green500,
            contentColor           = White,
            disabledContainerColor = Green500.copy(alpha = 0.5f),
            disabledContentColor   = White.copy(alpha = 0.5f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation  = 0.dp,
            pressedElevation  = 2.dp
        )
    ) {
        Text(
            text  = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}