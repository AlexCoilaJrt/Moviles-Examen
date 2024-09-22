package pe.edu.upeu.asistenciaupeujcn.data.remote
import pe.edu.upeu.asistenciaupeujcn.modelo.Estudiante
import retrofit2.http.*

interface RestEstudiante {
    @GET("estudiantes")
    suspend fun getEstudiantes(): List<Estudiante>

    @POST("estudiantes")
    suspend fun createEstudiante(@Body estudiante: Estudiante): Estudiante

    @PUT("estudiantes/{id}")
    suspend fun updateEstudiante(@Path("id") id: Int, @Body estudiante: Estudiante): Estudiante

    @DELETE("estudiantes/{id}")
    suspend fun deleteEstudiante(@Path("id") id: Int)
}