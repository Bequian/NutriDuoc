package com.example.nutriduoc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun PasswordRecovery(onVolver: () -> Unit) {
    var correo by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Recuperar Contraseña",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Ingresa tu correo electrónico y te enviaremos un enlace para restablecer tu contraseña.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { mostrarDialogo = true },
            modifier = Modifier.fillMaxWidth(),
            enabled = correo.isNotBlank()
        ) {
            Text("Enviar enlace de recuperación")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { onVolver() }) {
            Text("Volver al Login")
        }
    }

    // Componente alerta para simular envío de correo
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = {
                Text(text = "Correo Enviado")
            },
            text = {
                Text(text = "Se han enviado las instrucciones de recuperación a: $correo")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                        onVolver()
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}