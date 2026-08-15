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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.unit.dp

@Composable
fun RegistroUsuario(onVolver: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    // Variables para el Checkbox y RadioButton
    var aceptaTerminos by remember { mutableStateOf(false) }
    var nivelCocina by remember { mutableStateOf("Principiante") }
    val opcionesNivel = listOf("Principiante", "Intermedio", "Experto")

    // Variable para controlar la alerta
    var mostrarDialogoRegistro by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Crear Cuenta",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Input Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Correo
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de Radio Buttons
        Text(text = "¿Cuál es tu nivel cocinando?", style = MaterialTheme.typography.bodyLarge)
        Column(modifier = Modifier.fillMaxWidth()) {
            opcionesNivel.forEach { nivel ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = (nivel == nivelCocina),
                        onClick = { nivelCocina = nivel }
                    )
                    Text(text = nivel, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Checkbox para aceptar tyc.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = aceptaTerminos,
                onCheckedChange = { aceptaTerminos = it }
            )
            Text(
                text = "Acepto los términos y condiciones",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de Registro
        Button(
            // Mostrar alerta
            onClick = { mostrarDialogoRegistro = true },
            modifier = Modifier.fillMaxWidth(),
            // Boton se activa al seleccionar el checkbox de TYC
            enabled = aceptaTerminos && nombre.isNotBlank() && correo.isNotBlank()
        ) {
            Text("Registrar")
        }

        Button(
            onClick = { onVolver() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Volver")
        }

    }

    // Simulamos registro con una alerta
    if (mostrarDialogoRegistro) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoRegistro = false },
            title = {
                Text(text = "Registro Exitoso")
            },
            text = {
                Text(text = "¡Felicidades $nombre! Tu cuenta ha sido creada correctamente. Ahora puedes iniciar sesión.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoRegistro = false
                        onVolver()
                    }
                ) {
                    Text("Ir al Login")
                }
            }
        )
    }
}