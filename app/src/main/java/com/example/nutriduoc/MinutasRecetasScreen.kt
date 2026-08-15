package com.example.nutriduoc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Clase Receta
data class Receta(
    val dia: String,
    val nombre: String,
    val calorias: String,
    val recomendacionNutricional: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinutaScreen(onCerrarSesion: () -> Unit) {

    // Array para almacenar recetas
    val arrayRecetas = arrayOf(
        Receta("Lunes", "Pollo al horno con verduras", "450 kcal", "Alta en proteínas. Acompañar con abundante agua."),
        Receta("Martes", "Lentejas guisadas", "380 kcal", "Rica en hierro y fibra. Añadir limón para mejor absorción."),
        Receta("Miércoles", "Pescado a la plancha con puré", "410 kcal", "Excelente fuente de Omega 3 para la salud cardiovascular."),
        Receta("Jueves", "Tortilla de espinacas", "350 kcal", "Baja en carbohidratos, ideal para una digestión ligera."),
        Receta("Viernes", "Pasta integral con salsa de tomate", "500 kcal", "Aporta energía duradera. Moderar la porción de pasta.")
    )

    var expanded by remember { mutableStateOf(false) }
    var diaSeleccionado by remember { mutableStateOf("Todos") }
    val opcionesDias = listOf("Todos", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Minuta Semanal",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Select tipo día
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = diaSeleccionado,
                onValueChange = {},
                readOnly = true,
                label = { Text("Filtrar por Día") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opcionesDias.forEach { dia ->
                    DropdownMenuItem(
                        text = { Text(dia) },
                        onClick = {
                            diaSeleccionado = dia
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val recetasFiltradas = if (diaSeleccionado == "Todos") {
            arrayRecetas.toList()
        } else {
            arrayRecetas.filter { it.dia == diaSeleccionado }
        }

        // Grilla de recetas
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recetasFiltradas) { receta ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = receta.dia, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                        Text(text = receta.nombre, style = MaterialTheme.typography.titleLarge)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        Text(text = "Calorías: ${receta.calorias}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Recomendación: ${receta.recomendacionNutricional}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onCerrarSesion, modifier = Modifier.fillMaxWidth()) {
            Text("Cerrar Sesión")
        }
    }
}