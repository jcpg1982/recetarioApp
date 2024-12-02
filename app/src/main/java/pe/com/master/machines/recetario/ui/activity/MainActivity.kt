package pe.com.master.machines.recetario.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pe.com.master.machines.design.ui.theme.RecetarioTheme
import pe.com.master.machines.recetario.ui.navigation.NavigationWrapper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecetarioTheme {
                NavigationWrapper(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}