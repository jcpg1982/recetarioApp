package pe.com.master.machines.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.database.repository.RecipeDatabaseRepository
import pe.com.master.machines.database.repositoryImpl.RecipeDatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBaseRepositoryModule {

    @Binds
    abstract fun provideRecipeDatabaseRepository(recipeDatabaseRepositoryImpl: RecipeDatabaseRepositoryImpl): RecipeDatabaseRepository

}