package pe.com.master.machines.preferences.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.preferences.repository.PreferencesRepository
import pe.com.master.machines.preferences.repositoryImpl.PreferencesRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    private val TAG = PreferencesModule::class.java.simpleName

    @Provides
    @Singleton
    fun providePreferencesRepository(context: Context): PreferencesRepository =
        PreferencesRepositoryImpl(context)
}