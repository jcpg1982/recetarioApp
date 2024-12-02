package pe.com.master.machines.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import pe.com.master.machines.data.repository.local.database.RecipeEntityDataRepository
import pe.com.master.machines.data.repository.local.preferences.PreferencesDataRepository
import pe.com.master.machines.data.repository.remote.RecipeDataRepository
import pe.com.master.machines.data.repositoryImpl.local.database.RecipeEntityDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.local.preferences.PreferencesDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.remote.RecipeDataRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun provideRecipeDataRepository(recipeDataRepositoryImpl: RecipeDataRepositoryImpl): RecipeDataRepository

    @Binds
    abstract fun provideRecipeEntityDataRepository(recipeEntityDataRepositoryImpl: RecipeEntityDataRepositoryImpl): RecipeEntityDataRepository

    @Binds
    abstract fun providePreferencesDataRepository(preferencesDataRepositoryImpl: PreferencesDataRepositoryImpl): PreferencesDataRepository
}