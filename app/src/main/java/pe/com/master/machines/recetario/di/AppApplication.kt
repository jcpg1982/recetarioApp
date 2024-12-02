package pe.com.master.machines.recetario.di


import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import pe.com.master.machines.constants.BuildConfig
import pe.com.master.machines.constants.security.KeyStoreHelper.generateKeyIfNeeded

@HiltAndroidApp
class AppApplication : Application() {

    private val TAG = AppApplication::class.java.simpleName

    companion object {
        private var _instance: AppApplication? = null
        val instance: AppApplication
            get() = _instance
                ?: throw IllegalStateException("AppApplication instance is not initialized")
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
        generateKeyIfNeeded()

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build()
            )
        }
    }
}