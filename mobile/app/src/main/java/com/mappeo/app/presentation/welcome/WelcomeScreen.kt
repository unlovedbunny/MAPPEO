package com.mappeo.app.presentation.welcome


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mappeo.app.R
import com.mappeo.app.ui.components.LocationChip
import com.mappeo.app.ui.components.MappeoLogoBox
import com.mappeo.app.ui.components.PrimaryButton
import com.mappeo.app.ui.components.SecondaryButton
import com.mappeo.app.ui.components.StatItem
import com.mappeo.app.ui.theme.*

// ─────────────────────────────────────────────────────────────────────────────
// Dados estáticos da tela (poderiam vir de um UseCase futuramente)
// ─────────────────────────────────────────────────────────────────────────────

private data class LocationChipData(val emoji: String, val name: String)

private val featuredLocations = listOf(
    LocationChipData("🗼", "Paris"),
    LocationChipData("🏔️", "Machu Picchu"),
    LocationChipData("🌸", "Bali"),
    LocationChipData("🗽", "Nova York"),
    LocationChipData("🏛️", "Roma"),
)

private data class StatData(val value: String, val label: String)

private val appStats = listOf(
    StatData("2.4M+", "Viajantes"),
    StatData("180+",  "Países"),
    StatData("12M+",  "Postagens"),
)

// ─────────────────────────────────────────────────────────────────────────────
// WelcomeScreen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun WelcomeScreen(
    onNavigateToLogin    : () -> Unit,
    onNavigateToRegister : () -> Unit,
    viewModel            : WelcomeViewModel = hiltViewModel()
) {
    // Coleta eventos de navegação (one-shot)
    val navigationEvent by viewModel.navigationEvent.collectAsState(initial = null)

    LaunchedEffect(navigationEvent) {
        when (navigationEvent) {
            WelcomeNavigationEvent.NavigateToLogin    -> onNavigateToLogin()
            WelcomeNavigationEvent.NavigateToRegister -> onNavigateToRegister()
            null -> Unit
        }
    }

    WelcomeContent(
        onSignInClicked        = viewModel::onSignInClicked,
        onCreateAccountClicked = viewModel::onCreateAccountClicked
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// WelcomeContent
// ─────────────────────────────────────────────────────────────────────────────

@Composable
internal fun WelcomeContent(
    onSignInClicked        : () -> Unit,
    onCreateAccountClicked : () -> Unit
) {
    // Animação de entrada em cascata
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(modifier = Modifier.fillMaxSize()) {

        // ── 1. Imagem de fundo ───────────────────────────────────────────────
        Image(
            painter        = painterResource(id = R.drawable.bg_welcome),
            contentDescription = null,
            contentScale   = ContentScale.Crop,
            modifier       = Modifier.fillMaxSize()
        )

        // ── 2. Overlay gradiente escuro-verde ────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.00f to Color(0x552D6A4F),
                            0.40f to Color(0xAA1A3D2B),
                            0.75f to Color(0xE51A3D2B),
                            1.00f to Color(0xFF0F2419),
                        )
                    )
                )
        )

        // ── 3. Conteúdo principal ────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(48.dp))

            // Logo
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600)) + slideInVertically(tween(600)) { -40 }
            ) {
                MappeoLogoBox()
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Título "Mappeo"
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 150))
            ) {
                Text(
                    text  = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize   = 48.sp
                    ),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Slogan
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 250))
            ) {
                Text(
                    text  = stringResource(R.string.welcome_slogan),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.80f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Location chips — linha 1
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 350))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        featuredLocations.take(3).forEach { chip ->
                            LocationChip(emoji = chip.emoji, name = chip.name)
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        featuredLocations.drop(3).forEach { chip ->
                            LocationChip(emoji = chip.emoji, name = chip.name)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Stats
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 450))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    appStats.forEach { stat ->
                        StatItem(value = stat.value, label = stat.label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Botão primário — Criar Conta
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 550)) +
                        slideInVertically(tween(600, delayMillis = 550)) { 30 }
            ) {
                PrimaryButton(
                    text     = stringResource(R.string.welcome_create_account),
                    onClick  = onCreateAccountClicked,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botão secundário — Entrar
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 650)) +
                        slideInVertically(tween(600, delayMillis = 650)) { 30 }
            ) {
                SecondaryButton(
                    text    = stringResource(R.string.welcome_sign_in),
                    onClick = onSignInClicked,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Termos
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(600, delayMillis = 750))
            ) {
                Text(
                    text  = stringResource(R.string.welcome_terms),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.55f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
    MappeoTheme {
        WelcomeContent(
            onSignInClicked        = {},
            onCreateAccountClicked = {}
        )
    }
}
