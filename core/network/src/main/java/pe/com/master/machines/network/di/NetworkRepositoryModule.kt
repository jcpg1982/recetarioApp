package pe.com.master.machines.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import pe.com.master.machines.network.repository.RecipeNetworkRepository
import pe.com.master.machines.network.repositoryImpl.RecipeNetworkRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetworkRepositoryModule {

    @Binds
    abstract fun provideRecipeNetworkRepository(recipeNetworkRepositoryImpl: RecipeNetworkRepositoryImpl): RecipeNetworkRepository

}