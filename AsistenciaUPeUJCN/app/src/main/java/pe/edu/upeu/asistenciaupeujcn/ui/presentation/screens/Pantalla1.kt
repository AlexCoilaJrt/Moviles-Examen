package pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upeu.asistenciaupeujcn.modelo.Estudiante
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login.EstudianteViewModel


@Composable
fun Pantalla1(
    viewModel: EstudianteViewModel = hiltViewModel(),
    navegarPantalla2: (String) -> Unit
) {
    val estudiantes by viewModel.estudiantes.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var grado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Formulario para agregar estudiante
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        TextField(
            value = grado,
            onValueChange = { grado = it },
            label = { Text("Grado") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Button(onClick = {
            val estudiante = Estudiante(0, nombre, grado)
            viewModel.createEstudiante(estudiante)
        }) {
            Text("Agregar Estudiante")
        }

        // Lista de estudiantes
        LazyColumn {
            items(estudiantes) { estudiante ->
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Text(text = estudiante.nombre, Modifier.weight(1f))
                    Text(text = estudiante.grado, Modifier.weight(1f))
                    Button(onClick = { viewModel.deleteEstudiante(estudiante.id) }) {
                        Text("Eliminar")
                    }
                }
            }
        }

        Button(onClick = { navegarPantalla2("Texto") }) {
            Text("Enviar")
        }
    }
}

