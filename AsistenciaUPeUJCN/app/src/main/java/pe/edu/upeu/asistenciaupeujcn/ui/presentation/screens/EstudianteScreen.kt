package pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens

// ui/screens/EstudianteScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pe.edu.upeu.asistenciaupeujcn.modelo.Estudiante
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login.EstudianteViewModel

@Composable
fun EstudianteScreen(viewModel: EstudianteViewModel = viewModel()) {
    val estudiantes by viewModel.estudiantes.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var grado by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Formulario para agregar estudiante
        BasicTextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            decorationBox = { innerTextField ->
                if (nombre.isEmpty()) Text("Nombre")
                innerTextField()
            }
        )
        BasicTextField(
            value = grado,
            onValueChange = { grado = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            decorationBox = { innerTextField ->
                if (grado.isEmpty()) Text("Grado")
                innerTextField()
            }
        )
        Button(onClick = {
            val estudiante = Estudiante(0, nombre, grado)
            viewModel.createEstudiante(estudiante)
        }) {
            Text("Agregar Estudiante")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de estudiantes
        estudiantes.forEach { estudiante ->
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(estudiante.nombre, Modifier.weight(1f))
                Text(estudiante.grado, Modifier.weight(1f))
                Button(onClick = { viewModel.deleteEstudiante(estudiante.id) }) {
                    Text("Eliminar")
                }
            }
        }
    }
}
