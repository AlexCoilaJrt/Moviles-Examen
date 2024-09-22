package pe.edu.upeu.asistenciaupeujcn.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upeu.asistenciaupeujcn.data.remote.RestEstudiante
import pe.edu.upeu.asistenciaupeujcn.modelo.Estudiante

class EstudianteRepository(private val restEstudiante: RestEstudiante) {

    suspend fun getEstudiantes(): List<Estudiante> {
        return withContext(Dispatchers.IO) {
            restEstudiante.getEstudiantes()
        }
    }

    suspend fun createEstudiante(estudiante: Estudiante): Estudiante {
        return withContext(Dispatchers.IO) {
            restEstudiante.createEstudiante(estudiante)
        }
    }

    suspend fun updateEstudiante(id: Int, estudiante: Estudiante): Estudiante {
        return withContext(Dispatchers.IO) {
            restEstudiante.updateEstudiante(id, estudiante)
        }
    }

    suspend fun deleteEstudiante(id: Int) {
        withContext(Dispatchers.IO) {
            restEstudiante.deleteEstudiante(id)
        }
    }
}