package com.example.nutriduoc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

data class Usuario(
    val user: String,
    val pass: String
)

@Composable
fun LoginScreen(onNavegarRegistro: () -> Unit, onNavegarRecuperar: () -> Unit, onLoginSuccess: () -> Unit ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Variable de estado para controlar la alerta
    var mostrarErrorDialog by remember { mutableStateOf(false) }

    val arrayUser = arrayOf(
        Usuario(user = "user@user.cl", pass = "123"),
        Usuario(user = "user1@user.cl", pass = "123")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Titulo
        Text(
            text = "Minuta Nutricional",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = "DuocUc",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Input Correo
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para Ingresar
        Button(
            onClick = {
                // Simular validacion de credenciaales.
                val credencialesValidas = arrayUser.any { it.user == email && it.pass == password }

                if (credencialesValidas) {
                    onLoginSuccess()
                } else {
                    mostrarErrorDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Vinculos para Registrar y Recuperar Contraseña
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { onNavegarRegistro() }
            ) {
                Text("Registrarse")
            }
            TextButton(
                onClick = { onNavegarRecuperar() }
            ) {
                Text("¿Olvidaste tu contraseña?")
            }
        }
    }

    // Componente para mostrar alertas
    if (mostrarErrorDialog) {
        AlertDialog(
            onDismissRequest = { mostrarErrorDialog = false },
            title = {
                Text(text = "Error de Autenticación")
            },
            text = {
                Text(text = "El correo o la contraseña son incorrectos. Por favor, intenta de nuevo.")
            },
            confirmButton = {
                TextButton(
                    onClick = { mostrarErrorDialog = false }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}