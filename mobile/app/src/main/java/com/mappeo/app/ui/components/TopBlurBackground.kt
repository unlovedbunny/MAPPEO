package com.mappeo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mappeo.app.ui.theme.SkyBottom
import com.mappeo.app.ui.theme.SkyTop

/**
 * Fundo gradiente de céu/natureza, usado no cabeçalho das telas de autenticação.
 * Pode ser customizado com cores e altura diferentes.
 */

@Composable
fun TopBlurBackground(
    modifier: Modifier = Modifier,
    height: Dp = 280.dp,
    topColor: Color = SkyTop,
    bottomColor: Color = SkyBottom
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to topColor,
                        0.6f to topColor.copy(alpha = 0.85f),
                        1.0f to bottomColor
                    )
                )
            )
    )
}