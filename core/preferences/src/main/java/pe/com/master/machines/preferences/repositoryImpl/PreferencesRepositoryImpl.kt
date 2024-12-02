package pe.com.master.machines.preferences.repositoryImpl

import android.content.Context
import pe.com.master.machines.preferences.repository.PreferencesRepository
import pe.com.master.machines.preferences.utils.Constants.NAME_PREFERENCES
import pe.com.master.machines.preferences.utils.Constants.Preferences.PREF_VISITOR
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(context: Context) :
    PreferencesRepository {

    private val TAG = PreferencesRepositoryImpl::class.java.simpleName

    private val preferences = context.getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE)

    private val editor = preferences.edit()

    override var isfirst: Boolean
        get() = preferences.getBoolean(PREF_VISITOR, true)
        set(value) {
            editor.putBoolean(PREF_VISITOR, value).apply()
        }
}