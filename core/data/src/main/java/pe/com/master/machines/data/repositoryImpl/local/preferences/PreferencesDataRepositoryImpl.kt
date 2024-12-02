package pe.com.master.machines.data.repositoryImpl.local.preferences

import pe.com.master.machines.data.repository.local.preferences.PreferencesDataRepository
import pe.com.master.machines.preferences.repository.PreferencesRepository
import javax.inject.Inject

class PreferencesDataRepositoryImpl @Inject constructor(private val preferencesRepository: PreferencesRepository) :
    PreferencesDataRepository {

    override var isfirst: Boolean
        get() = preferencesRepository.isfirst
        set(value) {
            preferencesRepository.isfirst = value
        }
}