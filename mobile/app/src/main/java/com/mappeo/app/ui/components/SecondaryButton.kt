package com.mappeo.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SecondaryButton(
    text     : String,
    onClick  : () -> Unit,
    modifier : Modifier = Modifier,
    enabled  : Boolean  = true
) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier.height(56.dp),
        enabled  = enabled,
        shape    = RoundedCornerShape(28.dp),
        border   = BorderStroke(
            width = 1.5.dp,
            color = Color.White.copy(alpha = 0.55f)
        ),
        colors   = ButtonDefaults.outlinedButtonColors(
            contentColor           = Color.White,
            disabledContentColor   = Color.White.copy(alpha = 0.4f)
        )
    ) {
        Text(
            text  = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}