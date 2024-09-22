package pe.edu.upeu.asistenciaupeujcn.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.asistenciaupeujcn.data.remote.RestEstudiante
import pe.edu.upeu.asistenciaupeujcn.repository.EstudianteRepository
import pe.edu.upeu.asistenciaupeujcn.repository.UsuarioRepository
import pe.edu.upeu.asistenciaupeujcn.repository.UsuarioRepositoryImp
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun userRepository(userRepos: UsuarioRepositoryImp): UsuarioRepository
}

@Module
@InstallIn(SingletonComponent::class)
object EstudianteModule {

    @Provides
    @Singleton
    fun provideRestEstudiante(retrofit: Retrofit): RestEstudiante {
        return retrofit.create(RestEstudiante::class.java)
    }

    @Provides
    @Singleton
    fun provideEstudianteRepository(restEstudiante: RestEstudiante): EstudianteRepository {
        return EstudianteRepository(restEstudiante)
    }
}
