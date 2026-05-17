package com.mappeo.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mappeo.app.ui.theme.AppleButtonBg
import com.mappeo.app.ui.theme.GoogleBlue
import com.mappeo.app.ui.theme.GoogleButtonBg
import com.mappeo.app.ui.theme.MappeoTheme
import com.mappeo.app.ui.theme.TextPrimary

enum class SocialProvider(
    val label: String,
    val icon: String
) {
    GOOGLE(label = "Google", icon = "G"),
    APPLE(label = "Apple",  icon = "")
}

@Composable
fun SocialLoginButton(
    provider: SocialProvider,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(100),
        label = "social_btn_scale"
    )

    val bgColor      = if (provider == SocialProvider.GOOGLE) GoogleButtonBg else AppleButtonBg
    val contentColor = if (provider == SocialProvider.GOOGLE) TextPrimary else Color.White
    val iconColor    = if (provider == SocialProvider.GOOGLE) GoogleBlue else Color.White

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(52.dp)
            .scale(scale),
        enabled = !isLoading,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.25f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = bgColor,
            contentColor   = contentColor
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = contentColor,
                strokeWidth = 2.dp
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = provider.icon,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = iconColor
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = provider.label,
                    style = MaterialTheme.typography.labelLarge,
                    color = contentColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SocialLoginButtonPreview() {
    MappeoTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SocialLoginButton(
                provider = SocialProvider.GOOGLE,
                onClick = {},
                modifier = Modifier.weight(1f)
            )
            SocialLoginButton(
                provider = SocialProvider.APPLE,
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}