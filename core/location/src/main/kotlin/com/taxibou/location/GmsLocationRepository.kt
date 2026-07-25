package com.taxibou.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class GmsLocationRepository(context: Context, request: LocationRequest) : LocationRepository {

    @SuppressLint("MissingPermission")
    override val locationFlow: Flow<Location> = callbackFlow {

        val client = LocationServices.getFusedLocationProviderClient(context.applicationContext)
        val listener = LocationListener {
            trySend(it)
        }
        client.requestLocationUpdates(request, listener, null)
        awaitClose {
            client.removeLocationUpdates(listener)
        }
    }
}

enum class LocationPriority {
    High, Balanced, LowPower
}

data class LocationBuildOptions(var priority: LocationPriority, var interval: Duration)

fun Context.buildLocationRepository(config: LocationBuildOptions.() -> Unit): LocationRepository {
    val options = LocationBuildOptions(LocationPriority.Balanced, 10.seconds)
    options.config()
    val priority = when (options.priority) {
        LocationPriority.High -> Priority.PRIORITY_HIGH_ACCURACY
        LocationPriority.Balanced -> Priority.PRIORITY_BALANCED_POWER_ACCURACY
        LocationPriority.LowPower -> Priority.PRIORITY_LOW_POWER
    }
    val request = LocationRequest.Builder(priority, options.interval.inWholeMilliseconds).build()
    return GmsLocationRepository(this, request)
}