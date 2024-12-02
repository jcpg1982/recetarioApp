package pe.com.master.machines.domain.local.preferences

import pe.com.master.machines.data.repository.local.preferences.PreferencesDataRepository
import javax.inject.Inject

class InitFirstUsesCase @Inject constructor(
    private var preferencesDataRepository: PreferencesDataRepository,
) {

    operator fun invoke() = preferencesDataRepository.isfirst
    operator fun invoke(isFirst: Boolean) {
        preferencesDataRepository.isfirst = isFirst
    }
}