package com.example.nutriduoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.nutriduoc.ui.theme.NutriDuocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriDuocTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        var pantallaActual by remember { mutableStateOf("Login") }

                        when (pantallaActual){
                            "Login" -> {
                                LoginScreen(
                                    onNavegarRegistro = { pantallaActual = "Registro" },
                                    onNavegarRecuperar = { pantallaActual = "Recuperar" },
                                    onLoginSuccess = { pantallaActual = "Minuta" }
                                )
                            }
                            "Registro" -> {
                                RegistroUsuario(
                                    onVolver = { pantallaActual = "Login" }
                                )
                            }
                            "Recuperar" -> {
                                PasswordRecovery(
                                    onVolver = { pantallaActual = "Login" }
                                )
                            }
                            "Minuta" -> {
                                MinutaScreen(
                                    onCerrarSesion = { pantallaActual = "Login" }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}