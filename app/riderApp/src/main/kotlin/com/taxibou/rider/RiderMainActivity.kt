package com.taxibou.rider

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.taxibou.app.MainActivity
import com.taxibou.app.StartScope
import com.taxibou.location.buildLocationRepository
import com.taxibou.ui.TaxibouTheme
import kotlin.time.Duration.Companion.seconds

class RiderMainActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppComposable {
            RiderApp()
        }
    }
}

@Composable
fun StartScope.RiderApp() {
    TaxibouTheme(uiMode, lightColorScheme(), darkColorScheme()) {
        Scaffold(Modifier.fillMaxSize()) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 50.dp)
            ) {
                val context = LocalContext.current
                var permissionGranted by remember {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context, Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    )
                }
                val repo =
                    remember(context) { context.buildLocationRepository { interval = 3.seconds } }
                if (permissionGranted) {
                    val location by repo.locationFlow.collectAsState(null)
                    location?.let {
                        Text("Location: ${it.latitude}, ${it.longitude}")
                        LaunchedEffect(it) {
                            Toast.makeText(
                                context,
                                "Location: ${it.latitude}, ${it.longitude}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    val launcher =
                        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
                            permissionGranted = it
                            Toast.makeText(
                                context,
                                "Permission granted: $permissionGranted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    Button(onClick = {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }) {
                        Text("Request permission")
                    }
                }

            }
        }
    }

}