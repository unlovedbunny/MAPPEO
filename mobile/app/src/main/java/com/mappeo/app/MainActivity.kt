package com.mappeo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mappeo.app.navigation.MappeoNavGraph
import com.mappeo.app.ui.theme.MappeoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inicializa a Splash Screen para aplicar o postSplashScreenTheme (Theme.Mappeo)
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MappeoTheme {
                MappeoNavGraph()
            }
        }
    }
}