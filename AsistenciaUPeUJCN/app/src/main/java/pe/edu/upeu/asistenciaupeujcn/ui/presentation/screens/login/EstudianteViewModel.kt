package pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login// ui/presentation/pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login.EstudianteViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcn.modelo.Estudiante
import pe.edu.upeu.asistenciaupeujcn.repository.EstudianteRepository
import javax.inject.Inject

@HiltViewModel
class EstudianteViewModel @Inject constructor(
    private val repository: EstudianteRepository
) : ViewModel() {

    private val _estudiantes = MutableStateFlow<List<Estudiante>>(emptyList())
    val estudiantes: StateFlow<List<Estudiante>> get() = _estudiantes

    fun getEstudiantes() {
        viewModelScope.launch {
            _estudiantes.value = repository.getEstudiantes()
        }
    }

    fun createEstudiante(estudiante: Estudiante) {
        viewModelScope.launch {
            repository.createEstudiante(estudiante)
            getEstudiantes()
        }
    }

    fun updateEstudiante(id: Int, estudiante: Estudiante) {
        viewModelScope.launch {
            repository.updateEstudiante(id, estudiante)
            getEstudiantes()
        }
    }

    fun deleteEstudiante(id: Int) {
        viewModelScope.launch {
            repository.deleteEstudiante(id)
            getEstudiantes()
        }
    }
}
