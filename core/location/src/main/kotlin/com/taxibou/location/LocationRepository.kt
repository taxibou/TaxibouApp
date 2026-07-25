package com.taxibou.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    val locationFlow: Flow<Location>
}